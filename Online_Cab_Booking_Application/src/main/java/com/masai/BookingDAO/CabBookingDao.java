package com.masai.BookingDAO;

import java.util.List;

import com.masai.BookingEntity.Admin;
import com.masai.BookingEntity.Cab;
import com.masai.BookingEntity.Customer;
import com.masai.BookingEntity.TripBooking;
import com.masai.Exception.NoCabAvailableAtThisTime;
import com.masai.Exception.SomethingWentWrongException;
import com.masai.Exception.UserNotFoundException;

public interface CabBookingDao {
	
	public void addCabAndDetails();
	
	public void addAdmin(String userName, String password, String address, String mobilNo, String email, int adminId);
	
	public Admin findUsernameAndPasswordForAdmin(String user_name) throws UserNotFoundException,SomethingWentWrongException;
	
	public Customer findUsernameAndPasswordForCustomer(String user_name) throws UserNotFoundException,SomethingWentWrongException;
    
	public void addCustomer(Customer customer);
	
	public List<Cab> viewAllCabFromCustomerSide() throws NoCabAvailableAtThisTime;
	
	public List<Cab> giveAvailableCab();
	public void bookAvailableCab(String userName,String driverId,int bookingId,String fDateTime,String eDateTime,String fLocation,String tLocation,float distence);
	public TripBooking viewBookedCab(int tripBookingId);
	public void updateYourAccount(String userName, String password, String address, String mobileNo, String email);
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
