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

public class DerbyJdbcIleMusteri {
	private final String yol = "jdbc:derby:otomasyonProgrami;create=true";
	private final String kullanici = "";
	private final String sifre = "";

	public DerbyJdbcIleMusteri() {

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

	public void yeniKayit(MusteriDomain yeniMusteri) {
		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("INSERT INTO musteri(mAdi, mSoyadi, ePosta, mTel,dTarihi,cinsiyet) VALUES('" + yeniMusteri.getAdi() + "','" + yeniMusteri.getSoyadi() + "','" 	+ yeniMusteri.getEPosta() + "','" 	+ yeniMusteri.getTelefonu() + "','" + yeniMusteri.getDTarihi() + "','" 	+ yeniMusteri.getCinsiyet() + "')");

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<MusteriDomain> listele() {

		Connection baglanti = getConnection();
		MusteriDomain yeniDomain;
		List<MusteriDomain> mList = new ArrayList<MusteriDomain>();

		try {
			Statement sorgu = baglanti.createStatement();

			ResultSet rs = sorgu.executeQuery("SELECT * FROM musteri");

			while (rs.next()) {
				yeniDomain = new MusteriDomain();

				yeniDomain.setMNo(rs.getInt("mNo"));
				yeniDomain.setAdi(rs.getString("mAdi"));
				yeniDomain.setSoyadi(rs.getString("mSoyadi"));
				yeniDomain.setEPosta(rs.getString("ePosta"));
				yeniDomain.setTelefonu(rs.getString("mTel"));
				yeniDomain.setDTarihi(rs.getString("dTarihi"));
				yeniDomain.setCinsiyet(rs.getString("cinsiyet"));

				mList.add(yeniDomain);

			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return mList;
	}

	public MusteriDomain bul(int mNo) {
		MusteriDomain bulunacakKisi = new MusteriDomain();

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM musteri WHERE mNo = " + mNo + "");

			while (rs.next()) {
				bulunacakKisi.setMNo(rs.getInt("mNo"));
				bulunacakKisi.setAdi(rs.getString("mAdi"));
				bulunacakKisi.setSoyadi(rs.getString("mSoyadi"));
				bulunacakKisi.setEPosta(rs.getString("ePosta"));
				bulunacakKisi.setTelefonu(rs.getString("mTel"));
				bulunacakKisi.setDTarihi(rs.getString("dTarihi"));
				bulunacakKisi.setCinsiyet(rs.getString("cinsiyet"));
			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			System.out.println(e);
		}

		return bulunacakKisi;

	}

	public void sil(MusteriDomain silinecekKisi) {

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("DELETE FROM musteri WHERE mNo = " + silinecekKisi.getMNo() + "");

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void duzenle(MusteriDomain duzenlenecekKisi) {

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("UPDATE musteri SET mAdi='"	+ duzenlenecekKisi.getAdi() + "',mSoyadi='" + duzenlenecekKisi.getSoyadi() + "',ePosta='" + duzenlenecekKisi.getEPosta() + "',mTel='"	+ duzenlenecekKisi.getTelefonu() + "',dTarihi='"	+ duzenlenecekKisi.getDTarihi() + "',cinsiyet='"	+ duzenlenecekKisi.getCinsiyet() + "' WHERE mNo=" + duzenlenecekKisi.getMNo() + "");

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
