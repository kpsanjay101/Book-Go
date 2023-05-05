package com.masai.BookingService;

import java.util.List;

import com.masai.BookingEntity.Customer;

public interface CustomerService {
	public void insertCustomer(Customer cust);
	public void updateCustomer(int customerId, String address, String email, String mobileNo);
	public void deletetCustomer(int customerId);
	public List<Customer> viewCustomer();
	public Customer viewCustomerById(int customerId);
	public Customer validateCustomer(String userName, String password);
	
	
}
