package com.masai.BookingEntity;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User{
	private int adminId;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String userName, String password, String address, String mobileNo, String email, int adminId) {
		super(userName, password, address, mobileNo, email);
		this.adminId = adminId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	@Override
	public String toString() {
		return "adminId=" + adminId;
	}
	

}
