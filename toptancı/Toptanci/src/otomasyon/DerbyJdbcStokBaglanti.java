package otomasyon;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import domain.MusteriDomain;
import domain.StokDomain;


public class DerbyJdbcStokBaglanti {

	private final String yol = "jdbc:derby:otomasyonProgrami;create=true";
	private final String kullanici = "";
	private final String sifre = "";

	public DerbyJdbcStokBaglanti() {
		initOlustur();

	}

	static {
		String ev, sistem;
		ev = System.getProperty("user.home", ".");
		sistem = ev + File.separatorChar + ".veriTabani";
		System.setProperty("derby.system.home", sistem);

		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

	}

	public Connection getConnection() {

		Connection baglanti = null;

		try {
			baglanti = DriverManager.getConnection(yol, kullanici, sifre);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return baglanti;

	}

	public void yeniEkle(StokDomain ekleDomain) {

		Connection baglanti = getConnection();

		try {
			Statement sorgu = baglanti.createStatement();
			sorgu.executeUpdate("INSERT INTO stok (uAdi, uTuru, tarih, adet) VALUES ('" + ekleDomain.getUrunAdi() + "','" + ekleDomain.getUTuru() + "','" + ekleDomain.getTarih() + "'," + ekleDomain.getAdet() + ")");

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

	}

	public List<StokDomain> StokListele() {

		Connection baglanti = getConnection();

		List<StokDomain> stkList = new ArrayList<StokDomain>();

		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM stok");

			while (rs.next()) {

				StokDomain yeniStok = new StokDomain();

				yeniStok.setId(rs.getInt("id"));
				yeniStok.setUrunAdi(rs.getString("uAdi"));
				yeniStok.setUTuru(rs.getString("uTuru"));
				yeniStok.setAdet(rs.getInt("adet"));
				yeniStok.setTarih(rs.getString("tarih"));
				stkList.add(yeniStok);

			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return stkList;

	}

	public List<StokDomain> kisiIsmeGoreBul(String isim) {
		List<StokDomain> liste = new ArrayList<StokDomain>();

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM stok WHERE uAdi LIKE '"+isim+"%'");

			while (rs.next()) {
				StokDomain yeniKisiDomain = new StokDomain();
				yeniKisiDomain.setId(rs.getInt("id"));
				yeniKisiDomain.setUrunAdi(rs.getString("uAdi"));
				yeniKisiDomain.setUTuru(rs.getString("uTuru"));
				yeniKisiDomain.setTarih(rs.getString("tarih"));
				yeniKisiDomain.setAdet(rs.getInt("adet"));

				liste.add(yeniKisiDomain);

			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			
		}

		return liste;
	}
	

	public void sil(StokDomain silStokDomain) {

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("DELETE FROM stok WHERE id = "
					+ silStokDomain.getId() + "");

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<StokDomain> urunTuruListele() {

		List<StokDomain> liste = new ArrayList<StokDomain>();

		Connection baglanti = getConnection();

		try {

			StokDomain yeniKisi = null;
			Statement sorgu = baglanti.createStatement();

			ResultSet rs = sorgu
					.executeQuery("SELECT DISTINCT uTuru FROM stok");

			while (rs.next()) {
				yeniKisi = new StokDomain();

				yeniKisi.setUTuru(rs.getString("uTuru"));
				

				liste.add(yeniKisi);

			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return liste;

	}
	
	public List<StokDomain> urunAdiListele() {

		List<StokDomain> liste = new ArrayList<StokDomain>();

		Connection baglanti = getConnection();

		try {

			StokDomain yeniKisi = null;
			Statement sorgu = baglanti.createStatement();

			ResultSet rs = sorgu
					.executeQuery("SELECT DISTINCT uAdi FROM stok");

			while (rs.next()) {
				yeniKisi = new StokDomain();

				yeniKisi.setUrunAdi(rs.getString("uAdi"));
				

				liste.add(yeniKisi);

			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return liste;

	}
	
	
	
	

	public CreateTableDb initOlustur() {

		CreateTableDb baglanti = new CreateTableDb();

		return baglanti;

	}

}
