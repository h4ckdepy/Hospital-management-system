package com.Emma.bean;

import java.util.Date;

public class Doctor {
	
	private Integer did;
	private String name;
	private String cardno;
	private String phone;
	private boolean sex;
	private Integer age;
	private Date birthday;
	
	private String email;
	private Integer department;
	private Integer education;
	private String remark;
	public Doctor() {
		
	}
	
	public Doctor(Integer did, String name, String cardno, String phone, boolean sex, Integer age, Date birthday,
			String email, Integer department, Integer education, String remark) {
		super();
		this.did = did;
		this.name = name;
		this.cardno = cardno;
		this.phone = phone;
		this.sex = sex;
		this.age = age;
		this.birthday = birthday;
		this.email = email;
		this.department = department;
		this.education = education;
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Doctor [did=" + did + ", name=" + name + ", cardno=" + cardno + ", phone=" + phone + ", sex=" + sex
				+ ", age=" + age + ", birthday=" + birthday + ", email=" + email + ", department=" + department
				+ ", education=" + education + ", remark=" + remark + "]";
	}

	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getdepartment() {
		return department;
	}
	public void setdepartment(Integer department) {
		this.department = department;
	}
	public Integer getEducation() {
		return education;
	}
	public void setEducation(Integer education) {
		this.education = education;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
