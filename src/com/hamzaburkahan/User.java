package com.hamzaburkahan;

import com.hamzaburakhan.jdbc.RealName;
import com.hamzaburakhan.jdbc.Table;

@Table("tabloadi")
public class User {
	
	private int id;

	@RealName("name")
	private String ad;

	@RealName("lastName")
	private String soyad;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int id, String ad, String soyad) {
		super();
		this.id = id;
		this.ad = ad;
		this.soyad = soyad;
	}

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

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

}