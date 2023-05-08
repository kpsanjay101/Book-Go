package com.masai.BookingEntity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TripBooking {
	@Id
	private int tripBookingId;

	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Driver driver;
	private String fromLocation;
	private String toLocation;
	private String fromDateTime;
	private String toDateTime;
	private boolean stattus;
	private float distanceInKM;
	private float bill;
	@ManyToMany(mappedBy = "tripBooking")
	private Set<Customer> customer;
	
	public TripBooking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TripBooking(int tripBookingId, Driver driver, String fromLocation, String toLocation, String fromDateTime,
			String toDateTime, boolean stattus, float distanceInKM, float bill, Set<Customer> customer) {
		super();
		this.tripBookingId = tripBookingId;
		this.driver = driver;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.stattus = stattus;
		this.distanceInKM = distanceInKM;
		this.bill = bill;
		this.customer = customer;
	}

	public int getTripBookingId() {
		return tripBookingId;
	}

	public void setTripBookingId(int tripBookingId) {
		this.tripBookingId = tripBookingId;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(String fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public String getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(String toDateTime) {
		this.toDateTime = toDateTime;
	}

	public boolean getStattus() {
		return stattus;
	}

	public void setStattus(boolean stattus) {
		this.stattus = stattus;
	}

	public float getDistanceInKM() {
		return distanceInKM;
	}

	public void setDistanceInKM(float distanceInKM) {
		this.distanceInKM = distanceInKM;
	}

	public float getBill() {
		return bill;
	}

	public void setBill(float bill) {
		this.bill = bill;
	}

	public Set<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(Set<Customer> customer) {
		this.customer = customer;
	}
	
	

}
