package com.masai.BookingEntity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Driver extends User{
	private int driverId;
	private String licenceNo;
	private float rating;
	@OneToOne(mappedBy = "driver",cascade = CascadeType.ALL)
	private Cab cab;
	@OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
	private Set<TripBooking> tripBooking;
	
	
	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Driver(String userName, String password, String address, String mobileNo, String email, int driverId,
			String licenceNo, float rating, Cab cab, Set<TripBooking> tripBooking) {
		super(userName, password, address, mobileNo, email);
		this.driverId = driverId;
		this.licenceNo = licenceNo;
		this.rating = rating;
		this.cab = cab;
		this.tripBooking = tripBooking;
	}


	public int getDriverId() {
		return driverId;
	}


	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}


	public String getLicenceNo() {
		return licenceNo;
	}


	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}


	public float getRating() {
		return rating;
	}


	public void setRating(float rating) {
		this.rating = rating;
	}


	public Cab getCab() {
		return cab;
	}


	public void setCab(Cab cab) {
		this.cab = cab;
	}


	public Set<TripBooking> getTripBooking() {
		return tripBooking;
	}


	public void setTripBooking(Set<TripBooking> tripBooking) {
		this.tripBooking = tripBooking;
	}


	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", licenceNo=" + licenceNo + ", rating=" + rating + ", cab=" + cab
				+ ", tripBooking=" + tripBooking + "]";
	}

	
	
	

}
