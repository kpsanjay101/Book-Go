package com.masai.BookingService;

import java.util.List;

import com.masai.BookingDAO.EMUtils;
import com.masai.BookingEntity.Admin;
import com.masai.BookingEntity.Driver;
import com.masai.BookingEntity.TripBooking;
import com.masai.Exception.NoRecordFoundException;
import com.masai.Exception.SomethingWentWrongException;

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
				throw new NoRecordFoundException("No Record Found whith this Id");
			}
			et.begin();
			admin.setPassword(password);
			admin.setAddress(address);
			admin.setEmail(email);
			admin.setMobileNo(mobileNo);
			et.commit();
		}catch(PersistenceException ex) {
			throw new SomethingWentWrongException("Something Went Wrong");
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
	public List<TripBooking> getAllTrips(int adminId) {
		// TODO Auto-generated method stub
		return null;
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
