package com.Emma.bean;

import java.util.Date;

public class Register {
	private String rid;
	private String name;
	private String idCard;
	private String siNumber;
	private Double registerMoney;
	private String phone;
	private boolean ispay;
	private boolean sex;
	private Integer age;
	private boolean consultation;
	private Integer department;
	private Integer did;
	private Integer status;
	private Date registerDate;
	private String remark;
	
	public Register() {
		super();
	}
	public Register(String rid, String name, String idCard, String siNumber, Double registerMoney, String phone,
			boolean ispay, boolean sex, Integer age, boolean consultation, Integer department, Integer did,
			Integer status, Date registerDate, String remark) {
		super();
		this.rid = rid;
		this.name = name;
		this.idCard = idCard;
		this.siNumber = siNumber;
		this.registerMoney = registerMoney;
		this.phone = phone;
		this.ispay = ispay;
		this.sex = sex;
		this.age = age;
		this.consultation = consultation;
		this.department = department;
		this.did = did;
		this.status = status;
		this.registerDate = registerDate;
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return "Register [rid=" + rid + ", name=" + name + ", idCard=" + idCard + ", siNumber=" + siNumber
				+ ", registerMoney=" + registerMoney + ", phone=" + phone + ", ispay=" + ispay + ", sex=" + sex
				+ ", age=" + age + ", consultation=" + consultation + ", department=" + department + ", did=" + did
				+ ", status=" + status + ", registerDate=" + registerDate + ", remark=" + remark + "]";
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getSiNumber() {
		return siNumber;
	}
	public void setSiNumber(String siNumber) {
		this.siNumber = siNumber;
	}
	public Double getRegisterMoney() {
		return registerMoney;
	}
	public void setRegisterMoney(Double registerMoney) {
		this.registerMoney = registerMoney;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isIspay() {
		return ispay;
	}
	public void setIspay(boolean ispay) {
		this.ispay = ispay;
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
	public boolean isConsultation() {
		return consultation;
	}
	public void setConsultation(boolean consultation) {
		this.consultation = consultation;
	}
	public Integer getDepartment() {
		return department;
	}
	public void setDepartment(Integer department) {
		this.department = department;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
