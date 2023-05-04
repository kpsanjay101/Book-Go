package com.masai.BookingService;

import com.masai.BookingDAO.CabBookingDao;
import com.masai.BookingDAO.CabBookingDaoImpl;
import com.masai.BookingEntity.Admin;
import com.masai.Exception.UserNotFoundException;

import jakarta.persistence.PersistenceException;

public class BookingServise {
	
	public static String loginAdminPage(String user_name, String password) {
		
		CabBookingDao cabBDao = new CabBookingDaoImpl();
		
		try {
			Admin admin = cabBDao.findUsernameAndPassword(user_name);
			String userName = admin.getUserName();
			String passwordA = admin.getPassword();
			if(user_name.equals(userName) && password.equals(passwordA)) {
				return "Login successfull";
			}
		}catch(PersistenceException ex) {
			throw new UserNotFoundException("User doesn't Exist. Please Sign in first");
		}
		
		
		return "Please check your userName And password";
		
	}

}
