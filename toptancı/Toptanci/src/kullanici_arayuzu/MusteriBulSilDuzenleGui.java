package kullanici_arayuzu;

import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

import domain.MusteriDomain;
import domain.UrunDomain;
import otomasyon.DerbyJdbcIleMusteri;

public class MusteriBulSilDuzenleGui extends JDialog {
	DerbyJdbcIleMusteri baglan = new DerbyJdbcIleMusteri();
	private static final long serialVersionUID = 1L;

	public MusteriBulSilDuzenleGui() throws ParseException {
		JPanel musteriPanel = initPanel();

		add(musteriPanel);

		setTitle("M��teri Bul Listele D�zenle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

	}

	private JPanel initPanel() throws ParseException {
		JPanel musteriPanel = new JPanel();
		JPanel tamponPanel = new JPanel(new GridLayout(9, 4, 3, 3));
		tamponPanel.setBorder(BorderFactory.createTitledBorder("M��teri Bul"));

		JLabel museriNoLabel = new JLabel("M��teri No:", JLabel.RIGHT);
		tamponPanel.add(museriNoLabel);
		final JTextField musteriNoField = new JTextField(15);
		tamponPanel.add(musteriNoField);
		JLabel musteriAdiJLabel = new JLabel("M��teri Ad�:", JLabel.RIGHT);
		tamponPanel.add(musteriAdiJLabel);
		final JTextField musteriAdiField = new JTextField(15);
		tamponPanel.add(musteriAdiField);
		JLabel musteriSoyadiJLabel = new JLabel("M��teri Soyad�", JLabel.RIGHT);
		tamponPanel.add(musteriSoyadiJLabel);
		final JTextField musteriSoyadiField = new JTextField(15);
		tamponPanel.add(musteriSoyadiField);
		JLabel ePostaJLabel = new JLabel("E-Posta", JLabel.RIGHT);
		tamponPanel.add(ePostaJLabel);
		final JTextField ePostaField = new JTextField(15);
		tamponPanel.add(ePostaField);
		final MaskFormatter mf1 = new MaskFormatter("+90-(5##)-###-##-##");
		mf1.setPlaceholderCharacter('_');

		JLabel musteriTelLabel = new JLabel("M��teri Telefonu", JLabel.RIGHT);
		tamponPanel.add(musteriTelLabel);
		final JFormattedTextField telField = new JFormattedTextField(mf1);

		tamponPanel.add(telField);

		JLabel dTarihJLabel = new JLabel("Do�um Tarihi", JLabel.RIGHT);
		tamponPanel.add(dTarihJLabel);

		final JTextField dTarihField = new JTextField(15);
		tamponPanel.add(dTarihField);
		JPanel bosPanel = new JPanel(new GridLayout(2, 2));

		tamponPanel.add(bosPanel);
		JPanel radioPanel = new JPanel(new GridLayout(1, 1));
		radioPanel.setBorder(BorderFactory.createTitledBorder(""));

		final String erkek = "Erkek";
		final String kadin = "Kad�n";

		JLabel cinsiyetLabel = new JLabel("Cinsiyet:", JLabel.RIGHT);
		final ButtonGroup group = new ButtonGroup();

		final JRadioButton erkekButton = new JRadioButton(erkek);
		final JRadioButton kadinButton = new JRadioButton(kadin);

		erkekButton.setMnemonic(KeyEvent.VK_B);
		erkekButton.setActionCommand(kadin);
		erkekButton.setSelected(true);

		group.add(erkekButton);
		group.add(kadinButton);

		radioPanel.add(cinsiyetLabel);
		radioPanel.add(erkekButton);
		radioPanel.add(kadinButton);

		tamponPanel.add(radioPanel);

		final JButton bulButton = new JButton("Bul");
		tamponPanel.add(bulButton);
		bulButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				MusteriDomain bulunanMusteri = baglan.bul(Integer
						.parseInt(musteriNoField.getText()));

				musteriAdiField.setText(bulunanMusteri.getAdi());
				musteriSoyadiField.setText(bulunanMusteri.getSoyadi());
				ePostaField.setText(bulunanMusteri.getEPosta());
				telField.setText(bulunanMusteri.getTelefonu());

				dTarihField.setText(bulunanMusteri.getDTarihi());

				musteriNoField.setEditable(false);
				musteriNoField.setBackground(Color.lightGray);
				musteriNoField
						.setToolTipText("M��teri Numaras�n� D�zenleyemezsiniz !");

				if (bulunanMusteri.getCinsiyet().contains(erkek)) {

					erkekButton.setSelected(true);
				} else {
					kadinButton.setSelected(true);
				}

			}

		});

		JButton duzenleButton = new JButton("D�zenle");
		tamponPanel.add(duzenleButton);
		duzenleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				MusteriDomain duzenlenecekKisi = new MusteriDomain();

				duzenlenecekKisi.setMNo(Integer.parseInt(musteriNoField
						.getText()));

				duzenlenecekKisi.setAdi(musteriAdiField.getText());
				duzenlenecekKisi.setSoyadi(musteriSoyadiField.getText());
				duzenlenecekKisi.setTelefonu(telField.getText());
				duzenlenecekKisi.setEPosta(ePostaField.getText());
				duzenlenecekKisi.setDTarihi(dTarihField.getText());

				if (erkekButton.getSelectedObjects() != null) {
					duzenlenecekKisi.setCinsiyet(erkekButton.getText());

				} else {
					duzenlenecekKisi.setCinsiyet(kadinButton.getText());

				}

				baglan.duzenle(duzenlenecekKisi);

			}
		});
		JButton silButton = new JButton("Sil");
		tamponPanel.add(silButton);
		silButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				MusteriDomain silDomain = new MusteriDomain();

				silDomain.setMNo(Integer.parseInt(musteriNoField.getText()));
				baglan.sil(silDomain);

				String[] tamamString = initSil();

				ImageIcon hataIcon = new ImageIcon("images/hata2.png");
				JOptionPane.showMessageDialog(getParent(), tamamString,
						"Silme ��leminiz Ba�ar�l� !",
						getDefaultCloseOperation(), hataIcon);

				musteriNoField.setText("");
				musteriAdiField.setText("");
				musteriSoyadiField.setText("");
				ePostaField.setText("");
				telField.setText("");
				musteriNoField.setEditable(true);
				musteriNoField.setBackground(Color.white);

			}
		});

		JButton iptalButton = new JButton("�ptal");
		tamponPanel.add(iptalButton);
		iptalButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				musteriNoField.setText("");
				musteriAdiField.setText("");
				musteriSoyadiField.setText("");
				ePostaField.setText("");
				telField.setText("");
				dTarihField.setText("");
				musteriNoField.setEditable(true);
				musteriNoField.setBackground(Color.white);
				String[] tamamString = initTemizle();

				ImageIcon hataIcon = new ImageIcon("images/ok.png");
				JOptionPane.showMessageDialog(getParent(), tamamString,
						"T�m Alan Temizlendi", getDefaultCloseOperation(),
						hataIcon);
			}
		});

		musteriPanel.add(tamponPanel);

		return musteriPanel;

	}

	public String[] initJOptString() {

		UIManager.put("OptionPane.okButtonText", "Tamam");

		String JOpHataMsj[] = { "Kay�t D�zenlendi Devam Etmek ��in Tamam'a T�klay�n !" };

		return JOpHataMsj;
	}

	public String[] initTemizle() {

		UIManager.put("OptionPane.okButtonText", "Tamam");

		String JOpHataMsj[] = { "T�m Alan Temizlendi" };

		return JOpHataMsj;
	}

	public String[] initSil() {

		UIManager.put("OptionPane.okButtonText", "Tamam");

		String JOpHataMsj[] = { "Silme ��lemi Ba�ar�l�" };

		return JOpHataMsj;
	}

}
