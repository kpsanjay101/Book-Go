package com.masai.BookingService;

import java.util.List;

import com.masai.BookingEntity.Admin;
import com.masai.BookingEntity.TripBooking;

public interface AdminService {
	
	public Admin insertAdmin(Admin admin);
	public void updateAdmin(int adminId);
	public void deleteAdmin(Admin admin);
	public List<TripBooking> getAllTrips(int adminId);
	public List<TripBooking> getTripsCabwise();
	public List<TripBooking> getTripsCustomerwise();
	public List<TripBooking> getTripsDatewise();
	
}
