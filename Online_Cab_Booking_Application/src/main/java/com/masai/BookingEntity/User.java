package com.masai.BookingEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
	@Id
	private String userName;
	private String password;
	private String address;
	private String mobileNo;
	private String email;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String password, String address, String mobileNo, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.mobileNo = mobileNo;
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "userName=" + userName + ", password=" + password + ", address=" + address + ", mobileNo="
				+ mobileNo + ", email=" + email;
	}
	
	

}
