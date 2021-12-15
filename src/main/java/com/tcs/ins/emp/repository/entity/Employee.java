package com.tcs.ins.emp.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "EMPLOYEE")
public class Employee {
	@Id
	@Column(name = "EMPID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;
	@Column(name = "EMPNAME")
	private String empName;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "PHONENO")
	private Long phoneNo;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "CONFIRMEMAIL")
	private String confirmEmail;
	@Column(name = "COMMUNICATIONTYPE")
	private String communicationType;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public String getCommunicationType() {
		return communicationType;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}
}
