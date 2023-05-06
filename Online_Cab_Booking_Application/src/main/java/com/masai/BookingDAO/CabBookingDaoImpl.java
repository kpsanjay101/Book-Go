package com.masai.BookingDAO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.masai.BookingEntity.Admin;
import com.masai.BookingEntity.Cab;
import com.masai.BookingEntity.Customer;
import com.masai.BookingEntity.Driver;
import com.masai.BookingEntity.TripBooking;
import com.masai.Exception.NoCabAvailableAtThisTime;
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
		
		Admin admin = new Admin("rohit123", "Rohit@123", "Patna", "4578458788", "rohit@gmail.com", 1);
//		"aman123", "Aman@123", "Hajipur", "9845124578", "aman@gmail.com", 1
		Customer customer = new Customer();
		customer.setUserName("aman123");
		customer.setPassword("Aman@123");
		customer.setAddress("Hajipur");
		customer.setEmail("aman@gmail.com");
		customer.setMobileNo("9845124578");
		customer.setCustomerId(1);
		
		Set<Customer> cusSet = new HashSet<>();
		cusSet.add(customer);
		
		Cab cab = new Cab(1, "SUV", 3.5f, 20.25f, null);
		
		Driver driver = new Driver("naman123", "Naman@123", "Patna", "7894563245", "naman@gmail.com",1, "naman12L12", 3.5f, cab, null);
		cab.setDriver(driver);
		
		Set<TripBooking> tripSet = new HashSet<>();
		
//		1001, 1, null, "Pune", "Mumbai", "2023-05-04-8-42-12", "2023-05-08-9-25-42", true, 500, 0.0f
		
		TripBooking tripB1 = new TripBooking();
		
		tripB1.setTripBookingId(1001);
		tripB1.setFromDateTime("2023-05-04-8-42-12");
		tripB1.setToDateTime("2023-05-08-9-25-42");
		tripB1.setFromLocation("Pune");
		tripB1.setToLocation( "Mumbai");
		tripB1.setStattus(true);
		tripB1.setDistanceInKM(300);
		tripB1.setBill(2000);
		tripB1.setDriver(driver);
		tripB1.setCustomer(cusSet);
		
		
//		TripBooking tripB2 = new TripBooking(1002, 2, null, "Patna", "Gaya", "2022-05-04-8-42-12", "2022-05-08-9-25-42", true, 150, 0.0f);
		
		tripSet.add(tripB1);
		customer.setTripBooking(tripSet);
		driver.setTripBooking(tripSet);
		
	    EntityTransaction et = em.getTransaction();
	    
	    et.begin();
	    em.persist(admin);
	    em.persist(customer);
	    em.persist(driver);
	    et.commit();
	    em.close();

	}

	@Override
	public Admin findUsernameAndPasswordForAdmin(String user_name) throws UserNotFoundException,SomethingWentWrongException{
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
		}finally {
			em.close();
		}
		
		return admin;
	}

	@Override
	public void addAdmin(String userName, String password, String address, String mobilNo, String email,int adminId) {
		EntityManager em = EMUtils.getEntityManager();
		Admin admin = new Admin(userName, password, address, mobilNo, email,adminId);
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

	@Override
	public Customer findUsernameAndPasswordForCustomer(String user_name) throws UserNotFoundException, SomethingWentWrongException {
		Customer cust = null;
		EntityManager em = EMUtils.getEntityManager();
		String findCustomer = "SELECT c FROM Customer c WHERE c.userName = :name";
		Query query = em.createQuery(findCustomer);
		query.setParameter("name", user_name);
		cust = (Customer) query.getSingleResult();
		try {
			if(cust == null) {
				throw new UserNotFoundException("User doesn't Exist");
			}
		}catch(PersistenceException ex) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
		return cust;
		
	}

	@Override
	public void addCustomer(String userName, String password, String address, String mobilNo, String email,int customerId) {
		
		EntityManager em = EMUtils.getEntityManager();
//		Customer cust = new Customer(userName, password, address, mobilNo, email,customerId);
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
//			em.persist(cust);
			et.commit();
		}catch(PersistenceException ex) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
	}

	@Override
	public List<Cab> viewAllCabFromCustomerSide() throws NoCabAvailableAtThisTime{
		EntityManager em = EMUtils.getEntityManager();
		List<Cab> list = null;
		String viewQuery = "SELECT c FROM Cab c";
		Query vQuery = em.createQuery(viewQuery);
		
		try {
			list = vQuery.getResultList();
			if(list.isEmpty()) {
				throw new NoCabAvailableAtThisTime("No Cab Available Please Come After Some Times");
			}
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
		
		return list;
	}

}
