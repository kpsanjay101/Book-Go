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
import com.masai.Exception.NoRecordFoundException;
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
		
		Cab cab = new Cab(1, "SUV", 3.5f, 20.25f,true, null);
		
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
	public void addCustomer(Customer customer) {
		
		EntityManager em = EMUtils.getEntityManager();
//		Customer cust = new Customer(userName, password, address, mobilNo, email,customerId);
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(customer);
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

   public List<Cab> giveAvailableCab() {
		
		EntityManager em = EMUtils.getEntityManager();
		List<Cab> list = null;
		String viewQuery = "SELECT c FROM Cab c WHERE c.available = :yes";
		Query vQuery = em.createQuery(viewQuery);
		vQuery.setParameter("yes", true);
		
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

   public void bookAvailableCab(String user_name,String driverId,int bookingId,String fDateTime,String eDateTime,String fLocation,String tLocation,float distence) {
	   EntityManager em = EMUtils.getEntityManager();
	   EntityTransaction et = em.getTransaction();
	   String getCus = "SELECT d FROM Driver d WHERE d.userName = :name";
	   Query query1 = em.createQuery(getCus);
	   query1.setParameter("name", driverId);
	   Driver dr = (Driver)query1.getSingleResult();
	   
//	   String getCab = "SELECT c FROM Cab c WHERE c.driver_id = :drId";
//	   Query query2 = em.createQuery(getCab);
//	   query2.setParameter("drId", driverId);
//	   Cab cab = (Cab)query2.getSingleResult();
	   
//	   Set<Driver> set = new HashSet<>();
//	   set.add(dr);
	   
	   String findCustomer = "SELECT c FROM Customer c WHERE c.userName = :name";
	   Query query2 = em.createQuery(findCustomer);
	   query2.setParameter("name", user_name);
	   Customer cus = (Customer)query2.getSingleResult();
	   
	   
		   if(cus == null) {
			   System.out.println("Please enter Correct user_name");
			  return ;
		   }
	   
	   String insertQuery = "INSERT INTO trip_booking_customers (customerId, tripBookingId) "
	                  +"VALUES (:customerId, :tripBookingId)";
	   
	   Query query3 = em.createNativeQuery(insertQuery);
	   
	   
	   
	   TripBooking trip = new TripBooking();
	   
	   trip.setDriver(dr);
	   trip.setTripBookingId(bookingId);
	   trip.setFromDateTime(fDateTime);
	   trip.setToDateTime(eDateTime);
	   trip.setFromLocation(fLocation);
	   trip.setToLocation(tLocation);
	   trip.setDistanceInKM(distence);
	   trip.setBill(dr.getCab().getPerKmRate()*distence);
	   trip.setStattus(true);
	   
	   try {
		   et.begin();
		   em.persist(trip);
		   query3.setParameter("customerId", cus.getUserName());
		   query3.setParameter("tripBookingId", trip.getTripBookingId());
		   query3.executeUpdate();
		   
		   
		   et.commit();
		   
	   }catch(PersistenceException e) {
		   throw new SomethingWentWrongException("Something went wrong");
	   }finally {
		   em.close();
	   }
	   
   }
	
	@Override
	public void updateYourAccount(String userName, String password, String address, String mobileNo, String email) {
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		String updateQuery = "SELECT c FROM Customer c WHERE c.userName = :user_name";
		Query query = em.createQuery(updateQuery);
		query.setParameter("user_name", userName);

		Customer cust = (Customer)query.getSingleResult();
		
		try {
			if(cust == null) {
				throw new UserNotFoundException("Customer doesn't Exist whith this id");
			}
			et.begin();
			if(password != "") {
				cust.setPassword(password);
			}else {
				cust.setPassword(cust.getPassword());
			}
			
            if(address != "") {
            	cust.setAddress(address);
			}else {
				cust.setAddress(cust.getAddress());
			}
			
			if(email != "") {
				cust.setEmail(email);		
			}else {
				cust.setEmail(cust.getEmail());
			}
			
			if(mobileNo != "") {
				cust.setMobileNo(mobileNo);			
			}else {
				cust.setMobileNo(cust.getMobileNo());
			}
			et.commit();
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
	}

	@Override
	public TripBooking viewBookedCab(int tripBookingId) {
		EntityManager em = EMUtils.getEntityManager();
		String getQuery = "SELECT t FROM TripBooking t WHERE t.tripBookingId = :id";
		Query query = em.createQuery(getQuery);
		query.setParameter("id", tripBookingId);
		TripBooking tripB = (TripBooking)query.getSingleResult();
		
		try {
			if(tripB == null) {
				throw new NoRecordFoundException("You don't have any Open Booking");
			}
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}
		return tripB;
	}

}
