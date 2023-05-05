package com.masai.BookingService;

import java.util.List;

import com.masai.BookingEntity.TripBooking;

public interface TripBookingService {
	
	public void insertTripBooking(TripBooking tripB);
	public void updateTripBooking(int tripBookingId);
	public void deleteTripBooking(int tripBookingId);
	public List<TripBooking> viewAllTripCustomer(int customerId);
	public TripBooking calculateBill(int customerId);
	
}
