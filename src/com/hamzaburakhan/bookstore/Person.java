package com.hamzaburakhan.bookstore;

import com.hamzaburakhan.jdbc.RealName;
import com.hamzaburakhan.jdbc.Table;

@Table("persons")
public class Person {

	@RealName("id")
	private int id;
	
	@RealName("adi")
	private String name;
	
	@RealName("soyadi")
	private String lastname;
	
	@RealName("telefon")
	private String tel;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	

}
