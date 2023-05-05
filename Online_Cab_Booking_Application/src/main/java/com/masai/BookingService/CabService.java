package com.masai.BookingService;

import java.util.List;

import com.masai.BookingEntity.Cab;

public interface CabService {
	public void insertCab(Cab cab);
	public void updateCab(int cabId);
	public void deleteCab(int cabId);
	public List<Cab> viewCabsOfType(String carTye);
	public int countCabOfType(String carType);
	

}
