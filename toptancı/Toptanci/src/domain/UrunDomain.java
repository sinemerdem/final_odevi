package domain;

public class UrunDomain {

	private int uNo;
	private String uAdi;
	private String uTuru;
	private String uTarih;
	private double fiyat;
	
	

	public int getUNo() {
		return uNo;
	}

	public void setUNo(int uNo) {
		this.uNo = uNo;
	}

	public String getAdi() {
		return uAdi;
	}

	public void setAdi(String uAdi) {
		this.uAdi = uAdi;
	}

	public String getUrunTuru() {
		return uTuru;
	}

	public void setUTuru(String uTuru) {
		this.uTuru = uTuru;
	}

	public String getUTarih() {
		return uTarih;
	}

	public void setUTarih(String uTarih) {
		this.uTarih = uTarih;
	}

	public double getFiyat() {
		return fiyat;
	}

	public void setFiyat(double fiyat) {
		this.fiyat = fiyat;
	}
	

	public Object[] getVeriler() {
		Object veriler[] = { uNo, uAdi, uTuru, uTarih, fiyat };

		return veriler;
	}
	

}
