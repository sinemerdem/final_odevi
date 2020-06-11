package domain;

public class PersonelDomain {
	private int pNo;
	private String adi;
	private String soyadi;
	private String telefon;
	private String gorevi;

	public int getPNo() {
		return pNo;
	}

	public void setPNo(int pNo) {
		this.pNo = pNo;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getSoyadi() {
		return soyadi;
	}

	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}

	

	public String getTel() {

		return telefon;
	}

	public void setTel(String telefon) {
		this.telefon = telefon;

	}

	public String getGorevi() {
		return gorevi;
	}

	public void setGorevi(String gorevi) {
		this.gorevi = gorevi;
	}

	public Object[] getVeriler() {
		Object veriler[] = { pNo, adi, soyadi, gorevi, telefon };

		return veriler;
	}

}
