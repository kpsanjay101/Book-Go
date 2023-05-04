package com.masai.BookingDAO;

import com.masai.BookingEntity.Admin;
import com.masai.Exception.SomethingWentWrongException;
import com.masai.Exception.UserNotFoundException;

public interface CabBookingDao {
	
	public void addCabAndDetails();
	public void addAdmin(String userName, String password, String address, String mobilNo, String email, int adminId);
	public Admin findUsernameAndPassword(String user_name) throws UserNotFoundException,SomethingWentWrongException;

}
