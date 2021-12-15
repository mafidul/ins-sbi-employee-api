package com.tcs.ins.emp.service.model;

public class EmployeeModel {
	private Long empId;
	private String empName;
	private String address;
	private Long phoneNo;
	private String email;
	private String confirmEmail;
	private String communicationType;

	public Long getEmpId() {
		return empId;
	}

	public String getEmpName() {
		return empName;
	}

	public String getAddress() {
		return address;
	}

	public Long getPhoneNo() {
		return phoneNo;
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

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
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

	@Override
	public String toString() {
		return "EmployeeModel [empId=" + empId + ", empName=" + empName + ", address=" + address + ", phoneNo="
				+ phoneNo + ", email=" + email + ", confirmEmail=" + confirmEmail + ", communicationType="
				+ communicationType + "]";
	}
}
