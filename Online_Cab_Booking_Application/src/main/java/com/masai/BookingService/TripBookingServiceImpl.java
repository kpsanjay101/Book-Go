package com.masai.BookingService;

import java.util.List;

import com.masai.BookingDAO.EMUtils;
import com.masai.BookingEntity.Driver;
import com.masai.BookingEntity.TripBooking;
import com.masai.Exception.NoRecordFoundException;
import com.masai.Exception.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class TripBookingServiceImpl implements TripBookingService{

	@Override
	public void insertTripBooking(TripBooking tripB,String user_name) {
		
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();

		String drQuery = "SELECT d FROM Driver d WHERE d.userName = :name";
		Query query = em.createQuery(drQuery);
		query.setParameter("name", user_name);
		Driver dr = (Driver)query.getSingleResult();
		
	
		float price = dr.getCab().getPerKmRate();
		
		tripB.setBill(tripB.getDistanceInKM()*price);
		tripB.setDriver(dr);
		
		try {
			et.begin();
			em.persist(tripB);
			et.commit();
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}
		
	}

	@Override
	public void updateTripBooking(int tripBookingId,boolean status) {
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		String updateQuery = "SELECT t FROM TripBooking t WHERE t.tripBookingId = :id";
		Query query = em.createQuery(updateQuery);
		query.setParameter("id", tripBookingId);
//		query.setParameter("st", status);
		TripBooking trip = (TripBooking)query.getSingleResult();

		try {
			if(trip == null) {
				throw new NoRecordFoundException("No Record found with this id");
			}
			et.begin();
			trip.setStattus(status);
			trip.setTripBookingId(trip.getTripBookingId());
			trip.setFromDateTime(trip.getFromDateTime());
			trip.setToDateTime(trip.getToDateTime());
			trip.setFromLocation(trip.getFromLocation());
			trip.setToLocation(trip.getToLocation());
			trip.setDriver(trip.getDriver());
			trip.setBill(trip.getBill());
			trip.setDistanceInKM(trip.getDistanceInKM());

			em.persist(trip);
			et.commit();
			
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
	}

	@Override
	public void deleteTripBooking(int tripBookingId) {
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		TripBooking trip = em.find(TripBooking.class, tripBookingId);
		
		try {
			if(trip == null) {
				throw new NoRecordFoundException("No Record Found");
			}
			et.begin();
			em.remove(trip);
			et.commit();
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
	}

	@Override
	public List<TripBooking> viewAllTripCustomer(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripBooking calculateBill(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
