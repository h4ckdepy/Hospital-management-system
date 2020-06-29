package com.Emma.bean;

import java.util.Date;

public class User {
	private  Integer uid;
	private  String  name;
	private  String  email;
	private  Integer status;
	private  String  username;
	private  String password;
	private  Date modifytime;
	
	public User(Integer uid, String name, String email, Integer status, String username, String password,
			Date modifytime) {
		super();
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.status = status;
		this.username = username;
		this.password = password;
		this.modifytime = modifytime;
	}
	public User() {
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", email=" + email + ", status=" + status + ", username="
				+ username + ", password=" + password + ", modifytime=" + modifytime + "]";
	}
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	
}
