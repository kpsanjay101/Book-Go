package com.masai.BookingService;

import java.util.List;

import com.masai.BookingEntity.Cab;

public interface CabService {
	public void insertCab(int cab_id, String cabType, boolean isAvailable, float price, float rating, String userName);
	public void updateCab(int cabId,boolean isAv,float rating, float price);
	public void deleteCab(int cabId);
	public List<Cab> viewCabsOfType(String carTye);
	public int countCabOfType(String carType);
	

}
