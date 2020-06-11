package kullanici_arayuzu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

import domain.PersonelDomain;
import otomasyon.DerbyJdbcPersonel;

public class PersonelAraSilDuzenleGui extends JDialog {
	DerbyJdbcPersonel baglan = new DerbyJdbcPersonel();

	private static final long serialVersionUID = 1L;

	public PersonelAraSilDuzenleGui() throws ParseException {
		JPanel panel = initPanel();

		add(panel);
		setTitle("Personel Ara Sil Düzenle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

	}

	private JPanel initPanel() throws ParseException {
		JPanel panel = new JPanel();
		JPanel tamponPanel = new JPanel(new GridLayout(8, 4, 3, 3));
		tamponPanel.setBorder(BorderFactory.createTitledBorder("Personel Sil Bul Düzenle"));
		JLabel pNoLabel = new JLabel("Personel No:", JLabel.RIGHT);
		tamponPanel.add(pNoLabel);
		final JTextField pNoField = new JTextField(15);
		tamponPanel.add(pNoField);
		JLabel adiJLabel = new JLabel("Personel Adý:", JLabel.RIGHT);
		tamponPanel.add(adiJLabel);
		final JTextField adiField = new JTextField(15);
		tamponPanel.add(adiField);
		JLabel soyadiJLabel = new JLabel("Personel Soyadý:", JLabel.RIGHT);
		tamponPanel.add(soyadiJLabel);
		final JTextField soyadiField = new JTextField(15);
		tamponPanel.add(soyadiField);
		JLabel goreviJLabel = new JLabel("Personel Görevi:", JLabel.RIGHT);
		tamponPanel.add(goreviJLabel);
		final JTextField gorevField = new JTextField(15);
		tamponPanel.add(gorevField);
		MaskFormatter mf1 = new MaskFormatter("+90-(###)-###-##-##");
		mf1.setPlaceholderCharacter('_');

		JLabel telLabel = new JLabel("Personel Telefonu:", JLabel.RIGHT);

		final JFormattedTextField telField = new JFormattedTextField(mf1);
		mf1.setPlaceholderCharacter('_');
		tamponPanel.add(telLabel);
		tamponPanel.add(telField);

		JButton araButton = new JButton("Ara");
		tamponPanel.add(araButton);
		araButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PersonelDomain personelBul = baglan.bul(Integer.parseInt(pNoField.getText()));
				pNoField.setEditable(false);
				pNoField.setBackground(Color.LIGHT_GRAY);
				adiField.setText(personelBul.getAdi());
				soyadiField.setText(personelBul.getSoyadi());
				telField.setText(personelBul.getTel());
				gorevField.setText(personelBul.getGorevi());
				
			}
		});
		JButton silButton = new JButton("Sil");
		tamponPanel.add(silButton);
		silButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PersonelDomain silDomain = new PersonelDomain();

				silDomain.setPNo(Integer.parseInt(pNoField.getText()));

				baglan.sil(silDomain);

				String[] tamamString = initSil();

				ImageIcon hataIcon = new ImageIcon("images/hata2.png");
				JOptionPane.showMessageDialog(getParent(), tamamString, "Silme Ýþlemi Baþarýlý !", getDefaultCloseOperation(), hataIcon);

				pNoField.setText("");
				adiField.setText("");
				soyadiField.setText("");
				telField.setText("");
				
				gorevField.setText("");
				pNoField.setEditable(true);
				pNoField.setBackground(Color.WHITE);
			}
		});

		JButton duzenButton = new JButton("Düzenle");
		tamponPanel.add(duzenButton);
		duzenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PersonelDomain duzenlenecekKisi = new PersonelDomain();
				
				duzenlenecekKisi.setPNo(Integer.parseInt(pNoField .getText()));
				duzenlenecekKisi.setAdi(adiField.getText());
				duzenlenecekKisi.setSoyadi(soyadiField.getText());
				duzenlenecekKisi.setGorevi(gorevField.getText());
				duzenlenecekKisi.setTel(telField.getText());
				
				baglan.duzenle(duzenlenecekKisi);
				
				JOptionPane.showMessageDialog(null, "Düzenleme Baþarýlý");
				
			}
		});
		JButton iptalButton = new JButton("Ýptal");
		tamponPanel.add(iptalButton);
		iptalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				pNoField.setText("");
				adiField.setText("");
				soyadiField.setText("");
				telField.setText("");
				gorevField.setText("");

				String[] tamamString = initTemizle();

				ImageIcon hataIcon = new ImageIcon("images/ok.png");
				JOptionPane.showMessageDialog(getParent(), tamamString, "Tüm Alan Temizlendi", getDefaultCloseOperation(), hataIcon);

				pNoField.setEditable(true);
				pNoField.setBackground(Color.WHITE);

			}
		});

		panel.add(tamponPanel);

		return panel;

	}

	public String[] initTemizle() {

		UIManager.put("OptionPane.okButtonText", "Tamam");

		String JOpHataMsj[] = { "Tüm Alan Temizlendi" };

		return JOpHataMsj;
	}

	public String[] initSil() {

		UIManager.put("OptionPane.okButtonText", "Tamam");

		String JOpHataMsj[] = { "Silme Ýþlemi Baþarýlý" };

		return JOpHataMsj;
	}

}
