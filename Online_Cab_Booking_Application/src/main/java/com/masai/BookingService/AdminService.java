package com.masai.BookingService;

import java.util.List;

import com.masai.BookingEntity.Admin;
import com.masai.BookingEntity.TripBooking;

public interface AdminService {
	
	public void insertAdmin(Admin admin);
	public void updateAdmin(String password, String address,String email, String mobileNo, int adminId);
	public void deleteAdmin(int adminId);
	public  List<TripBooking> getAllTrips(String userId);
	public List<TripBooking> getTripsCabwise();
	public List<TripBooking> getTripsCustomerwise();
	public List<TripBooking> getTripsDatewise();
	
}
