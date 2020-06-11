package kullanici_arayuzu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;



import domain.MusteriDomain;
import domain.UrunDomain;
import otomasyon.DerbyJdbcUrun;

public class UrunBulSilDuzenleGui extends JDialog {
	DerbyJdbcUrun baglanti = new DerbyJdbcUrun();
	List<UrunDomain> uTuruListeleDomain = baglanti.kisiListele();
	private static final long serialVersionUID = 1L;

	public UrunBulSilDuzenleGui() throws ParseException {

		BulSilDuzenle();
	}

	public void BulSilDuzenle() throws ParseException {
		JPanel panel = initAraPanel();
		add(panel);
		setTitle("Ürün Bul Sil Düzenle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

	}

	public JPanel initAraPanel() throws ParseException {

		JPanel bulPanel = new JPanel(new BorderLayout());
		JPanel araPanel = new JPanel(new GridLayout(10, 2, 5, 5));
		

		araPanel.setBorder(BorderFactory.createTitledBorder("Ne Aramýþtýnýz ?"));
		JLabel urunNoJLabel = new JLabel("Ürün No:", JLabel.RIGHT);
		araPanel.add(urunNoJLabel);
		final JTextField urunNoField = new JTextField(15);
		araPanel.add(urunNoField);
		JLabel urunAdiJLabel = new JLabel("Urun Adý:", JLabel.RIGHT);
		araPanel.add(urunAdiJLabel);
		final JTextField adiField = new JTextField(15);
		araPanel.add(adiField);

		JLabel urunTuruJLabel = new JLabel("Ürün Türü:", JLabel.RIGHT);
		araPanel.add(urunTuruJLabel);
		final JComboBox urunTuruBox = new JComboBox();
		urunTuruBox.setBackground(Color.decode("#ff8c8c"));
		urunTuruBox.setEditable(true);
		araPanel.add(urunTuruBox);
		for (UrunDomain listele : uTuruListeleDomain) {
			urunTuruBox.addItem(listele.getUrunTuru());
        	}
		JLabel urunTarihJLabel = new JLabel("Ürün Fiyat:", JLabel.RIGHT);
		araPanel.add(urunTarihJLabel);

		final JTextField tarih = new JTextField(15);
		JLabel urunFiyatJLabel = new JLabel("Ürün Tarihi:", JLabel.RIGHT);
		DecimalFormat formatter = new DecimalFormat("#0,000");

		final JFormattedTextField urunFiyatField = new JFormattedTextField(
				formatter);

		araPanel.add(urunFiyatField);
		araPanel.add(urunFiyatJLabel);

		araPanel.add(tarih);
		JButton bulButton = new JButton("Ara");
		bulButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				UrunDomain urunBul = baglanti.bul(Integer.parseInt(urunNoField.getText()));
				adiField.setText(urunBul.getAdi());
				urunTuruBox.setSelectedItem(urunBul.getUrunTuru().toString());
				urunFiyatField.setText(String.valueOf(urunBul.getFiyat()));
				tarih.setText(urunBul.getUTarih().toString());
				urunNoField.setEditable(false);
				urunNoField.setBackground(Color.lightGray);
				urunNoField.setToolTipText("Müþteri Numarasýný Düzenleyemezsiniz !");

			}
		});
		araPanel.add(bulButton);
		JButton silButton = new JButton("Sil");
		araPanel.add(silButton);
		silButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UrunDomain silDomain = new UrunDomain();

				silDomain.setUNo(Integer.parseInt(urunNoField.getText()));

				String[] tamamString = initSil();
				ImageIcon hataIcon = new ImageIcon("images/hata2.png");
				JOptionPane.showMessageDialog(getParent(), tamamString,"Silme Ýþleminiz Baþarýlý !",getDefaultCloseOperation(), hataIcon);
				baglanti.sil(silDomain);
				urunNoField.setEditable(true);
				urunNoField.setBackground(Color.white);
				urunNoField.setText("");
				adiField.setText("");
				urunTuruBox.setSelectedItem("");
				urunFiyatField.setText("");
				tarih.setToolTipText("");

			}
		});
		JButton duzenleButton = new JButton("Düzenle");
		araPanel.add(duzenleButton);
		duzenleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UrunDomain duzenlenecekKisi = new UrunDomain();

				duzenlenecekKisi.setUNo(Integer.parseInt(urunNoField.getText()));

				duzenlenecekKisi.setAdi(adiField.getText());
				duzenlenecekKisi.setUTuru(urunTuruBox.getSelectedItem().toString());
				duzenlenecekKisi.setFiyat(Double.parseDouble(urunFiyatField.getText()));
				duzenlenecekKisi.setUTarih(tarih.getText());
					
				baglanti.duzenle(duzenlenecekKisi);
		}			
		});
		JButton temizleButton = new JButton("Temizle");
		araPanel.add(temizleButton);
		temizleButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				
				urunNoField.setEditable(true);
				urunNoField.setBackground(Color.white);
				urunNoField.setText("");
				adiField.setText("");
				urunTuruBox.setSelectedItem("");
				urunFiyatField.setText("");
				tarih.setText("");
			}
		});
		bulPanel.add(araPanel,BorderLayout.CENTER);
			
		return bulPanel;

	}

	public String[] initJOptString() {

		UIManager.put("OptionPane.okButtonText", "Tamam");
		String JOpHataMsj[] = { "Kayýt Silme Baþarýlý !" };

		return JOpHataMsj;
	}

	public String[] initSil() {

		UIManager.put("OptionPane.okButtonText", "Tamam");

		String JOpHataMsj[] = { "Silme Ýþlemi Baþarýlý" };

		return JOpHataMsj;
	}

}
