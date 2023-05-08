package com.masai.BookingUI;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.masai.AllServices.All_Admin_Services_Class;
import com.masai.BookingDAO.CabBookingDao;
import com.masai.BookingDAO.CabBookingDaoImpl;
import com.masai.BookingEntity.Cab;
import com.masai.BookingEntity.Customer;
import com.masai.BookingEntity.TripBooking;
import com.masai.BookingService.BookingServices;
import com.masai.BookingService.CustomerService;
import com.masai.BookingService.CustomerServiceImpl;
import com.masai.Exception.InterIntegerValueException;

import jakarta.persistence.PersistenceException;

public class UIMain {
	

	
	static void addCabAndDetails() {
		
		CabBookingDao cabBookinDao = new CabBookingDaoImpl();
		cabBookinDao.addCabAndDetails();
	}
		
	public static void logingToAdmin(Scanner sc) {
		System.out.print("Enter User Name ");
		String user_name = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		try {
			String str = BookingServices.loginAdminPage(user_name, password);
			System.out.println();
			System.out.println(str);
			System.out.println();
			All_Admin_Services_Class. showTheServices(sc);
		}catch(Exception ex) {
			System.out.println();
			System.out.println(ex.getMessage());
			System.out.println();
		}
	}
	
	static void viewListOfCabFromCustomerSide() {
		BookingServices.viewListOfCabCustomerSide();
	}
	
	static void updateYourAccountCus(Scanner sc) {
		String address = "";
		String password = "";
		String email = "";
		String mobileNumber = "";

		System.out.print("Enter User name ");
		String user_name = sc.next();
		System.out.println("You want to Change your Address");
		System.out.print("Enter your Choice(yes/no):- ");
		String addChoice = sc.next().toUpperCase();
		if(addChoice.equals("YES")) {
			System.out.print("Enter Your Updated Address: ");
			address = sc.next();
		}
		
		System.out.println("You want to Change your Password");
		System.out.print("Enter your choice:- ");
		String passChoice = sc.next().toUpperCase();
		if(passChoice.equals("YES")) {
			System.out.print("Enter Your Updated Password: ");
			password = sc.next();
		}
		
		System.out.println("You want to Change your Email");
		System.out.print("Enter your choice:- ");
		String emailChoice = sc.next().toUpperCase();
		if(emailChoice.equals("YES")) {
			System.out.print("Enter Your Updated Email: ");
			email = sc.next();
		}	
	
		System.out.println("You want to Change your Mobile Number");
		System.out.print("Enter your choice:- ");
		String mbChoice = sc.next().toUpperCase();
		if(mbChoice.equals("YES")) {
			System.out.print("Enter Your Updated Mobile Number: ");
			mobileNumber = sc.next();
		}
		
		
		
		CabBookingDao cabDao = new CabBookingDaoImpl();
		try {
			cabDao.updateYourAccount(user_name,password, address, mobileNumber, email);
			System.out.println();
			System.out.println("Account Updated Successfully");
			System.out.println();
		}catch(PersistenceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void bookCab(Scanner sc) {
		List<Cab> list = null;
		System.out.println();
		CabBookingDao cabDao = new CabBookingDaoImpl();
		
		try {
			list = cabDao.giveAvailableCab();
			System.out.println();
			for(Cab cab : list) {
				System.out.println("|CabId: "+cab.getCabId()+"| Type: "+cab.getCarType()+"| Per_KM_Rate: "+cab.getPerKmRate()+"| Driver_Id: "+cab.getDriver().getUserName());
			    System.out.println();
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.print("Enter Your userName ");
		String userName = sc.next();
		System.out.print("Enter any one of the Driver_id ");
		String driverId = sc.next();
		System.out.print("Enter Trip_booking_id ");
		int bookingId;
		while(true) {
			try {
				bookingId = sc.nextInt();
				System.out.println();
				break;
			}catch(InputMismatchException ex) {
				System.out.print("Invalid Input. Please Inter Integer Value :- ");
				sc.nextLine();
			}
		}
		
		System.out.println("****************************************");
		System.out.println("Please Remember your Trip Booking_id to see your booking details");
		System.out.println("****************************************");
		System.out.print("Enter From Date and Time ");
		String fDateTime = sc.next();
		System.out.print("Enter End Date and Time ");
		String eDateTime = sc.next();
		System.out.print("Enter From Location ");
		String fLocation = sc.next();
		System.out.print("Enter To Location ");
		String tLocation = sc.next();
		System.out.print("Enter Distence in KM ");
		float distence = sc.nextFloat();
		
		CabBookingDao cabDao1 = new CabBookingDaoImpl();
		try {
			System.out.println();
			cabDao1.bookAvailableCab(userName,driverId,bookingId,fDateTime,eDateTime,fLocation,tLocation,distence);
			System.out.println("Booking Confirmed!");
		    
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	static void seeBokedCab(Scanner sc) {
		System.out.print("Enter your Trip Booking Id ");
		int tripId = sc.nextInt();
		CabBookingDao cabDao = new CabBookingDaoImpl();
		try {
			
			TripBooking tripB = cabDao.viewBookedCab(tripId);
			System.out.println();
			System.out.println("| Trip_Id: "+tripB.getTripBookingId()+" | Driver_Id "+tripB.getDriver().getUserName()+" | From_Location "+tripB.getFromLocation()+" | To_Location "+tripB.getToLocation()+" | Start Date_Time "+tripB.getFromDateTime()+" | End Date_Time "+tripB.getToDateTime()+" | Total_Amount "+tripB.getBill()+" | Status "+tripB.getStattus());
		    System.out.println();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void loginToCustomer(Scanner sc) {
		System.out.print("Enter User Name ");
		String user_name = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		try {
			String str = BookingServices.loginCustomerPage(user_name, password);
			System.out.println();
			System.out.println(str);
			System.out.println();
			int choice = 0;
			do {
				System.out.println("1.View The List of Cab");
				System.out.println("2.Book Cab");
				System.out.println("3.See Your Booked Cab");
				System.out.println("4.Update Your Account");
				System.out.println("0.Exit!");
				System.out.print("Enter Your Choice ");
				
				while(true) {
					try {
						choice = sc.nextInt();
						System.out.println();
						break;
					}catch(InputMismatchException ex) {
						System.out.print("Invalid Input. Please Inter Integer Value :- ");
						sc.nextLine();
					}
				}
				switch(choice) {
				case 1:
					viewListOfCabFromCustomerSide();
					break;
				case 2:
					bookCab(sc);
					break;
				case 3:
					seeBokedCab(sc);
					break;
				case 4:
					updateYourAccountCus(sc);
					break;
				case 0:
					System.out.println("Thank For Using Our Services!");
					break;
				}
				
			}while(choice != 0);
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	static void addCustomerAccount(Scanner sc) {
		System.out.print("Enter username ");
		String user_name = sc.next();
		System.out.print("Enter Password ");
		String password = sc.next();
		System.out.print("Enter address ");
		String address = sc.next();
		System.out.print("Enter email ");
		String email = sc.next();		
		System.out.print("Enter Mobile Number ");
		String mobileNumber = sc.next();		
		System.out.print("Enter customer Id ");
		int customerId=0;
		while(true) {
			try {
				customerId = sc.nextInt();
				System.out.println();
				break;
			}catch(InputMismatchException ex) {
				System.out.print("Invalid Input. Please Inter Integer Value :- ");
				sc.nextLine();
			}
		}
		
		
		Customer cust = new Customer(user_name, password, address, mobileNumber, email, customerId, null);
		CabBookingDao cabDao = new CabBookingDaoImpl();
		try {
			cabDao.addCustomer(cust);
			System.out.println("Account Added Successfully");
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	public static void main(String[] args)  throws InterIntegerValueException{
//		addCabAndDetails();
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		do {
			System.out.println("1.Admin Side");
			System.out.println("2.Customer Side");
			System.out.println("0.Exit");
			System.out.print("Enter Selection ");
			
			while(true) {
				try {
					choice = sc.nextInt();
					System.out.println();
					break;
				}catch(InputMismatchException ex) {
					System.out.print("Invalid Input. Please Inter Integer Value :- ");
					sc.nextLine();
				}
			}
			
			switch(choice) {
			case 1:
				logingToAdmin(sc);
				break;
			case 2:
				int customerChoice = 0;
				do {
					System.out.println("1.LogIn To Account");
					System.out.println("2.Make new Account");
					System.out.println("0.Exit !");
					System.out.print("Enter Your Choice ");
		   			while(true) {
						try {
							customerChoice = sc.nextInt();
							System.out.println();
							break;
						}catch(InputMismatchException ex) {
							System.out.print("Invalid Input. Please Inter Integer Value :- ");
							sc.nextLine();
						}
					}
					
					switch(customerChoice) {
					case 1:
						loginToCustomer(sc);
						break;
					case 2:
						addCustomerAccount(sc);
						break;
					case 0:
						System.out.println("Thank You Visit again!");
						break;
					}
					
					
					
				}while(customerChoice != 0);
				
				
				
			}
			
		}while(choice != 0);
		sc.close();
	}

}
