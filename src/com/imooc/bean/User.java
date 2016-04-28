package com.imooc.bean;

import com.imooc.myannotation.Column;
import com.imooc.myannotation.Table;

@Table("user")
public class User {

	@Column("id")
	private int id;
	
	@Column("user_name")
	private String userName;
	
	@Column("age")
	private int age;
	
	@Column("email")
	private String email;
	
	@Column("address")
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
