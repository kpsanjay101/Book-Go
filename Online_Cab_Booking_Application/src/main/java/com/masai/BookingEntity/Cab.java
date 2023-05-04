package com.masai.BookingEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Cab {
	@Id
	private int cabId;
	private String carType;
	private float rating;
	private float perKmRate;
	@OneToOne
	@JoinColumn(name = "id")
	 private Driver driver;
	
	public Cab() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cab(int cabId, String carType, float rating, float perKmRate, Driver driver) {
		super();
		this.cabId = cabId;
		this.carType = carType;
		this.rating = rating;
		this.perKmRate = perKmRate;
		this.driver = driver;
	}

	public int getCabId() {
		return cabId;
	}

	public void setCabId(int cabId) {
		this.cabId = cabId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public float getPerKmRate() {
		return perKmRate;
	}

	public void setPerKmRate(float perKmRate) {
		this.perKmRate = perKmRate;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "Cab [cabId=" + cabId + ", carType=" + carType + ", rating=" + rating + ", perKmRate=" + perKmRate
				+ ", driver=" + driver + "]";
	}

	
	

}
