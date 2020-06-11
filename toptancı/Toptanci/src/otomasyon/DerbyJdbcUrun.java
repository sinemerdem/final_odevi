package otomasyon;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.MusteriDomain;
import domain.UrunDomain;

public class DerbyJdbcUrun {

	private final String yol = "jdbc:derby:otomasyonProgrami;create=true";
	private final String kullanici = "";
	private final String sifre = "";

	public DerbyJdbcUrun() {

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

	private Connection getConnection() {
		Connection baglanti = null;
		try {
			baglanti = DriverManager.getConnection(yol, kullanici, sifre);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return baglanti;

	}

	public void urunKayit(UrunDomain yeniUrun) {
		Connection baglanti = getConnection();

		try {
			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("INSERT INTO urun (uAdi, uTuru, uTarihi, uFiyat) VALUES ('" + yeniUrun.getAdi() + "','" + yeniUrun.getUrunTuru() + "','" + yeniUrun.getUTarih() + "'," + yeniUrun.getFiyat() + ")");

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	public List<UrunDomain> kisiListele() {

		List<UrunDomain> liste = new ArrayList<UrunDomain>();

		Connection baglanti = getConnection();

		try {

			UrunDomain yeniKisi = null;
			Statement sorgu = baglanti.createStatement();

			ResultSet rs = sorgu
					.executeQuery("SELECT * FROM urun ORDER BY uAdi");

			while (rs.next()) {
				yeniKisi = new UrunDomain();

				yeniKisi.setUNo(rs.getInt("uNo"));
				yeniKisi.setAdi(rs.getString("uAdi"));
				yeniKisi.setUTuru(rs.getString("uTuru"));
				yeniKisi.setUTarih(rs.getString("uTarihi"));
				yeniKisi.setFiyat(rs.getDouble("uFiyat"));

				liste.add(yeniKisi);

			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		return liste;

	}

	public UrunDomain bul(int uNo) {
		UrunDomain bulunacakKisi = new UrunDomain();

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM urun WHERE uNo = "
					+ uNo + "");

			while (rs.next()) {
				bulunacakKisi.setUNo(rs.getInt("uNo"));
				bulunacakKisi.setAdi(rs.getString("uAdi"));
				bulunacakKisi.setUTuru(rs.getString("uTuru"));
				bulunacakKisi.setUTarih(rs.getString("uTarihi"));
				bulunacakKisi.setFiyat(rs.getDouble("uFiyat"));

			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return bulunacakKisi;

	}

	public CreateTableDb initOlustur() {

		CreateTableDb tabloyaBaglan = new CreateTableDb();

		return tabloyaBaglan;

	}

	public void sil(UrunDomain silDomain) {

		Connection baglanti = getConnection();

		try {
			Statement sorgu = baglanti.createStatement();
			sorgu.execute("DELETE FROM urun WHERE uNo = " + silDomain.getUNo()
					+ "");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	public List<UrunDomain> urunTuruListele() {

		List<UrunDomain> liste = new ArrayList<UrunDomain>();

		Connection baglanti = getConnection();

		try {

			UrunDomain yeniKisi = null;
			Statement sorgu = baglanti.createStatement();

			ResultSet rs = sorgu
					.executeQuery("SELECT DISTINCT uTuru FROM urun");

			while (rs.next()) {
				yeniKisi = new UrunDomain();

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

	public List<UrunDomain> urunAdiListele() {

		List<UrunDomain> liste = new ArrayList<UrunDomain>();

		Connection baglanti = getConnection();

		try {

			UrunDomain yeniKisi = null;
			Statement sorgu = baglanti.createStatement();

			ResultSet rs = sorgu.executeQuery("SELECT DISTINCT uAdi FROM stok");

			while (rs.next()) {
				yeniKisi = new UrunDomain();

				yeniKisi.setUTuru(rs.getString("uAdi"));

				liste.add(yeniKisi);

			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return liste;

	}

	public List<UrunDomain> tariheGoreBul(String Liste) {
		List<UrunDomain> liste = new ArrayList<UrunDomain>();

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu
					.executeQuery("SELECT uNo, uAdi, uTuru, uFiyat FROM urun WHERE uTarihi ='"
							+ Liste + "'");
			UrunDomain bulunacakKisi = null;

			while (rs.next()) {
				bulunacakKisi = new UrunDomain();
				bulunacakKisi.setUNo(rs.getInt("uNo"));
				bulunacakKisi.setAdi(rs.getString("uAdi"));
				bulunacakKisi.setUTuru(rs.getString("uTuru"));
				bulunacakKisi.setUTarih(rs.getString("uTarihi"));
				bulunacakKisi.setFiyat(rs.getDouble("uFiyat"));

			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return liste;

	}

	public void duzenle(UrunDomain duzenlenecekKisi) {

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("UPDATE urun SET uAdi='" + duzenlenecekKisi.getAdi() + "',uTuru='" + duzenlenecekKisi.getUrunTuru() + "',uTarihi='" + duzenlenecekKisi.getUTarih() + ",uFiyat=" + duzenlenecekKisi.getFiyat() + "'WHERE uNo=" + duzenlenecekKisi.getUNo() + "");

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
