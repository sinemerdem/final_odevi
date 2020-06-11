package otomasyon;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.SatisDomain;

public class DerbyJdbcSatisDb {
	private final String yol = "jdbc:derby:otomasyonProgrami;create=true";
	private final String kullanici = "";
	private final String sifre = "";

	public DerbyJdbcSatisDb() {

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

	public void yeniKayit(SatisDomain yeniSatis) {
		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("INSERT INTO satis " + "(mAdi, mSoyadi, pAdi, pSoyadi, uAdi, uTuru, uFiyat, adet, satisTarihi) VALUES ('" + yeniSatis.getMAdi() + "','" + yeniSatis.getMSoyadi() + "','" + yeniSatis.getPAdi() + "','" + yeniSatis.getPSoyadi() + "','" + yeniSatis.getUAdi() + "','" + yeniSatis.getUTuru() + "'," + yeniSatis.getUFiyat() + "," + yeniSatis.getAdet() + ",'" + yeniSatis.getSatisTarihi() + "')");
			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<SatisDomain> listele() {

		Connection baglanti = getConnection();
		SatisDomain yeniDomain;
		List<SatisDomain> satisList = new ArrayList<SatisDomain>();

		try {
			Statement sorgu = baglanti.createStatement();

			ResultSet rs = sorgu.executeQuery("SELECT * FROM satis");

			while (rs.next()) {
				yeniDomain = new SatisDomain();

				yeniDomain.setId(rs.getInt("id"));
				yeniDomain.setMAdi(rs.getString("mAdi"));
				yeniDomain.setMSoyadi(rs.getString("mSoyadi"));
				yeniDomain.setPAdi(rs.getString("pAdi"));
				yeniDomain.setPSoyadi(rs.getString("pSoyadi"));
				yeniDomain.setUAdi(rs.getString("uAdi"));
				yeniDomain.setUTuru(rs.getString("uTuru"));
				yeniDomain.setUFiyat(rs.getInt("uFiyat"));
				yeniDomain.setAdet(rs.getInt("adet"));
				yeniDomain.setSatisTarihi(rs.getString("satisTarihi"));

				satisList.add(yeniDomain);

			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return satisList;
	}

	public List<SatisDomain> tarihListeleme() {

		Connection baglanti = getConnection();
		SatisDomain yeniDomain;
		yeniDomain = new SatisDomain();
		List<SatisDomain> satisList = new ArrayList<SatisDomain>();

		try {
			Statement sorgu = baglanti.createStatement();

			ResultSet rs = sorgu.executeQuery("SELECT id, mAdi, mSoyadi, pAdi, pSoyadi, uAdi, uTuru, uFiyat, adet FROM satis where satisTarihi = '" + yeniDomain.getSatisTarihi() + "'");

			while (rs.next()) {

				yeniDomain.setId(rs.getInt("id"));
				yeniDomain.setMAdi(rs.getString("mAdi"));
				yeniDomain.setMSoyadi(rs.getString("mSoyadi"));
				yeniDomain.setPAdi(rs.getString("pAdi"));
				yeniDomain.setPSoyadi(rs.getString("pSoyadi"));
				yeniDomain.setUAdi(rs.getString("uAdi"));
				yeniDomain.setUTuru(rs.getString("uTuru"));
				yeniDomain.setUFiyat(rs.getInt("uFiyat"));
				yeniDomain.setAdet(rs.getInt("adet"));
				yeniDomain.setSatisTarihi(rs.getString("satisTarihi"));

				satisList.add(yeniDomain);

			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return satisList;
	}

	public SatisDomain bul(int id) {
		SatisDomain bulunacakSatis = new SatisDomain();

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM satis WHERE id = " + id + "");

			while (rs.next()) {

				bulunacakSatis.setId(rs.getInt("id"));
				bulunacakSatis.setMAdi(rs.getString("mAdi"));
				bulunacakSatis.setMSoyadi(rs.getString("mSoyadi"));
				bulunacakSatis.setPAdi(rs.getString("pAdi"));
				bulunacakSatis.setPSoyadi(rs.getString("pSoyadi"));
				bulunacakSatis.setUAdi(rs.getString("uAdi"));
				bulunacakSatis.setUTuru(rs.getString("uTuru"));
				bulunacakSatis.setUFiyat(rs.getInt("uFiyat"));
				bulunacakSatis.setAdet(rs.getInt("adet"));
				bulunacakSatis.setSatisTarihi(rs.getString("satisTarihi"));

			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			System.out.println(e);
		}

		return bulunacakSatis;

	}

	public void sil(SatisDomain silinecekSatis) {

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("DELETE FROM satis WHERE id = " + silinecekSatis.getId() + "");

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void duzenle(SatisDomain duzenlenecekKisi) {

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("UPDATE satis SET mAdi = '" + duzenlenecekKisi.getMAdi() + "', mSoyadi = '" + duzenlenecekKisi.getMSoyadi() + "', pAdi = " + duzenlenecekKisi.getPAdi() + "' , pSoyadi = '" + duzenlenecekKisi.getPSoyadi() + "', uAdi = '" + duzenlenecekKisi.getUAdi() + "', uTuru ='" + duzenlenecekKisi.getUTuru() + "', uFiyat = " + duzenlenecekKisi.getUFiyat() + ", adet =" + duzenlenecekKisi.getAdet() + ", satisTarihi = '" + duzenlenecekKisi.getSatisTarihi() + "' WHERE id = " + duzenlenecekKisi.getId() + " ");

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public CreateTableDb initOlustur() {

		CreateTableDb baglanti = new CreateTableDb();

		return baglanti;

	}

}
