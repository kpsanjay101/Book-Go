package com.masai.BookingEntity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
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
//@NamedNativeQuery(
//	    name = "",
//	    query = "SELECT * FROM table_name WHERE column_name = :parameter",
//	    resultClass = EntityClassName.class
//	)
public class Customer extends User{
	@Column(unique = true)
	private int customerId;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "trip_booking_customers",
        joinColumns = @JoinColumn(name = "customerId"),
        inverseJoinColumns = @JoinColumn(name = "tripBookingId")
    )	
	private Set<TripBooking> tripBooking;
//	@OneToMany
//	private Cab cab;
	
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
