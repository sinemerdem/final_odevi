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
import domain.PersonelDomain;

public class DerbyJdbcPersonel {
	private final String yol = "jdbc:derby:otomasyonProgrami;create=true";
	private final String kullanici = "";
	private final String sifre = "";

	public DerbyJdbcPersonel() {

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

	public void yeniKayit(PersonelDomain yeniPersonel) {
		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("INSERT INTO personel ( adi, soyadi, gorevi, telefonu ) VALUES ('" + yeniPersonel.getAdi() + "','" + yeniPersonel.getSoyadi() 	+ "','" + yeniPersonel.getGorevi() 	+ "','" + yeniPersonel.getTel() + "')");

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<PersonelDomain> listele() {

		Connection baglanti = getConnection();
		PersonelDomain yeniDomain;
		List<PersonelDomain> uList = new ArrayList<PersonelDomain>();

		try {
			Statement sorgu = baglanti.createStatement();

			ResultSet rs = sorgu.executeQuery("SELECT *FROM personel");

			while (rs.next()) {
				yeniDomain = new PersonelDomain();

				yeniDomain.setPNo(rs.getInt("pNo"));
				yeniDomain.setAdi(rs.getString("adi"));
				yeniDomain.setSoyadi(rs.getString("soyadi"));
				yeniDomain.setGorevi(rs.getString("gorevi"));
				yeniDomain.setTel(rs.getString("telefonu"));

				uList.add(yeniDomain);

			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return uList;
	}

	public PersonelDomain bul(int pNo) {
		PersonelDomain bulunacakKisi = new PersonelDomain();

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM personel WHERE pNo = " + pNo + "");

			while (rs.next()) {
				bulunacakKisi.setPNo(rs.getInt("pNo"));
				bulunacakKisi.setAdi(rs.getString("adi"));
				bulunacakKisi.setSoyadi(rs.getString("soyadi"));
				bulunacakKisi.setGorevi(rs.getString("gorevi"));
				bulunacakKisi.setTel(rs.getString("telefonu"));

			}

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return bulunacakKisi;

	}

	public void sil(PersonelDomain silinecekKisi) {

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("DELETE FROM personel WHERE pNo = " + silinecekKisi.getPNo() + "");

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void duzenle(PersonelDomain duzenlenecekKisi) {

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("UPDATE personel SET adi='" + duzenlenecekKisi.getAdi() + "',soyadi='" + duzenlenecekKisi.getSoyadi() + "',gorevi='" + duzenlenecekKisi.getGorevi() + "',telefonu='" + duzenlenecekKisi.getTel() + "' WHERE pNo =" + duzenlenecekKisi.getPNo() + "");

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
