package kullanici_arayuzu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import otomasyon.DerbyJdbcIleMusteri;
import otomasyon.DerbyJdbcPersonel;
import otomasyon.DerbyJdbcSatisDb;
import otomasyon.DerbyJdbcStokBaglanti;
import otomasyon.DerbyJdbcUrun;

import com.toedter.calendar.JDateChooser;

import domain.MusteriDomain;
import domain.PersonelDomain;
import domain.SatisDomain;
import domain.StokDomain;
import domain.UrunDomain;

public class IslemAlaniGui extends JFrame {
	DerbyJdbcUrun bagDerbyJdbcUrunBaglan = new DerbyJdbcUrun();
	DerbyJdbcUrun baglan = new DerbyJdbcUrun();
	DerbyJdbcIleMusteri baglanDerbyJdbcIleMusterilereBaglan = new DerbyJdbcIleMusteri();
	DerbyJdbcSatisDb baglanti = new DerbyJdbcSatisDb();
	List<UrunDomain> uList = baglan.urunTuruListele();
	DerbyJdbcStokBaglanti baglanStok = new DerbyJdbcStokBaglanti();
	List<StokDomain> sList = baglanStok.StokListele();
	List<UrunDomain> uList2 = baglan.kisiListele();
	StokDomain silDomain = new StokDomain();

	ImageIcon yazdirIcon = new ImageIcon("images/yazdir_icon.png");
	private static final long serialVersionUID = 1L;
	protected static final Component IslemAlaniGui = null;

	public IslemAlaniGui() {
		initPencere();
	}

	public void initPencere() {
		setTitle("Toptancý Otomasyonu v1.0");
		JMenuBar bar = initbar();
		setJMenuBar(bar);
		JTabbedPane anaTabbedPane = initPane();
		add(anaTabbedPane);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JTabbedPane initPane() { 

		JTabbedPane tabbedPane = new JTabbedPane();
		ImageIcon urunIcon = new ImageIcon("images/urun.png");
		JPanel urunHareketiPanel = new JPanel(new BorderLayout());
		urunHareketiPanel.setBorder(BorderFactory.createTitledBorder("Ürün Hareketi"));
		urunHareketiPanel.setBackground(Color.decode("#c8ddf2"));
		final List<UrunDomain> mList2 = bagDerbyJdbcUrunBaglan.kisiListele();
		final DefaultTableModel urunModel = new DefaultTableModel();
		JTable tabloJTable = new JTable(urunModel);
		tabloJTable.setAutoCreateRowSorter(true);
		JScrollPane urunPane = new JScrollPane(tabloJTable);
		urunModel.addColumn("Ürün No");
		urunModel.addColumn("Ürün Adý");
		urunModel.addColumn("Ürün Türü");
		urunModel.addColumn("Ürün Tarihi");
		urunModel.addColumn("Ürün Fiyat");
		for (UrunDomain urun : mList2) {
			Object[] veriler = { urun.getUNo(), urun.getAdi(),
					urun.getUrunTuru(), urun.getUTarih(), urun.getFiyat() };
			urunModel.addRow(veriler);
		}

		tabloJTable.repaint();
		tabloJTable.setAutoCreateRowSorter(true);
		tabloJTable.setPreferredScrollableViewportSize(new Dimension(900, 400));
		tabbedPane.addTab("Ürün Hareketi", urunIcon, urunHareketiPanel);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		urunHareketiPanel.add(urunPane, BorderLayout.CENTER);
		JPanel musteriHareketiPanel = new JPanel(new BorderLayout());
		musteriHareketiPanel.setBorder(BorderFactory.createTitledBorder("Müþteri Hareketi"));
		musteriHareketiPanel.setBackground(Color.decode("#c8ddf2"));
		ImageIcon musterilerIcon = new ImageIcon("images/musteriler.png");
		tabbedPane.addTab("Müþteri Hareketi", musterilerIcon,
				musteriHareketiPanel, "Müþteri Hareketleri");
		
		
		
		List<MusteriDomain> mList = baglanDerbyJdbcIleMusterilereBaglan.listele();
		DefaultTableModel musteriModel = new DefaultTableModel();
		JTable musteriHareketiJTablo = new JTable(musteriModel);
		JScrollPane musteriPane = new JScrollPane(musteriHareketiJTablo);
		musteriModel.addColumn("Müþteri No");
		musteriModel.addColumn("Müþteri Adý");
		musteriModel.addColumn("Müþteri Soyadý");
		musteriModel.addColumn("E-Posta");
		musteriModel.addColumn("Müþteri Telefonu");
		musteriModel.addColumn("Doðum Tarihi");
		for (MusteriDomain musteri : mList) {
			Object[] veriler = { musteri.getMNo(), musteri.getAdi(), musteri.getSoyadi(), musteri.getEPosta(),musteri.getTelefonu(), musteri.getDTarihi() };
			musteriModel.addRow(veriler);
		}
		musteriHareketiJTablo.repaint();
		musteriHareketiJTablo.setAutoCreateRowSorter(true);
		musteriHareketiJTablo.setPreferredScrollableViewportSize(new Dimension(900, 400));
		musteriHareketiPanel.add(musteriPane, BorderLayout.CENTER);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		JPanel kasaJPanel = new JPanel(new BorderLayout());
		kasaJPanel.setBorder(BorderFactory.createTitledBorder(""));
		kasaJPanel.setBackground(Color.decode("#c8ddf2"));
		JPanel kasa2Panel = new JPanel(new GridLayout(3, 2, 2, 2));
		ImageIcon tabResimIcon = new ImageIcon("images/kasa.gif");
		kasa2Panel.setBackground(Color.decode("#c8ddf2"));
		tabbedPane.addTab("Kasa Ýþlemleri", tabResimIcon, kasaJPanel);

		kasaJPanel.add(kasa2Panel);

		JPanel solPanel = new JPanel();
		JPanel solIcPanel = new JPanel(new GridLayout(9, 2, 2, 2));
		solIcPanel.setBackground(Color.decode("#f4f9ca"));
		solPanel.setBackground(Color.decode("#f4f9ca"));

		kasaJPanel.setBackground(Color.decode("#f4f9ca"));
		kasaJPanel.setBorder(BorderFactory.createTitledBorder(""));

		solPanel.setBorder(BorderFactory.createTitledBorder("Stok Ekle"));

		

		JLabel kategoriJLabel = new JLabel("Ürün Adý Çek:", JLabel.RIGHT);
		solIcPanel.add(kategoriJLabel);
		final JComboBox urunAdiBox = new JComboBox();
		urunAdiBox.setBackground(Color.decode("#ff8c8c"));
		urunAdiBox.setEditable(false);
		solIcPanel.add(urunAdiBox);
		JLabel urunTuruJLabel = new JLabel("Ürün Türü:", JLabel.RIGHT);
		solIcPanel.add(urunTuruJLabel);
		final JComboBox urunTuruBox = new JComboBox();
		urunTuruBox.setBackground(Color.decode("#ff8c8c"));
		urunTuruBox.setEditable(false);
		solIcPanel.add(urunTuruBox);

		for (UrunDomain Listele : uList) {

			urunTuruBox.addItem(Listele.getUrunTuru());

		}

		for (UrunDomain List2 : uList2) {

			urunAdiBox.addItem(List2.getAdi());
		}

		JLabel musteriBilgiJLabel = new JLabel("Adet:", JLabel.RIGHT);
		solIcPanel.add(musteriBilgiJLabel);
		final JTextField adetField = new JTextField(10);
		solIcPanel.add(adetField);
		JLabel tarihLabel = new JLabel("Tarih:", JLabel.RIGHT);
		solIcPanel.add(tarihLabel);
		final JDateChooser tarihChooser = new JDateChooser();
		final SimpleDateFormat frmat = new SimpleDateFormat("yyyy/MM/dd");
		

		JButton ekleButton = new JButton("Ekle");
		solIcPanel.add(ekleButton);
		JButton silButton = new JButton("Sil");
		solIcPanel.add(silButton);

		

		String sutunlar[] = { "id", "Adet", "Ürün Adý", "Ürün Türü", "Tarih" };
		final DefaultTableModel stokModel = new DefaultTableModel(sutunlar, 0);
		final JTable table = new JTable(stokModel);
		JScrollPane stokPane = new JScrollPane(table);
		kasaJPanel.add(stokPane);

		List<StokDomain> stokListe = baglanStok.StokListele();
		for (StokDomain stokList : stokListe) {

			stokModel.addRow(stokList.getVeriler());

		}

		ekleButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int satir = stokModel.getRowCount();
				for (int i = 0; i < satir; i++) {
					stokModel.removeRow(0);
				}

				StokDomain yeniStokDomain = new StokDomain();
				String cevirTarih = frmat.format(tarihChooser.getDate());
				yeniStokDomain.setUrunAdi(urunAdiBox.getSelectedItem()
						.toString());
				yeniStokDomain.setUTuru(urunTuruBox.getSelectedItem()
						.toString());
				yeniStokDomain.setAdet(Integer.parseInt(adetField.getText()));
				yeniStokDomain.setTarih(cevirTarih.toString());
				baglanStok.yeniEkle(yeniStokDomain);

				List<StokDomain> listele = baglanStok.StokListele();

				for (StokDomain stokList : listele) {

					stokModel.addRow(stokList.getVeriler());

				}

			}
		});

		silButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				StokDomain kisi = new StokDomain();

				int secilenSatir = table.getSelectedRow();
				int id = (Integer) stokModel.getValueAt(secilenSatir, 0);
				kisi.setId(id);

				baglanStok.sil(kisi);

				int satirSayisi = stokModel.getRowCount();

				for (int i = 0; i < satirSayisi; i++) {
					stokModel.removeRow(0);
				}

				List<StokDomain> listele2 = baglanStok.StokListele();
				for (StokDomain stokList : listele2) {
					stokModel.addRow(stokList.getVeriler());

				}
				System.out.println(id);

			}
		});

		kasaJPanel.add(solPanel, BorderLayout.WEST);
		solPanel.add(solIcPanel, BorderLayout.SOUTH);

	
		JPanel satisAnaPanel = new JPanel(new BorderLayout());
		JPanel sagPanel = new JPanel(new BorderLayout());
		JPanel solIcPanel3 = new JPanel(new GridLayout(15, 3, 2, 2));
		JPanel solPanel3 = new JPanel();
		ImageIcon satisYapIcon = new ImageIcon("images/satisYap.png");
		tabbedPane.addTab("Satýþ Ýþlemleri", satisYapIcon, satisAnaPanel);
		solIcPanel3.setBackground(Color.decode("#c8ddf2"));
		JLabel musteriAdiLabel = new JLabel("Müþteri Adý:", JLabel.RIGHT);
		solIcPanel3.add(musteriAdiLabel);
		final JComboBox musteriAdiBox = new JComboBox();
		musteriAdiBox.setBackground(Color.decode("#ff8c8c"));
		musteriAdiBox.setEditable(false);
		solIcPanel3.add(musteriAdiBox);
		JLabel musteriSoyadiJLabel = new JLabel("Müþteri Soyadý:", JLabel.RIGHT);
		solIcPanel3.add(musteriSoyadiJLabel);
		final JComboBox musteriSoyadiBox = new JComboBox();
		musteriSoyadiBox.setBackground(Color.decode("#ff8c8c"));
		musteriSoyadiBox.setEditable(false);
		solIcPanel3.add(musteriSoyadiBox);
		JLabel personelAdiLabel = new JLabel("Personel Adý:", JLabel.RIGHT);
		solIcPanel3.add(personelAdiLabel);
		final JComboBox personelAdiBox = new JComboBox();
		personelAdiBox.setBackground(Color.decode("#ff8c8c"));
		personelAdiBox.setEditable(false);
		solIcPanel3.add(personelAdiBox);
		JLabel personelSoyadiJLabel = new JLabel("Personel Soyadý:",
				JLabel.RIGHT);
		solIcPanel3.add(personelSoyadiJLabel);
		final JComboBox personelSoyadiBox = new JComboBox();
		personelSoyadiBox.setBackground(Color.decode("#ff8c8c"));
		personelSoyadiBox.setEditable(false);
		solIcPanel3.add(personelSoyadiBox);
		JLabel urunAdiLabel = new JLabel("Ürün Adý:", JLabel.RIGHT);
		solIcPanel3.add(urunAdiLabel);
		final JComboBox urunAdiBox3 = new JComboBox();
		urunAdiBox3.setBackground(Color.decode("#ff8c8c"));
		urunAdiBox3.setEditable(false);
		solIcPanel3.add(urunAdiBox3);
		JLabel urunTuruLabel = new JLabel("Ürün Türü", JLabel.RIGHT);
		solIcPanel3.add(urunTuruLabel);
		final JComboBox urunTuruBox3 = new JComboBox();
		urunTuruBox3.setBackground(Color.decode("#ff8c8c"));
		solIcPanel3.add(urunTuruBox3);
		JLabel urunFiyatLabel = new JLabel("Ürün Fiyatý", JLabel.RIGHT);
		solIcPanel3.add(urunFiyatLabel);
		final JComboBox urunFiyatBox = new JComboBox();
		urunFiyatBox.setBackground(Color.decode("#ff8c8c"));
		urunFiyatBox.setEditable(false);
		solIcPanel3.add(urunFiyatBox);
		JLabel adetJLabel = new JLabel("Adet:", JLabel.RIGHT);
		solIcPanel3.add(adetJLabel);
		final JTextField adetField3 = new JTextField(10);
		solIcPanel3.add(adetField3);
		JLabel dTarihJLabel = new JLabel("Satýþ Tarihi", JLabel.RIGHT);
		solIcPanel3.add(dTarihJLabel);
		final SimpleDateFormat frmat3 = new SimpleDateFormat("yyyy/MM/dd");
		final JDateChooser satisTarihChooser = new JDateChooser();
		solIcPanel3.add(satisTarihChooser);
		DerbyJdbcStokBaglanti baglanStok = new DerbyJdbcStokBaglanti();

		List<StokDomain> sList = baglanStok.urunTuruListele();
		List<StokDomain> uAdiListele = baglanStok.urunAdiListele();

		for (StokDomain Listele : uAdiListele) {
			urunAdiBox3.addItem(Listele.getUrunAdi());
		}
		for (StokDomain Listele : sList) {
			urunTuruBox3.addItem(Listele.getUTuru());
		}

		for (MusteriDomain Listele : mList) {
			musteriAdiBox.addItem(Listele.getAdi());
			musteriSoyadiBox.addItem(Listele.getSoyadi());
		}

		DerbyJdbcPersonel baglantiJdbcPersonel = new DerbyJdbcPersonel();
		List<PersonelDomain> pList = baglantiJdbcPersonel.listele();

		for (PersonelDomain Listele : pList) {
			personelAdiBox.addItem(Listele.getAdi());
			personelSoyadiBox.addItem(Listele.getSoyadi());
		}
		for (UrunDomain urun : mList2) {
			urunFiyatBox.addItem(urun.getFiyat());
		}

		JButton satisYapButton = new JButton("Satýþ Yap");
		solIcPanel3.add(satisYapButton);
		JButton temizleButton = new JButton("Temizle");
		solIcPanel3.add(temizleButton);
		String basliklar[] = { "id", "Müþ.Adý", "Müþ.Soy.", "Per Adý",
				"Per.Soyadý", "Ürün Adý", "Ürün Türü", "Ürün Fiy.", "Adet",
				"Satýþ Tar." };
		final DefaultTableModel satisModel = new DefaultTableModel(basliklar, 0);
		final JTable satisYapJTable = new JTable(satisModel);
		satisYapJTable.setPreferredScrollableViewportSize(new Dimension(600,
				500));
		JScrollPane satisListelePane = new JScrollPane(satisYapJTable);
		List<SatisDomain> listele = baglanti.listele();
		for (SatisDomain stokList : listele) {
			satisModel.addRow(stokList.getVeriler());
		}
		satisYapButton.addActionListener(new ActionListener() {
			DerbyJdbcStokBaglanti stokDus = new DerbyJdbcStokBaglanti();

			public void actionPerformed(ActionEvent arg0) {
				SatisDomain yeniSatisDomain = new SatisDomain();
				StokDomain dusDomain = new StokDomain();

				String cevirTarih = frmat3.format(satisTarihChooser.getDate());
				int adet1 = Integer.parseInt(adetField3.getText().toString());
				int satir = satisModel.getRowCount();
				for (int i = 0; i < satir; i++) {
					satisModel.removeRow(0);
				}

				yeniSatisDomain.setMAdi(musteriAdiBox.getSelectedItem().toString());
				yeniSatisDomain.setMSoyadi(musteriSoyadiBox.getSelectedItem().toString());
				yeniSatisDomain.setPAdi(personelAdiBox.getSelectedItem().toString());
				yeniSatisDomain.setPSoyadi(personelSoyadiBox.getSelectedItem().toString());
				yeniSatisDomain.setUAdi(urunAdiBox3.getSelectedItem().toString());
				yeniSatisDomain.setUTuru(urunTuruBox3.getSelectedItem().toString());
				yeniSatisDomain.setUFiyat(Double.parseDouble(urunFiyatBox.getSelectedItem().toString()));
				yeniSatisDomain.setAdet(Integer.parseInt(adetField3.getText()));
				yeniSatisDomain.setSatisTarihi(cevirTarih.toString());
				baglanti.yeniKayit(yeniSatisDomain);
				int id = 0;
				dusDomain.setUrunAdi(urunAdiBox3.getSelectedItem().toString());
				dusDomain.setTarih(cevirTarih.toString());
				dusDomain.setUTuru(urunTuruBox3.getSelectedItem().toString());
				dusDomain.setAdet(-adet1);
				dusDomain.setId(id);

				stokDus.yeniEkle(dusDomain);

				List<SatisDomain> listele = baglanti.listele();
				for (SatisDomain stokList : listele) {
					satisModel.addRow(stokList.getVeriler());

				}

			}
		});
		JButton satisRaporlaButton = new JButton("Satýþ Raporla");
		solIcPanel3.add(satisRaporlaButton);

		satisRaporlaButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				DerbyJdbcSatisDb satisRaporDb = new DerbyJdbcSatisDb();
				Connection baglanti = satisRaporDb.getConnection();
				try {
					JasperPrint print = JasperFillManager.fillReport(
							"SatisListesi.jasper", null, baglanti);
					JasperViewer.viewReport(print, false);
				} catch (JRException e) {
					
					e.printStackTrace();
				}

			}
		});
		JButton stokToplamButton = new JButton("Stok Toplam");
		solIcPanel3.add(stokToplamButton);
		JButton kasaButton = new JButton("Kasa Hareketi");
		solIcPanel3.add(kasaButton);

		stokToplamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DerbyJdbcStokBaglanti stokToplamDb = new DerbyJdbcStokBaglanti();

				Connection baglanti = stokToplamDb.getConnection();

				try {
					JasperPrint print = JasperFillManager.fillReport(
							"stokToplam.jasper", null, baglanti);
					JasperViewer.viewReport(print, false);
				} catch (JRException e1) {
					
					e1.printStackTrace();
				}

			}
		});
		stokToplamButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}
		});

		kasaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DerbyJdbcSatisDb satisDb = new DerbyJdbcSatisDb();
				Connection baglanti = satisDb.getConnection();

				try {
					JasperPrint print = JasperFillManager.fillReport(
							"Toplam.jasper", null, baglanti);
					JasperViewer.viewReport(print, false);
				} catch (JRException e1) {
					
					e1.printStackTrace();
				}

			}
		});

		JButton satisSilButton = new JButton("Satýþ Sil");
		solIcPanel3.add(satisSilButton);
		satisSilButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SatisDomain kisi = new SatisDomain();

				int secilenSatir = satisYapJTable.getSelectedRow();
				int id = (Integer) satisModel.getValueAt(secilenSatir, 0);
				kisi.setId(id);

				baglanti.sil(kisi);

				int satirSayisi = satisModel.getRowCount();

				for (int i = 0; i < satirSayisi; i++) {
					satisModel.removeRow(0);
				}

				List<SatisDomain> listele2 = baglanti.listele();
				for (SatisDomain stokList : listele2) {
					satisModel.addRow(stokList.getVeriler());

				}
				System.out.println(id);

			}
		});

		sagPanel.add(satisListelePane, BorderLayout.CENTER);
		sagPanel.setBorder(BorderFactory.createTitledBorder(""));
		satisAnaPanel.add(sagPanel, BorderLayout.EAST);
		satisAnaPanel.add(solPanel3, BorderLayout.WEST);
		solPanel3.add(solIcPanel3);
		solPanel3.setBackground(Color.decode("#c8ddf2"));
		satisAnaPanel.setBackground(Color.decode("#c8ddf2"));
		sagPanel.setBackground(Color.decode("#c8ddf2"));
		solIcPanel3.setBackground(Color.decode("#c8ddf2"));
		solIcPanel3.setBorder(BorderFactory.createTitledBorder("Satýþ Yap"));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		return tabbedPane;
	}

	public JMenuBar initbar() {
		JMenuBar bar = new JMenuBar();

		JMenu dosyaJMenu = new JMenu("Dosya");
		JMenu urunJMenu = new JMenu("Ürün");
		JMenu musteriJMenu = new JMenu("Müþteri");
		ImageIcon musterilerIcon = new ImageIcon("images/musteriler.png");
		bar.add(dosyaJMenu);
		bar.add(urunJMenu);
		bar.add(musteriJMenu);

		JMenuItem zRaporuItem = new JMenuItem("Rapor Al", yazdirIcon);
		dosyaJMenu.add(zRaporuItem);
		dosyaJMenu.addSeparator();
		ImageIcon hataIcon = new ImageIcon("images/hata.png");
		JMenuItem cikisItem = new JMenuItem("Çýkýþ", hataIcon);
		dosyaJMenu.add(cikisItem);
		cikisItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int messageType = JOptionPane.QUESTION_MESSAGE;
				String[] options = { "Evet", "Hayýr" };
				int code = JOptionPane.showOptionDialog(IslemAlaniGui,
						"Çýkýþ Yapmak Ýstiyormusunuz ?",
						"Çýkýþ Yapmak Ýstiyormusunuz ?", 0, messageType, null,
						options, "Çýk");

				if (code == 0) {
					System.exit(0);

				}

			}
		});
		ImageIcon urunIcon = new ImageIcon("images/urun.png");
		JMenuItem urunEkleItem = new JMenuItem("Ürün Ekle", urunIcon);
		urunJMenu.add(urunEkleItem);
		urunEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							new UrunEkleGui();
						} catch (ParseException e) {
							
							e.printStackTrace();
						}

					}
				});

			}
		});
		ImageIcon listeleIcon = new ImageIcon("images/listele.png");
		JMenuItem urunListeleItem = new JMenuItem("Ürün Listele", listeleIcon);
		urunJMenu.add(urunListeleItem);
		urunListeleItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UrunListeleGui urun = new UrunListeleGui();
				urun.listele();

			}
		});
		ImageIcon araIcon = new ImageIcon("images/ara.png");
		JMenuItem urunBulItem = new JMenuItem("Ara Sil Düzenle", araIcon);
		urunJMenu.addSeparator();
		urunJMenu.add(urunBulItem);
		urunBulItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UrunBulSilDuzenleGui bul = null;
				try {
					bul = new UrunBulSilDuzenleGui();
				} catch (ParseException e2) {

					e2.printStackTrace();
				}
				try {
					bul.initAraPanel();
				} catch (ParseException e1) {

					e1.printStackTrace();
				}

			}
		});

		JMenu personelJMenu = new JMenu("Personel");
		bar.add(personelJMenu);
		ImageIcon personelEkleIcon = new ImageIcon("images/persoenEkle.png");
		JMenuItem personelEklItem = new JMenuItem("Personel Ekle",
				personelEkleIcon);
		personelJMenu.add(personelEklItem);
		personelEklItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PersonelEkleGui();
				} catch (ParseException e1) {

					e1.printStackTrace();
				}

			}
		});
		JMenuItem personelListeleItem = new JMenuItem("Personel Listele",
				listeleIcon);
		personelJMenu.add(personelListeleItem);
		personelListeleItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PersonelListeleGui listele = new PersonelListeleGui();
				listele.list();

			}
		});
		personelJMenu.addSeparator();
		JMenuItem personelAraItem = new JMenuItem("Ara Sil Düzenle", araIcon);
		personelJMenu.add(personelAraItem);
		personelAraItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new PersonelAraSilDuzenleGui();
				} catch (ParseException e) {

					e.printStackTrace();
				}

			}
		});

		JMenuItem musteriEkleItem = new JMenuItem("Müþteri Ekle",
				musterilerIcon);
		musteriJMenu.add(musteriEkleItem);
		musteriEkleItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MusteriEkleGui();

			}
		});

		JMenuItem musteriListeleItem = new JMenuItem("Müþteri Listele",
				listeleIcon);
		musteriJMenu.add(musteriListeleItem);
		musteriListeleItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MusteriListeleGui listele = new MusteriListeleGui();
				listele.list();

			}
		});
		JMenuItem musteriBulItem = new JMenuItem("Ara Sil Düzenle", araIcon);
		musteriJMenu.addSeparator();
		musteriJMenu.add(musteriBulItem);
		musteriBulItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new MusteriBulSilDuzenleGui();
				} catch (ParseException e) {

					e.printStackTrace();
				}

			}
		});

		JMenu raporJMenu = new JMenu("Raporlar");
		bar.add(raporJMenu);
		JMenuItem musteriRaporuItem = new JMenuItem("Müþteri Raporu");
		raporJMenu.add(musteriRaporuItem);
		musteriRaporuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				DerbyJdbcIleMusteri musteriDerbyJdbcIleMusteri = new DerbyJdbcIleMusteri();
				Connection baglanti = musteriDerbyJdbcIleMusteri
						.getConnection();
				try {
					JasperPrint print = JasperFillManager.fillReport(
							"MusteriListesi.jasper", null, baglanti);
					JasperViewer.viewReport(print, false);
				} catch (JRException e) {
					
					e.printStackTrace();
				}

			}
		});

		return bar;

	}

	
	public String[] initString() {
		UIManager.put("OptionPane.okButtonText", "Tamam");
		String JOpHataMsj[] = { "Eriþim Þifrenizi Hatalý Girdiniz !" };
		return JOpHataMsj;

	}
}
