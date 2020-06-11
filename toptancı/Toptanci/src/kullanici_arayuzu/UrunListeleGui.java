package kullanici_arayuzu;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domain.UrunDomain;
import otomasyon.DerbyJdbcUrun;

public class UrunListeleGui extends JDialog {

	private static final long serialVersionUID = 1L;

	public void listele() {
		JPanel panelListe = initPanel();

		add(panelListe);
		setTitle("Listele");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setResizable(true);
		setVisible(true);
		
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

	}

	private JPanel initPanel() {
		JPanel panelListe = new JPanel();
		panelListe.setBorder(BorderFactory.createTitledBorder("Ürün Listesi"));

		DerbyJdbcUrun baglan = new DerbyJdbcUrun();
		List<UrunDomain> uList = baglan.kisiListele();

		DefaultTableModel urunModel = new DefaultTableModel();
		JTable tabloJTable = new JTable(urunModel);
		tabloJTable.setAutoCreateRowSorter(true);
		JScrollPane pane = new JScrollPane(tabloJTable);
		urunModel.addColumn("Ürün No");
		urunModel.addColumn("Ürün Adý");
		urunModel.addColumn("Ürün Türü");
		urunModel.addColumn("Ürün Tarih");
		urunModel.addColumn("Ürün Fiyat");

		for (UrunDomain urun : uList) {
			urunModel.addRow(urun.getVeriler());
		}
		panelListe.add(pane, BorderLayout.CENTER);

		return panelListe;

	}

}
