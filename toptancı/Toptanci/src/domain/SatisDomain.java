package domain;

public class SatisDomain {
	private int id;
	private String mAdi;
	private String mSoyadi;
	private String pAdi;
	private String pSoyadi;
	private String uAdi;
	private String uTuru;
	private double uFiyat;
	private int adet;
	private String satisTarihi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMAdi() {
		return mAdi;
	}

	public void setMAdi(String mAdi) {
		this.mAdi = mAdi;
	}

	public String getMSoyadi() {
		return mSoyadi;
	}

	public void setMSoyadi(String mSoyadi) {
		this.mSoyadi = mSoyadi;
	}

	public String getPAdi() {
		return pAdi;
	}

	public void setPAdi(String pAdi) {
		this.pAdi = pAdi;
	}

	public String getPSoyadi() {
		return pSoyadi;
	}

	public void setPSoyadi(String pSoyadi) {
		this.pSoyadi = pSoyadi;
	}

	public String getUAdi() {
		return uAdi;
	}

	public void setUAdi(String uAdi) {
		this.uAdi = uAdi;
	}

	public String getUTuru() {
		return uTuru;
	}

	public void setUTuru(String uTuru) {
		this.uTuru = uTuru;
	}

	public double getUFiyat() {
		return uFiyat;
	}

	public void setUFiyat(double uFiyat) {
		this.uFiyat = uFiyat;
	}

	public int getAdet() {
		return adet;
	}

	public void setAdet(int adet) {
		this.adet = adet;
	}

	public String getSatisTarihi() {
		return satisTarihi;
	}

	public void setSatisTarihi(String satisTarihi) {
		this.satisTarihi = satisTarihi;
	}

	public Object[] getVeriler() {

		Object[] veriler = { id, mAdi, mSoyadi, pAdi, pSoyadi, uAdi, uTuru, uFiyat, adet, satisTarihi };

		return veriler;
	}
}
