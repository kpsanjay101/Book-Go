package com.masai.BookingEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TripBooking {
	@Id
	private int tripBookingId;
	private int customerId;
	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Driver driver;
	private String fromLocation;
	private String toLocation;
//	private LocalDateTime fromDateTime;
//	private LocalDateTime toDateTime;
	private String fromDateTime;
	private String toDateTime;
	private boolean stattus;
	private float distanceInKM;
	private float bill;
	public TripBooking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TripBooking(int tripBookingId, int customerId, Driver driver, String fromLocation, String toLocation,
			String fromDateTime, String toDateTime, boolean stattus, float distanceInKM, float bill) {
		super();
		this.tripBookingId = tripBookingId;
		this.customerId = customerId;
		this.driver = driver;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.stattus = stattus;
		this.distanceInKM = distanceInKM;
		this.bill = bill;
	}
	public int getTripBookingId() {
		return tripBookingId;
	}
	public void setTripBookingId(int tripBookingId) {
		this.tripBookingId = tripBookingId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
	public boolean isStattus() {
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
	@Override
	public String toString() {
		return "TripBooking [tripBookingId=" + tripBookingId + ", customerId=" + customerId + ", driver=" + driver
				+ ", fromLocation=" + fromLocation + ", toLocation=" + toLocation + ", fromDateTime=" + fromDateTime
				+ ", toDateTime=" + toDateTime + ", stattus=" + stattus + ", distanceInKM=" + distanceInKM + ", bill="
				+ bill + "]";
	}
	
	

}
