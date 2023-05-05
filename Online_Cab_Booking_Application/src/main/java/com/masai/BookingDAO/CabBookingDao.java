package com.masai.BookingDAO;

import java.util.List;

import com.masai.BookingEntity.Admin;
import com.masai.BookingEntity.Cab;
import com.masai.BookingEntity.Customer;
import com.masai.Exception.NoCabAvailableAtThisTime;
import com.masai.Exception.SomethingWentWrongException;
import com.masai.Exception.UserNotFoundException;

public interface CabBookingDao {
	
	public void addCabAndDetails();
	
	public void addAdmin(String userName, String password, String address, String mobilNo, String email, int adminId);
	
	public Admin findUsernameAndPasswordForAdmin(String user_name) throws UserNotFoundException,SomethingWentWrongException;
	
	public Customer findUsernameAndPasswordForCustomer(String user_name) throws UserNotFoundException,SomethingWentWrongException;
    
	public void addCustomer(String userName, String password, String address, String mobilNo, String email,int customerId);
	
	public List<Cab> viewAllCabFromCustomerSide() throws NoCabAvailableAtThisTime;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
