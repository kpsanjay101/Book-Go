package com.masai.BookingEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer extends User{
	@Column(unique = true)
	private int customerId;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String userName, String password, String address, String mobileNo, String email,int customerId) {
		super(userName, password, address, mobileNo, email);
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "customerId=" + customerId;
	}
	
	

}
