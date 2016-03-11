package com.hamzaburakhan.bookstore;

import com.hamzaburakhan.jdbc.Embeded;
import com.hamzaburakhan.jdbc.RealName;
import com.hamzaburakhan.jdbc.Table;

@Table("books")
public class Book {
	
	
	private int id;

	@RealName("adi")
	private String ad;
	
	@RealName("yazar")
	private String yazar;

	@RealName("icerik")
	private String icerik;

	@RealName("basimyili")
	private int basimyili;

	@RealName("baskino")
	private int baskino;

	@RealName("resim")
	private String resim;

	@Embeded
	private Person verilenKisi;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getYazar() {
		return yazar;
	}

	public void setYazar(String yazar) {
		this.yazar = yazar;
	}

	public String getIcerik() {
		return icerik;
	}

	public void setIcerik(String icerik) {
		this.icerik = icerik;
	}

	public int getBasimyili() {
		return basimyili;
	}

	public void setBasimyili(int basimyili) {
		this.basimyili = basimyili;
	}

	public int getBaskino() {
		return baskino;
	}

	public void setBaskino(int baskino) {
		this.baskino = baskino;
	}

	public String getResim() {
		return resim;
	}

	public void setResim(String resim) {
		this.resim = resim;
	}

	public Person getVerilenKisi() {
		return verilenKisi;
	}

	public void setVerilenKisi(Person verilenKisi) {
		this.verilenKisi = verilenKisi;
	}
	
	
}
