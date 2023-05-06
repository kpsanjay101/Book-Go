package com.masai.BookingService;

import java.util.List;
import java.util.Scanner;

import com.masai.BookingDAO.EMUtils;
import com.masai.BookingEntity.Cab;
import com.masai.BookingEntity.Driver;
import com.masai.Exception.NoRecordFoundException;
import com.masai.Exception.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class DriverServiceImpl implements DriverService{

	@Override
	public void insertDriver(Driver driver) {
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(driver);
			et.commit();
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
	}

	@Override
	public void updateDriver(int driverId,String address,String email,String mobileNO,float rating) {
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		String uddateQuery = "SELECT d FROM Driver d WHERE d.driverId = :id";
		Query query = em.createQuery(uddateQuery);
		query.setParameter("id", driverId);
		Driver dr = (Driver) query.getSingleResult();
		
		try {
			if(dr == null) {
				throw new NoRecordFoundException("Driver doesn't exist with this Id");
			}
			et.begin();
			dr.setAddress(address);
			dr.setEmail(email);
			dr.setMobileNo(mobileNO);
			dr.setRating(rating);
			
			et.commit();
			
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Somethind went wrong");
		}
		em.close();
		
	}

	@Override
	public void deleteDriver(int driverId) {
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		String deleteQuery = "SELECT d FROM Driver d WHERE d.driverId = :id";
		Query query = em.createQuery(deleteQuery);
		query.setParameter("id", driverId);
		Driver dr = (Driver)query.getSingleResult();
		
		
		try {
			if(dr == null) {
				throw new NoRecordFoundException("Data is not available with this Id");
			}
			et.begin();	
			em.remove(dr);
			et.commit();
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
	}

	@Override
	public List<Driver> viewBestDriver() {
		
		EntityManager em = EMUtils.getEntityManager();
		
		String deleteQuery = "SELECT d FROM Driver d WHERE d.rating >=4.5";
		Query query = em.createQuery(deleteQuery);
		List<Driver> list = query.getResultList();
		
		try {
			if(list.isEmpty()) {
				throw new NoRecordFoundException("No Record Found");
			}
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something Went Wrong");
		}finally {
			em.close();
		}
		
		return list;
	}

	@Override
	public Driver viewDriverById(int driverId) {
		
		EntityManager em = EMUtils.getEntityManager();
		String viewByIdQuery = "SELECT d FROM Driver d WHERE d.driverId = :id";
		Query query = em.createQuery(viewByIdQuery);
		query.setParameter("id", driverId);
		Driver dr = (Driver) query.getSingleResult();
		
		try {
			if(dr == null) {
				throw new NoRecordFoundException("No Data Found with this Id");
			}
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
		return dr;
	}

}
