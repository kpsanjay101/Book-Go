package com.masai.BookingService;

import java.util.ArrayList;
import java.util.List;

import com.masai.BookingDAO.EMUtils;
import com.masai.BookingEntity.Admin;
import com.masai.BookingEntity.Driver;
import com.masai.BookingEntity.TripBooking;
import com.masai.Exception.NoRecordFoundException;
import com.masai.Exception.SomethingWentWrongException;
import com.masai.Exception.UserNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class AdminServiceImpl implements AdminService{

	@Override
	public void insertAdmin(Admin admin) {
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(admin);
			et.commit();
		}catch(PersistenceException ex) {
			throw new SomethingWentWrongException("Something Went Wrong");
		}finally {
			em.close();
		}
		
	}

	@Override
	public void updateAdmin(String password, String address,String email, String mobileNo, int adminId) {
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		String getQuery = "SELECT a FROM Admin a WHERE a.adminId = :id";
		Query query = em.createQuery(getQuery);
		query.setParameter("id", adminId);
		Admin admin = (Admin)query.getSingleResult();
		try {
			if(admin == null) {
				throw new UserNotFoundException("Customer doesn't Exist whith this id");
			}
			et.begin();
			if(password != "") {
				admin.setPassword(password);
			}else {
				admin.setPassword(admin.getPassword());
			}
			
            if(address != "") {
            	admin.setAddress(address);
			}else {
				admin.setAddress(admin.getAddress());
			}
			
			if(email != "") {
				admin.setEmail(email);		
			}else {
				admin.setEmail(admin.getEmail());
			}
			
			if(mobileNo != "") {
				admin.setMobileNo(mobileNo);			
			}else {
				admin.setMobileNo(admin.getMobileNo());
			}
			et.commit();
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
	}

	@Override
	public void deleteAdmin(int adminId) {
		
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		String deleteQuery = "SELECT a FROM Admin a WHERE a.adminId = :id";
		Query query = em.createQuery(deleteQuery);
		query.setParameter("id", adminId);
		Admin admin = (Admin)query.getSingleResult();
		
		
		try {
			if(admin == null) {
				throw new NoRecordFoundException("Data is not available with this Id");
			}
			et.begin();	
			em.remove(admin);
			et.commit();
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		

	}

	@Override
	public List<TripBooking> getAllTrips(String customerId) {
		EntityManager em = EMUtils.getEntityManager();
		
		String getAllTrips = "SELECT tripBookingId FROM trip_booking_customers WHERE customerId = :id";
		Query query = em.createNativeQuery(getAllTrips);
		query.setParameter("id", customerId);
		
		List<Integer> list = query.getResultList();
		
		List<TripBooking> tripList = new ArrayList<>();
		
		try {
			if(list.isEmpty()) {
				throw new NoRecordFoundException("No Record Found");
			}
			for(Integer i : list) {
				TripBooking tripB = getActualTrip(i);
				tripList.add(tripB);
			}
			
		}catch(PersistenceException p) {
			throw new SomethingWentWrongException("Something went wrong");
		}
		
		return tripList;
	}

	static TripBooking getActualTrip(int tripId) {
		
		EntityManager em = EMUtils.getEntityManager();
		TripBooking tripB = em.find(TripBooking.class, tripId);
		
		
		return tripB;
	}
	
	@Override
	public List<TripBooking> getTripsCabwise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripBooking> getTripsCustomerwise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripBooking> getTripsDatewise() {
		// TODO Auto-generated method stub
		return null;
	}

}
