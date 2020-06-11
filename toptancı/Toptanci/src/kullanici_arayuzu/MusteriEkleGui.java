package kullanici_arayuzu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import otomasyon.DerbyJdbcIleMusteri;

public class MusteriEkleGui extends JDialog {

	private static final long serialVersionUID = 1L;

	public MusteriEkleGui() {

		initPencere();

	}

	public void initPencere() {

		JPanel musteriPanel = null;
		try {
			musteriPanel = initPanel();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}

		add(musteriPanel);
		setTitle("Müþteri Ekle");

		setModalityType(DEFAULT_MODALITY_TYPE);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);

		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

	}

	private JPanel initPanel() throws ParseException {
		JPanel musteriPanel = new JPanel();
		JPanel tamponPanel = new JPanel(new GridLayout(9, 3, 3, 3));
		tamponPanel.setBorder(BorderFactory.createTitledBorder("Müþteri Ekle"));

		JLabel musteriAdiJLabel = new JLabel("Müþteri Adý:", JLabel.RIGHT);
		tamponPanel.add(musteriAdiJLabel);
		final JTextField musteriAdiField = new JTextField(15);
		tamponPanel.add(musteriAdiField);
		JLabel musteriSoyadiJLabel = new JLabel("Müþteri Soyadý", JLabel.RIGHT);
		tamponPanel.add(musteriSoyadiJLabel);
		final JTextField musteriSoyadiField = new JTextField(15);
		tamponPanel.add(musteriSoyadiField);
		JLabel ePostaJLabel = new JLabel("E-Posta", JLabel.RIGHT);
		tamponPanel.add(ePostaJLabel);
		final JTextField ePostaField = new JTextField(15);
		tamponPanel.add(ePostaField);

		final MaskFormatter mf1 = new MaskFormatter("+90-(5##)-###-##-##");
		mf1.setPlaceholderCharacter('_');

		JLabel musteriTelLabel = new JLabel("Müþteri Telefonu", JLabel.RIGHT);

		final JFormattedTextField telField = new JFormattedTextField(mf1);
		mf1.setPlaceholderCharacter('_');
		tamponPanel.add(musteriTelLabel);
		tamponPanel.add(telField);

		JLabel dTarihJLabel = new JLabel("Doðum Tarihi", JLabel.RIGHT);
		tamponPanel.add(dTarihJLabel);

		final JDateChooser dTarih = new JDateChooser();
		final SimpleDateFormat frmat = new SimpleDateFormat("yyyy/MM/dd");

		tamponPanel.add(dTarih);
		JPanel bosPanel = new JPanel(new GridLayout(2, 2));

		tamponPanel.add(bosPanel);
		JPanel radioPanel = new JPanel(new GridLayout(1, 1));
		radioPanel.setBorder(BorderFactory.createTitledBorder(""));

		final String erkek = "Erkek";
		final String kadin = "Kadýn";

		JLabel cinsiyetLabel = new JLabel("Cinsiyet:", JLabel.RIGHT);
		final ButtonGroup CinsiyetlerGroup = new ButtonGroup();

		final JRadioButton erkekButton = new JRadioButton(erkek);
		final JRadioButton kadinButton = new JRadioButton(kadin);

		erkekButton.setMnemonic(KeyEvent.VK_B);
		erkekButton.setActionCommand(kadin);
		erkekButton.setSelected(true);

		CinsiyetlerGroup.add(erkekButton);
		CinsiyetlerGroup.add(kadinButton);

		radioPanel.add(cinsiyetLabel);
		radioPanel.add(erkekButton);
		radioPanel.add(kadinButton);

		tamponPanel.add(radioPanel);

		JButton kaydetButton = new JButton("Kaydet");
		tamponPanel.add(kaydetButton);
		kaydetButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				DerbyJdbcIleMusteri baglan = new DerbyJdbcIleMusteri();
				MusteriDomain ekleDomain = new MusteriDomain();
				String cevirDtarihi = frmat.format(dTarih.getDate());

				ekleDomain.setAdi(musteriAdiField.getText());
				ekleDomain.setSoyadi(musteriSoyadiField.getText());
				ekleDomain.setEPosta(ePostaField.getText());
				ekleDomain.setTelefonu(telField.getText());
				ekleDomain.setDTarihi(cevirDtarihi.toString());
				
				if (erkekButton.getSelectedObjects() != null) {
					ekleDomain.setCinsiyet(erkekButton.getText());
				} else {
					ekleDomain.setCinsiyet(kadinButton.getText());

				}

				baglan.yeniKayit(ekleDomain);

				String[] tamamString = initJOptString();

				ImageIcon hataIcon = new ImageIcon("images/ok.png");

				JOptionPane.showMessageDialog(getParent(), tamamString,
						"Eriþim Þifrenizi Hatalý Girdiniz !",getDefaultCloseOperation(), hataIcon);

			}
		});

		JButton iptalButton = new JButton("Ýptal");
		tamponPanel.add(iptalButton);
		musteriPanel.add(tamponPanel);

		return musteriPanel;

	}

	public String[] initJOptString() {

		UIManager.put("OptionPane.okButtonText", "Tamam");

		String JOpHataMsj[] = { "Kayýt Baþarýlý ! Devam Etmek Ýçin Tamam'a Basýn" };

		return JOpHataMsj;
	}

}
