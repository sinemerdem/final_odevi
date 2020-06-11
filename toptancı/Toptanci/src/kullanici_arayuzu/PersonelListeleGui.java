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

import domain.PersonelDomain;
import otomasyon.DerbyJdbcPersonel;

public class PersonelListeleGui extends JDialog {

	
	private static final long serialVersionUID = 1L;

	public void list() {

		JPanel panelListe = initPanel();

		add(panelListe);
		setTitle("Personel Listele");
		setSize(900, 525);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

	}

	private JPanel initPanel() {

		JPanel panelListe = new JPanel();
		JScrollPane personelPane = initJScrollPane();
		panelListe.setBorder(BorderFactory.createTitledBorder("Personel Listesi"));
		panelListe.add(personelPane, BorderLayout.CENTER);
		return panelListe;
	}

	private JScrollPane initJScrollPane() {
		DerbyJdbcPersonel baglanti = new DerbyJdbcPersonel();
		List<PersonelDomain> pList = baglanti.listele();
		
		DefaultTableModel personelModel = new DefaultTableModel();
		JTable musteriHareketi = new JTable(personelModel);
		musteriHareketi.setAutoCreateRowSorter(true);
		JScrollPane personelPane = new JScrollPane(musteriHareketi);
		
		personelModel.addColumn("Personelin No");
		personelModel.addColumn("Personelin Adý");
		personelModel.addColumn("Personelin Soyadý");
		personelModel.addColumn("Personelin Görevi");
		personelModel.addColumn("Personelin Telefon");
		
		for(PersonelDomain perList: pList){
			personelModel.addRow(perList.getVeriler());
			
		}
		musteriHareketi.setPreferredScrollableViewportSize(new Dimension(850, 400));
		
 		return personelPane;
	}

}
