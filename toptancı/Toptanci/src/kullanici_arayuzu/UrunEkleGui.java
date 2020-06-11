package kullanici_arayuzu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;



import domain.UrunDomain;
import otomasyon.DerbyJdbcUrun;

public class UrunEkleGui extends JDialog {

	DerbyJdbcUrun baglanti = new DerbyJdbcUrun();
	UrunDomain ekleDomain = new UrunDomain();
	private static final long serialVersionUID = 1L;


	public UrunEkleGui() throws ParseException{
		
		initPencere();
	}
	public void initPencere() throws ParseException{

		JPanel panel = initPanel();

		add(panel);
		setTitle("�r�n Ekle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setResizable(true);
		setVisible(true);
		
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

	}

	private JPanel initPanel() throws ParseException {
		DerbyJdbcUrun baglan = new DerbyJdbcUrun();
		List<UrunDomain> uList = baglan.urunTuruListele();

		JPanel urunPanel = new JPanel();
		JPanel kayitPanel = new JPanel(new GridLayout(6, 1, 3, 3));
		kayitPanel.setBorder(BorderFactory.createTitledBorder("Kay�t Panel"));
		JLabel urunAdiJLabel = new JLabel("Urun Ad�:", JLabel.RIGHT);
		kayitPanel.add(urunAdiJLabel);
		final JTextField adiField = new JTextField(15);
		kayitPanel.add(adiField);

		JLabel urunTuruJLabel = new JLabel("�r�n T�r�:", JLabel.RIGHT);
		kayitPanel.add(urunTuruJLabel);
		final JComboBox<String> urunTuruBox = new JComboBox<String>();
		urunTuruBox.setBackground(Color.decode("#ff8c8c"));
		urunTuruBox.setEditable(true);
		kayitPanel.add(urunTuruBox);
		for (UrunDomain listele : uList) {

			urunTuruBox.addItem(listele.getUrunTuru());

		}

		JLabel urunTarihJLabel = new JLabel("�r�n Fiyat:", JLabel.RIGHT);
		kayitPanel.add(urunTarihJLabel);

		final JDateChooser tarih = new JDateChooser();
		JLabel urunTarihiJLabel = new JLabel("�r�n Tarihi:", JLabel.RIGHT);
		final SimpleDateFormat frmat = new SimpleDateFormat("yyyy/MMMMM/dd");
		

		final JTextField urunFiyatField = new JTextField(15);

		kayitPanel.add(urunFiyatField);
		kayitPanel.add(urunTarihiJLabel);

		

		JButton kaydetButton = new JButton("Kaydet");
		kayitPanel.add(kaydetButton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cevirDtarihi = frmat.format(tarih.getDate());

				ekleDomain.setAdi(adiField.getText());
				ekleDomain.setUTuru(urunTuruBox.getSelectedItem().toString());
				ekleDomain.setUTarih(tarih.getCalendar().toString());
				ekleDomain.setFiyat(Integer.parseInt(urunFiyatField.getText()));
				ekleDomain.setUTarih(cevirDtarihi.toString());
				baglanti.urunKayit(ekleDomain);

				String[] mesaj = initJOptString();
				ImageIcon hataIcon = new ImageIcon("images/ok.png");
				JOptionPane.showMessageDialog(getParent(), mesaj,"Kay�t Ba�ar�l�!", getDefaultCloseOperation(),hataIcon);
			}
		});
		JButton iptalButton = new JButton("�ptal");
		kayitPanel.add(iptalButton);
		iptalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				adiField.setText("");
				urunFiyatField.setText("");
				
				String[] mesaj = initTemizle();
				ImageIcon hataIcon = new ImageIcon("images/ok.png");
				JOptionPane.showMessageDialog(getParent(), mesaj,"Eri�im �ifrenizi Hatal� Girdiniz !",getDefaultCloseOperation(), hataIcon);

			}
		});
		urunPanel.add(kayitPanel);

		return urunPanel;
	}

	public String[] initJOptString() {

		UIManager.put("OptionPane.okButtonText", "Tamam");

		String JOpHataMsj[] = { "Kay�t Ba�ar�l� ! Devam Etmek ��in Tamam'a Bas�n" };

		return JOpHataMsj;
	}

	public String[] initTemizle() {

		UIManager.put("OptionPane.okButtonText", "Tamam");

		String JOpHataMsj[] = { "Kay�t Alan� Temizlendi !" };

		return JOpHataMsj;
	}
}
