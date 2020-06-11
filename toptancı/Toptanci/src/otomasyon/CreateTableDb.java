package otomasyon;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableDb {
	private final String yol = "jdbc:derby:otomasyonProgrami;create=true";
	private final String kullanici = "";
	private final String sifre = "";

	public CreateTableDb() {

		initTable();

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

	private void initTable() {

		Connection baglanti = getConnection();

		try {
			Statement sorgu = baglanti.createStatement();
			sorgu.executeUpdate("CREATE TABLE MUSTERI(mNo INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY " + "(START WITH 1, INCREMENT BY 1), " + "mAdi VARCHAR(50), " + "mSoyadi VARCHAR(50), " + "ePosta VARCHAR(50), " 	+ "mTel VARCHAR(50)," 	+ "dTarihi VARCHAR(50)," + "cinsiyet VARCHAR(50))");

			sorgu.executeUpdate("CREATE TABLE urun (uNo INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY " + "(START WITH 1, INCREMENT BY 1), " + "uAdi VARCHAR(50), " + "uTuru VARCHAR(50), " + "uTarihi VARCHAR(50), " + "uFiyat  DOUBLE)");

			sorgu.executeUpdate("CREATE TABLE personel (pNo INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY " + "(START WITH 1, INCREMENT BY 1), " + "adi VARCHAR(50), " + "soyadi VARCHAR(50), " + "gorevi VARCHAR(50),"  + "telefonu VARCHAR(50))");
			sorgu.executeUpdate("CREATE TABLE stok (id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY " + "(START WITH 1, INCREMENT BY 1), " + "uAdi VARCHAR(50), "	+ "uTuru VARCHAR(50), "	+ "adet INTEGER," 	+ "tarih VARCHAR(50))");
			sorgu.executeUpdate("CREATE TABLE satis(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY " + "(START WITH 1, INCREMENT BY 1), " + "mAdi VARCHAR(50), " + "mSoyadi VARCHAR(50), " + "pAdi VARCHAR(50), " + "pSoyadi VARCHAR(50)," + "uAdi VARCHAR(50)," + "uTuru VARCHAR(50)," + "uFiyat DOUBLE," + "adet INTEGER,"  + "satisTarihi VARCHAR(50))");

			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {

			
		}

	}

}
