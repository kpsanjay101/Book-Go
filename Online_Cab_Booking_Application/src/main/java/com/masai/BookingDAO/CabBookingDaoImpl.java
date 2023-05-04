package com.masai.BookingDAO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.masai.BookingEntity.Admin;
import com.masai.BookingEntity.Cab;
import com.masai.BookingEntity.Customer;
import com.masai.BookingEntity.Driver;
import com.masai.BookingEntity.TripBooking;
import com.masai.Exception.SomethingWentWrongException;
import com.masai.Exception.UserNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CabBookingDaoImpl implements CabBookingDao{

	@Override
	public void addCabAndDetails() {
        EntityManager em = EMUtils.getEntityManager();
		
		Admin admin = new Admin("rohit123", "Rohit@1223", "Patna", "4578458788", "rohit@gmail.com", 1);
		
		Customer customer = new Customer("aman123", "Aman123", "Hajipur", "1245124578", "aman@gmail.com", 1);
		
		Cab cab = new Cab(1, "SUV", 3.5f, 20.25f, null);
		Set<TripBooking> set = new HashSet<>();

		Driver driver = new Driver("naman123", "Naman@123", "Patna", "7894563245", "naman@gmail.com", 1, "naman12L12", 3.5f, cab, set);
		cab.setDriver(driver);
		
		TripBooking tripB1 = new TripBooking(1001, 1, null, "Pune", "Mumbai", "2023-05-04-8-42-12", "2023-05-08-9-25-42", true, 500, 0.0f);
		TripBooking tripB2 = new TripBooking(1002, 2, null, "Patna", "Gaya", "2022-05-04-8-42-12", "2022-05-08-9-25-42", true, 150, 0.0f);
		
		tripB1.setDriver(driver);
	    tripB2.setDriver(driver);
	    
		tripB1.setBill(tripB1.getDriver().getCab().getPerKmRate()*500);
		tripB2.setBill(tripB2.getDriver().getCab().getPerKmRate()*150);
		set.add(tripB1);
		set.add(tripB2);

		
		
	    
	    EntityTransaction et = em.getTransaction();
	    
	    et.begin();
	    em.persist(admin);
	    em.persist(customer);
	    em.persist(driver);
	    et.commit();
	    em.close();

	}

	@Override
	public Admin findUsernameAndPassword(String user_name) throws UserNotFoundException,SomethingWentWrongException{
		Admin admin = null;
		EntityManager em = EMUtils.getEntityManager();
		String findAdmin = "SELECT a FROM Admin a WHERE a.userName = :name";
		Query query = em.createQuery(findAdmin);
		query.setParameter("name", user_name);
		admin = (Admin) query.getSingleResult();
		try {
			if(admin == null) {
				throw new UserNotFoundException("User doesn't Exist");
			}
		}catch(PersistenceException ex) {
			throw new SomethingWentWrongException("Something went wrong");
		}
		
		return admin;
	}

	@Override
	public void addAdmin(String userName, String password, String address, String mobilNo, String email, int adminId) {
		EntityManager em = EMUtils.getEntityManager();
		Admin admin = new Admin(userName, password, address, mobilNo, email, adminId);
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(admin);
			et.commit();
		}catch(PersistenceException ex) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
	}

}
