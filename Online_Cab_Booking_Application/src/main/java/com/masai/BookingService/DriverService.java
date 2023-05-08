package com.masai.BookingService;

import java.util.List;
import java.util.Scanner;

import com.masai.BookingEntity.Cab;
import com.masai.BookingEntity.Driver;

public interface DriverService {
	public void insertDriver(Driver driverb);
	public void updateDriver(int driverId,String address,String email,String mobileNO,float rating);
	public void deleteDriver(int driverId);
	public List<Driver> viewBestDriver();//rating >= 4.5
	public Driver viewDriverById(int driverId);
	
	

}
