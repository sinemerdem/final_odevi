package test;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import domain.MusteriDomain;
import domain.PersonelDomain;
import domain.SatisDomain;
import domain.SifreDomain;
import domain.StokDomain;
import domain.UrunDomain;
import kullanici_arayuzu.IslemAlaniGui;
import otomasyon.CreateTableDb;
import otomasyon.DerbyJdbcIleMusteri;
import otomasyon.DerbyJdbcPersonel;
import otomasyon.DerbyJdbcSatisDb;
import otomasyon.DerbyJdbcSifreDb;
import otomasyon.DerbyJdbcStokBaglanti;
import otomasyon.DerbyJdbcUrun;

import javax.swing.UnsupportedLookAndFeelException;

public class Run {

	public static void main(String[] args) {
		

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new IslemAlaniGui();
				new CreateTableDb();
				new DerbyJdbcIleMusteri();
				new MusteriDomain();
				new DerbyJdbcUrun();
				new UrunDomain();
				new DerbyJdbcPersonel();
				new PersonelDomain();
				new DerbyJdbcStokBaglanti();
				new StokDomain();
				new DerbyJdbcSifreDb();
				new SifreDomain();
				new SatisDomain();
				new DerbyJdbcSatisDb();

			}
		}
		);

	}
}
