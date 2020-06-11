package domain;

public class StokDomain {

	private int id;
	private String urunAdi;
	private String uTuru;
	private int adet;
	private String tarih;
	private int toplam;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrunAdi() {
		return urunAdi;
	}

	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
	}

	public String getUTuru() {
		return uTuru;
	}

	public void setUTuru(String uTuru) {
		this.uTuru = uTuru;
	}

	public int getAdet() {
		return adet;
	}

	public void setAdet(int adet) {
		this.adet = adet;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public int getToplam() {
		return toplam;
	}

	public void setToplam(int toplam) {
		this.toplam = toplam;
	}

	public Object[] getVeriler() {

		Object[] veriler = {id, adet, urunAdi, uTuru, tarih, toplam };

		return veriler;
	}
	
}
