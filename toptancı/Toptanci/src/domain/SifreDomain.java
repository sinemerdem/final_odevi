package domain;

public class SifreDomain {

	private String kullaniciAdi;
	private String sifre;

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public Object[] getSifreVerisi() {

		Object[] sifreler = { kullaniciAdi, sifre };

		return sifreler;

	}

}
