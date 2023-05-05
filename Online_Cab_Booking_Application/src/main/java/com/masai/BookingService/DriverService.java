package com.masai.BookingService;

import java.util.List;

import com.masai.BookingEntity.Driver;

public interface DriverService {
	public void insertDriver(Driver driver);
	public void updateDriver(int driverId);
	public void deleteDriver(int driverId);
	public List<Driver> viewBestDriver();//rating >= 4.5
	public Driver viewDriver(int driverId);
	
	

}
