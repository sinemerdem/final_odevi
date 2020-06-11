package kullanici_arayuzu;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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

public class PersonelEkleGui extends JDialog {

	private static final long serialVersionUID = 1L;
	protected static final Component PersonelEkleGui = null;

	public PersonelEkleGui() throws ParseException {
		JPanel panel = initPanel();

		add(panel);
		setTitle("Personel Ekle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

	}

	private JPanel initPanel() throws ParseException {

		JPanel panel = new JPanel();
		JPanel tamponPanel = new JPanel(new GridLayout(6, 2, 3, 3));
		tamponPanel
				.setBorder(BorderFactory.createTitledBorder("Personel Ekle"));
		JLabel adiJLabel = new JLabel("Adý:", JLabel.RIGHT);
		tamponPanel.add(adiJLabel);
		final JTextField adiField = new JTextField(15);
		tamponPanel.add(adiField);
		JLabel soyadiJLabel = new JLabel("Soyadý:", JLabel.RIGHT);
		tamponPanel.add(soyadiJLabel);
		final JTextField soyadiField = new JTextField(15);
		tamponPanel.add(soyadiField);
		JLabel goreviJLabel = new JLabel("Görevi:", JLabel.RIGHT);
		tamponPanel.add(goreviJLabel);
		final JTextField gorevField = new JTextField(15);
		tamponPanel.add(gorevField);
		MaskFormatter mf1 = new MaskFormatter("+90-(###)-###-##-##");
		mf1.setPlaceholderCharacter('_');
		JLabel telLabel = new JLabel("Telefonu", JLabel.RIGHT);
		final JFormattedTextField telField = new JFormattedTextField(mf1);
		mf1.setPlaceholderCharacter('_');
		tamponPanel.add(telLabel);
		tamponPanel.add(telField);
		JButton kaydetButton = new JButton("Kaydet");
		tamponPanel.add(kaydetButton);
		kaydetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DerbyJdbcPersonel baglanti = new DerbyJdbcPersonel();
				PersonelDomain ekleDomain = new PersonelDomain();
				ekleDomain.setAdi(adiField.getText());
				ekleDomain.setSoyadi(soyadiField.getText());
				
				ekleDomain.setGorevi(gorevField.getText());
				ekleDomain.setTel(telField.getText());
				baglanti.yeniKayit(ekleDomain);
				String[] mesaj = initJOptString();
				ImageIcon hataIcon = new ImageIcon("images/ok.png");
				JOptionPane.showMessageDialog(getParent(), mesaj, "Eriþim Þifrenizi Hatalý Girdiniz !",getDefaultCloseOperation(), hataIcon);}
		});
		JButton iptalButton = new JButton("Ýptal");
		tamponPanel.add(iptalButton);
		iptalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				adiField.setText("");
				soyadiField.setText("");
				gorevField.setText("");
				telField.setText("");

				String[] mesaj = initJOptString();
				ImageIcon hataIcon = new ImageIcon("images/ok.png");
				JOptionPane.showMessageDialog(getParent(), mesaj,"Eriþim Þifrenizi Hatalý Girdiniz !",getDefaultCloseOperation(), hataIcon);

			}
		}
		);
		panel.add(tamponPanel);

		return panel;

	}

	public String[] initJOptString() {

		UIManager.put("OptionPane.okButtonText", "Tamam");

		String JOpHataMsj[] = { "Kayýt Alaný Baþarýlý!" };

		return JOpHataMsj;
	}
}
