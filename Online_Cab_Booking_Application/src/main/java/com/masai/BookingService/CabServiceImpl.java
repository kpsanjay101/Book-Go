package com.masai.BookingService;

import java.util.List;

import com.masai.BookingDAO.EMUtils;
import com.masai.BookingEntity.Cab;
import com.masai.BookingEntity.Driver;
import com.masai.Exception.NoRecordFoundException;
import com.masai.Exception.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CabServiceImpl implements CabService{

	@Override
	public void insertCab(int cab_id, String cabType, boolean isAvailable, float price, float rating, String userName) {
		
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		String getDriver = "SELECT d FROM Driver d WHERE d.userName = :name";
		Query query = em.createQuery(getDriver);
		query.setParameter("name", userName);
		Driver dr = (Driver)query.getSingleResult();
		Cab cab = new Cab(cab_id, cabType, rating, price, isAvailable, dr);
		try {
			if(dr == null) {
				throw new NoRecordFoundException("No Record found. Please enter correct Driver userName");
			}
			
			et.begin();
			em.persist(cab);
			et.commit();
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
	}

	@Override
	public void updateCab(int cabId,boolean isAv,float rating, float price) {
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Cab cab = em.find(Cab.class, cabId);
		try {
			et.begin();
			
			if(cab == null) {
				throw new NoRecordFoundException("No Record Found with this Id");
			}
			if(rating != 0.0) {
				cab.setRating(rating);
			}else {
				cab.setRating(cab.getRating());
			}
			if(price != 0.0) {
				cab.setPerKmRate(price);
			}else {
				cab.setPerKmRate(cab.getPerKmRate());
			}
			cab.setAvailable(isAv);
			em.persist(cab);
			et.commit();
			
			
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
	}

	@Override
	public void deleteCab(int cabId) {
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		String deleteQ = "SELECT c FROM Cab c WHERE c.cabId = :id";
		
		Query query = em.createQuery(deleteQ);
		query.setParameter("id", cabId);
		Cab cab = (Cab)query.getSingleResult();

		try {
			if(cab == null) {
				throw new NoRecordFoundException("No Record found with this Id");
			}
			
			et.begin();
			em.remove(cab);
			et.commit();

		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
	}

	@Override
	public List<Cab> viewCabsOfType(String carTye) {
		EntityManager em = EMUtils.getEntityManager();
		String getByType = "SELECT c FROM Cab c WHERE c.carType = :type";
		Query query = em.createQuery(getByType);
		query.setParameter("type", carTye);
		List<Cab> list = query.getResultList();
		
		try {
			if(list.isEmpty()) {
				throw new NoRecordFoundException("No Record Found");
			}
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		return list;
	}

	@Override
	public int countCabOfType(String carType) {
		// TODO Auto-generated method stub
		return 0;
	}

}
