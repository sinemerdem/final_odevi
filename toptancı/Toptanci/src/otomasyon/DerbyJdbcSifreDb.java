package otomasyon;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.SifreDomain;

public class DerbyJdbcSifreDb {
	private final String yol = "jdbc:derby:otomasyonProgrami;create=true";
	private final String kullanici = "";
	private final String sifre = "";

	public class CreateTableDb {

		public CreateTableDb() {

			initTable();

		}
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

	public void yeniSifre(SifreDomain sifreGir) {

		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			sorgu.executeUpdate("INSERT INTO sifreKullanici (kullaniciAdi,sifre) VALUES ('" + sifreGir.getKullaniciAdi() + "','" + sifreGir.getSifre() + "')");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	public void sifreGir(SifreDomain gir) {

		Connection baglan = getConnection();
		String kullaniciAdi = null;
		String sifre = null;
		
		try {
			Statement sorgu = baglan.createStatement();
			ResultSet rs = sorgu
					.executeQuery("SELECT sifre, kullaniciAdi FROM sifreKullanici WHERE kullaniciAdi ='" + kullaniciAdi+ "' AND sifre ='" + sifre + "' ");

			if (rs.next()) {

				gir.setKullaniciAdi(rs.getString(kullaniciAdi));
				gir.setSifre(rs.getString(sifre));
				
				
				
				sorgu.close();
				baglan.close();

			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	public CreateTableDb initTable() {

		CreateTableDb baglanti = new CreateTableDb();

		return baglanti;

	}
}
