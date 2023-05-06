package com.masai.BookingEntity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
/**
 * @ManyToMany
    @JoinTable(
        name = "trip_booking_customers",
        joinColumns = @JoinColumn(name = "trip_booking_id"),
        inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
 * @author hp
 *
 */
@Entity
public class Customer extends User{
	@Column(unique = true)
	private int customerId;
	
	@ManyToMany
    @JoinTable(
        name = "trip_booking_customers",
        joinColumns = @JoinColumn(name = "tripBookingId"),
        inverseJoinColumns = @JoinColumn(name = "customerId")
    )
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name="customer_details",joinColumns = {@JoinColumn(referencedColumnName = "tripBookingId")},inverseJoinColumns = {@JoinColumn(referencedColumnName = "customerId")})
	private Set<TripBooking> tripBooking;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Customer(String userName, String password, String address, String mobileNo, String email, int customerId,
			Set<TripBooking> tripBooking) {
		super(userName, password, address, mobileNo, email);
		this.customerId = customerId;
		this.tripBooking = tripBooking;
	}



	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Set<TripBooking> getTripBooking() {
		return tripBooking;
	}

	public void setTripBooking(Set<TripBooking> tripBooking) {
		this.tripBooking = tripBooking;
	}

	
	
	

}
