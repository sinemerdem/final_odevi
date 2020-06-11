package kullanici_arayuzu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domain.MusteriDomain;
import otomasyon.DerbyJdbcIleMusteri;


public class MusteriListeleGui extends JDialog {

	private static final long serialVersionUID = 1L;

	public void list() {

		JPanel panelListe = initPanel();

		add(panelListe);

		setTitle("Personel Listele");
		setModalityType(DEFAULT_MODALITY_TYPE);
		setSize(900, 525);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);

		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

	}

	private JPanel initPanel() {

		JPanel panelListe = new JPanel();
		JScrollPane personelPane = initJScrollPane();
		panelListe.setBorder(BorderFactory.createTitledBorder("Müþteri Listesi"));
		panelListe.add(personelPane, BorderLayout.CENTER);
		return panelListe;
	}

	private JScrollPane initJScrollPane() {
		
		DerbyJdbcIleMusteri baglan = new DerbyJdbcIleMusteri();
		List<MusteriDomain> mList = baglan.listele();
		
		String basliklar[] = { "Musteri No", "Müþteri Adý", "Müþteri Soyadý", "E-Posta",
		"Müþteri Telefonu","D.Tarihi","Cinsiyet" };
		DefaultTableModel model = new DefaultTableModel(basliklar, 0);
		JTable musterilerJTable = new JTable(model);
		JScrollPane pane = new JScrollPane(musterilerJTable);

		
		for (MusteriDomain musteri : mList) {
			model.addRow(musteri.getVeriler());
		}
		
		musterilerJTable.setPreferredScrollableViewportSize(new Dimension(850,400));

		return pane;
	}

}
