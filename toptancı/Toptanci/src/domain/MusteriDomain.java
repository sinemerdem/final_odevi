package domain;

import java.util.Collection;

public class MusteriDomain {

	private int mNo;
	private String mAdi;
	private String mSoyadi;
	private String ePosta;
	private String mTel;
	private String dTarihi;
	private String cinsiyet;

	public int getMNo() {
		return mNo;
	}

	public void setMNo(int id) {
		this.mNo = id;
	}

	public String getAdi() {
		return mAdi;
	}

	public void setAdi(String adi) {
		this.mAdi = adi;
	}

	public String getSoyadi() {
		return mSoyadi;
	}

	public void setSoyadi(String soyadi) {
		this.mSoyadi = soyadi;
	}

	public String getEPosta() {
		return ePosta;
	}

	public void setEPosta(String ePosta) {
		this.ePosta = ePosta;
	}

	public String getTelefonu() {
		return mTel;
	}

	public void setTelefonu(String telefonu) {
		this.mTel = telefonu;
	}
	public String getDTarihi() {
		return dTarihi;
	}

	public void setDTarihi(String dTarihi) {
		this.dTarihi = dTarihi;
	}
	public String getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public Object[] getVeriler() {
		Object veriler[] = { mNo, mAdi, mSoyadi, ePosta, mTel,dTarihi,cinsiyet };

		return veriler;
	}

	public Collection getToplam() {
		
		return null;
	}

}
