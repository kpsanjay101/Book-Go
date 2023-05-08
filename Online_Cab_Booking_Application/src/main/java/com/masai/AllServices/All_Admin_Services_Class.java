package com.masai.AllServices;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.masai.BookingDAO.CabBookingDao;
import com.masai.BookingDAO.CabBookingDaoImpl;
import com.masai.BookingEntity.Admin;
import com.masai.BookingEntity.Cab;
import com.masai.BookingEntity.Customer;
import com.masai.BookingEntity.Driver;
import com.masai.BookingEntity.TripBooking;
import com.masai.BookingService.AdminService;
import com.masai.BookingService.AdminServiceImpl;
import com.masai.BookingService.CabService;
import com.masai.BookingService.CabServiceImpl;
import com.masai.BookingService.CustomerService;
import com.masai.BookingService.CustomerServiceImpl;
import com.masai.BookingService.DriverService;
import com.masai.BookingService.DriverServiceImpl;
import com.masai.BookingService.TripBookingService;
import com.masai.BookingService.TripBookingServiceImpl;

import jakarta.persistence.PersistenceException;

public class All_Admin_Services_Class {
	
	private static final boolean No = false;







	public static void showTheServices(Scanner sc) {
		int choice = 0;
		do {
			System.out.println("1.Admin Service");
			System.out.println("2.Customer Service");
			System.out.println("3.Driver Servicee");
			System.out.println("4.Trip Booking Service");
			System.out.println("5.Cab Service");
			System.out.println("0.Exit!");
			System.out.print("Choose Service:- ");
			
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
				adminService(sc);
				
				break;
			case 2:
				customerService(sc);
				break;
			case 3:
				driverService(sc);
				break;
			case 4:
				tripBookingService(sc);
				break;
			case 5:
				cabService(sc);
				break;
			case 0:
				System.out.println("Visist Again ! ");
				break;
			default :
				System.out.println("Choose Correct Selection");
			}
			
			
		}while(choice != 0);
	}
	
	static void insertCustomer(Scanner sc) {
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
		int customerId = sc.nextInt();
		CabBookingDao cabDao = new CabBookingDaoImpl();
//		Customer cust = new Customer(user_name, password, address, mobileNumber, email, customerId);
		try {
			CustomerService custSer = new CustomerServiceImpl();
//			custSer.insertCustomer(cust);
			System.out.println("Customer added Successfully");
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
//	static void updateCustomer(Scanner sc) {
//		String address = "";
//		String password = "";
//		String email = "";
//		String mobileNumber = "";
//	
//		System.out.println("You want to Change your Address");
//		System.out.print("Enter your choice:- ");
//		String addChoice = sc.next().toUpperCase();
//		if(addChoice.equals("YES")) {
//			System.out.print("Enter Your Updated Address: ");
//			address = sc.next();
//		}
//		
//		System.out.println("You want to Change your Password");
//		System.out.print("Enter your choice:- ");
//		String passChoice = sc.next().toUpperCase();
//		if(passChoice.equals("YES")) {
//			System.out.print("Enter Your Updated Password: ");
//			password = sc.next();
//		}
//		
//		System.out.println("You want to Change your Email");
//		System.out.print("Enter your choice:- ");
//		String emailChoice = sc.next().toUpperCase();
//		if(emailChoice.equals("YES")) {
//			System.out.print("Enter Your Updated Email: ");
//			email = sc.next();
//		}	
//	
//		System.out.println("You want to Change your Mobile Number");
//		System.out.print("Enter your choice:- ");
//		String mbChoice = sc.next().toUpperCase();
//		if(emailChoice.equals("YES")) {
//			System.out.print("Enter Your Updated Mobile Number: ");
//			mobileNumber = sc.next();
//		}
//		
//		System.out.print("Enter customerId ");
//		int customerId = sc.nextInt();
//		
//		CustomerService custSer = new CustomerServiceImpl();
//		try {
//			custSer.updateCustomer(customerId, address,password, email, mobileNumber);
//			System.out.println();
//			System.out.println("Customer Updated Successfully");
//			System.out.println();
//		}catch(PersistenceException e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
	static void deleteCustomer(Scanner sc) {
		System.out.print("Enter customer id ");
		int id = sc.nextInt();
		CustomerService custSer = new CustomerServiceImpl();
		try {
			System.out.println();
			custSer.deletetCustomer(id);
			System.out.println("Customer Deleted Successfully");
		}catch(PersistenceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void viewAllCustomer() {
		CustomerService custSer = new CustomerServiceImpl();
		try {
			List<Customer> list = custSer.viewCustomer();
			System.out.println();
			for(Customer cus : list) {
				
				System.out.println("customerId "+cus.getCustomerId()+" UserName "+cus.getUserName()+" Address "+cus.getAddress()+" Email "+cus.getEmail()+" Mobile Number "+cus.getMobileNo());
			    System.out.println("***************************************************************************************************");
			}
		}catch(PersistenceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void viewCustomerById(Scanner sc) {
		System.out.print("Enter customer id ");
		int id = sc.nextInt();
		CustomerService custSer = new CustomerServiceImpl();
		try {
			System.out.println();
			Customer cust = custSer.viewCustomerById(id);
			System.out.println("customerId: "+cust.getCustomerId()+" userName: "+cust.getUserName()+" Password: "+cust.getPassword()+" Adress: "+cust.getAddress()+" Email: "+cust.getEmail()+" MobileNo: "+cust.getMobileNo());
	        System.out.println();
	        
		}catch(PersistenceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void customerService(Scanner sc) {
		int choice = 0;
		do {
			System.out.println("1.View All Customers");
			System.out.println("2.Delete Customer");
			System.out.println("3.View Customer using Id");
			System.out.println("4.Validate Customer");
			System.out.println("0.Exit!");
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
				viewAllCustomer();
				break;
			case 2:
				deleteCustomer(sc);
				break;
			case 3:
				viewCustomerById(sc);
				break;
			case 4:
//				validateCustomer();
				break;
			case 0:
				System.out.println("Thank You Visist again");
				break;
			default:
				System.out.println("Please Enter Correct Selection");
			}
			
			
			
		}while(choice != 0);
	}
	
	static void insertDriver(Scanner sc) {
		Driver dr = new Driver();
		System.out.print("Enter userName ");
		String user_name = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		System.out.print("Enter address ");
		String address = sc.next();
		System.out.print("Enter email ");
		String email = sc.next();
		System.out.print("Enter Mobile Number ");
		String mobileNO = sc.next();
		System.out.print("Enter Driver Licence ");
		String licence = sc.next();
		System.out.print("Enter rating ");
		float rating = sc.nextFloat();
		System.out.print("Enter driver Id ");
		int driverId = sc.nextInt();
		
		dr.setUserName(user_name);
		dr.setPassword(password);
		dr.setAddress(address);
		dr.setEmail(email);
		dr.setMobileNo(mobileNO);
		dr.setLicenceNo(licence);
		dr.setRating(rating);
		dr.setDriverId(driverId);
		dr.setCab(null);
		DriverService drSer = new DriverServiceImpl();
		try {
			drSer.insertDriver(dr);
			System.out.println();
			System.out.println("Driver Added Successfully");
		}catch(PersistenceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	 static void updateDriver(Scanner sc) {
			
			System.out.print("Enter address ");
			String address = sc.next();
			System.out.print("Enter email ");
			String email = sc.next();
			System.out.print("Enter Mobile Number ");
			String mobileNo = sc.next();
			System.out.print("Enter rating ");
			float rating = sc.nextFloat();
			System.out.print("Enter driver Id ");
			int driverId = sc.nextInt();
			
			DriverService drSer = new DriverServiceImpl();
			System.out.println();
			try {
				drSer.updateDriver(driverId,address,email,mobileNo,rating);
				System.out.println("Driver Update Successfully");
				System.out.println();
			}catch(PersistenceException e) {
				System.out.println(e.getMessage());
			}
			
		}
	 static void deleteDriverUsingId(Scanner sc) {
			
			System.out.print("Enter driver Id ");
			int driverId = sc.nextInt();
			
			DriverService drSer = new DriverServiceImpl();
			System.out.println();
			try {
				drSer.deleteDriver(driverId);
				System.out.println("Driver Update Successfully");
				System.out.println();
			}catch(PersistenceException e) {
				System.out.println(e.getMessage());
			}
			
		}
	
	 static void viewDriverById(Scanner sc) {
			
			System.out.print("Enter driver Id ");
			int driverId = sc.nextInt();
			
			DriverService drSer = new DriverServiceImpl();
			System.out.println();
			try {
				Driver dr = drSer.viewDriverById(driverId);
				System.out.println();
				System.out.println("DriverId: "+dr.getDriverId() +" userName: "+dr.getUserName()+" Password: "+dr.getPassword()+" Address: "+dr.getAddress()+" Email: "+dr.getEmail()+" MobileNo: "+dr.getMobileNo()+" Rating: "+dr.getRating()+" Licence: "+dr.getLicenceNo());
				System.out.println();
			}catch(PersistenceException e) {
				System.out.println(e.getMessage());
			}
			
		}
	
	 static void viewBestDriver() {
			DriverService drSer = new DriverServiceImpl();
			
			try {
				List<Driver> list = drSer.viewBestDriver();
				System.out.println();
				for(Driver dr : list) {
					System.out.println("DriverId: "+dr.getDriverId() +" userName: "+dr.getUserName()+" Password: "+dr.getPassword()+" Address: "+dr.getAddress()+" Email: "+dr.getEmail()+" MobileNo: "+dr.getMobileNo()+" Rating: "+dr.getRating()+" Licence: "+dr.getLicenceNo());
			        System.out.println("**********************************************************************************************************************************");	
				}
			}catch(PersistenceException e) {
				System.out.println(e.getMessage());
			}
		}
	 
	static void driverService(Scanner sc) {
		int choice = 0;
		do {
			System.out.println("1.Insert Driver");
			System.out.println("2.Update Driver");
			System.out.println("3.Delete Driver");
			System.out.println("4.View Best Driver");
			System.out.println("5.View Driver by driver Id");
			System.out.println("0.Exit!");
			System.out.print("Enter Selection ");

			while(true) {
				try {
					choice = sc.nextInt();
					System.out.println();
					break;
				}catch(InputMismatchException ex) {
					System.out.print("Invalid Input. Please Enter Integer Value :- ");
					sc.nextLine();
				}
			}
			
			switch(choice) {
			case 1:
				insertDriver(sc);
				break;
			case 2:
				updateDriver(sc);
				break;
			case 3:
				deleteDriverUsingId(sc);
				break;
			case 4:
				viewBestDriver();
				break;
			case 5:
				viewDriverById(sc);
				break;
			}
			
			
		}while(choice != 0);
		
	}
	
	public static void insertAdmin(Scanner sc) {
		System.out.print("Enter User Name ");
		String user_name = sc.next();
		System.out.print("Enter Password ");
		String password = sc.next();
		System.out.print("Enter Address ");
		String address = sc.next();
		System.out.print("Enter Email ");
		String email = sc.next();
		System.out.print("Enter Mobile Number ");
		String mobileNo = sc.next();
		System.out.print("Enter Admin Id ");
		int admin_id = sc.nextInt();
		
		Admin admin = new Admin(user_name, password, address, mobileNo, email, admin_id);
		AdminService adminSer = new AdminServiceImpl();
		try {
			System.out.println();
			adminSer.insertAdmin(admin);
			System.out.println("Admin Added Successfully");
			System.out.println();
			
		}catch(PersistenceException ex) {
			System.out.println(ex.getMessage());
		}
		
	}
   
	public static void updateAdmin(Scanner sc) {
		
		
		String address = "";
		String password = "";
		String email = "";
		String mobileNumber = "";
		System.out.print("Enter Admin Id ");
		int admin_id = sc.nextInt();
		
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
		
		
		
		AdminService admSer = new AdminServiceImpl();
		try {
			admSer.updateAdmin(password, address, email, mobileNumber, admin_id);
			System.out.println();
			System.out.println("Account Updated Successfully");
			System.out.println();
		}catch(PersistenceException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public static void deleteAdmin(Scanner sc) {
		
		System.out.print("Enter Admin Id ");
		int adminId = sc.nextInt();
		
		AdminService adSer = new AdminServiceImpl();
		System.out.println();
		try {
			adSer.deleteAdmin(adminId);
			System.out.println("Admin Deleted Successfully");
			System.out.println();
		}catch(PersistenceException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void getAllTrips(Scanner sc) {
		System.out.print("Enter customer user name ");
		String user_name = sc.next();
		
		AdminService adSer = new AdminServiceImpl();
		try {
			
			List<TripBooking> list = adSer.getAllTrips(user_name);
			System.out.println();
			for(TripBooking trip : list) {
				
				System.out.println("|Trip_Id "+trip.getTripBookingId()+" | Driver_Id "+trip.getDriver().getUserName()+" | Start Date&Time "+trip.getFromDateTime()+" | End Date&Time "+trip.getToDateTime()+" | From "+trip.getFromLocation()+" | To "+trip.getToLocation()+" | Distence "+trip.getDistanceInKM()+" | Bill_Amount "+trip.getBill()+" | Status "+trip.getStattus());
			    System.out.println();
			}
		}catch(PersistenceException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public static void getTripCabWise(Scanner sc) {
		// TODO Auto-generated method stub
		
	}
	
	public static void getTripCustomerWise(Scanner sc) {
		// TODO Auto-generated method stub
		
	}
	
	public static void getTripDateWise(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	public static void getAllTripForDay(Scanner sc) {
		// TODO Auto-generated method stub
		
	}
	
	static void adminService(Scanner sc) {
		int choice = 0;
		do {
			System.out.println("1.Insert Admin");
			System.out.println("2.Update Admin");
			System.out.println("3.Delete Admin");
			System.out.println("4.Get All Trips By Using Customer user_Name");
			System.out.println("5.Get Trips Cabwise");
			System.out.println("6.Get Trips Customer Wise");
			System.out.println("7.Get Trips Date Wise");
			System.out.println("8.Get All Trips For Day");
			System.out.println("0.Exit!");
			System.out.print("Enter Selection ");
			
			while(true) {
				try {
					choice = sc.nextInt();
					break;
				}catch(InputMismatchException ex) {
					System.out.print("Invalid Input. Please Enter Integer Value :- ");
					sc.nextLine();
				}
			}
			
			switch(choice) {
			case 1:
				insertAdmin(sc);
				break;
			case 2:
				updateAdmin(sc);
				break;
			case 3:
				deleteAdmin(sc);
				break;
			case 4:
				getAllTrips(sc);
				break;
			case 5:
				getTripCabWise(sc);
				break;
			case 6:
				getTripCustomerWise(sc);
				break;
			case 7:
				getTripDateWise(sc);
				break;
			case 8:
				getAllTripForDay(sc);
				break;
			case 0:
				System.out.println("Thank You For Using Our Services");
				break;
			default :
				System.out.println("Please Enter Correct Selection");
			}
			
			
		}while(choice != 0);
		
	}
	
	static void insertTripBooking(Scanner sc) {
		System.out.print("Enter Trip Id ");
		int trpId = sc.nextInt();
		System.out.print("Enter Driver User Name ");
		String driverUserName = sc.next();
		System.out.print("Enter Distance ");
		float distance = sc.nextFloat();
		System.out.print("Enter From Date&Time ");
		String fDateTime = sc.next();
		System.out.print("Enter End Date&Time ");
		String eDateTime = sc.next();
		System.out.print("Enter From Location ");
		String fLocation = sc.next();
		System.out.print("Enter To Location ");
		String eLocation = sc.next();
		System.out.print("Enter Status ");
		boolean status = sc.nextBoolean();
		
		TripBooking tripB = new TripBooking();
		
		tripB.setTripBookingId(trpId);
		tripB.setFromLocation(fLocation);
		tripB.setToLocation(eLocation);
		tripB.setFromDateTime(fDateTime);
		tripB.setToDateTime(eDateTime);
		tripB.setDistanceInKM(distance);
		tripB.setStattus(status);
		
		TripBookingService tripSer = new TripBookingServiceImpl();
		try {
			tripSer.insertTripBooking(tripB,driverUserName);
			System.out.println();
			System.out.println("Trip Booking Added Succefully");
			System.out.println();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
   static void updateStatusTripBooking(Scanner sc) {
		System.out.print("Enter trip_id ");
		int tripId = sc.nextInt();
		System.out.print("Enter Status ");
		boolean status = sc.nextBoolean();
		
		TripBookingService tripSer = new TripBookingServiceImpl();
		try {
			System.out.println();
			tripSer.updateTripBooking(tripId, status);
			System.out.println("Status Updated ");
			System.out.println();
		}catch(PersistenceException ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
    static void deleteTripBookingById(Scanner sc) {
    	
    	System.out.print("Enter Trip Id ");
    	int tripId = sc.nextInt();
    	TripBookingService tripSer = new TripBookingServiceImpl();
    	try {
    		System.out.println();
    		tripSer.deleteTripBooking(tripId);
    		System.out.println("TripBooking Deleted");
    	}catch(PersistenceException e) {
    		System.out.println(e.getMessage());
    	}
    }

	

	static void tripBookingService(Scanner sc) {
		int choice = 0;
		do {
			System.out.println("1.Add TripBooking");
			System.out.println("2.Update TripBooking Status");
			System.out.println("3.Delete TripBooking");
			System.out.println("0.Exit!");
			System.out.print("Enter Selection ");
			while(true) {
				try {
					choice = sc.nextInt();
					break;
				}catch(InputMismatchException e) {
					System.out.println("Please enter an Integer Value");
					sc.nextLine();
				}
			}
			
			switch(choice) {
			case 1:
				insertTripBooking(sc);
				break;
			case 2:
				updateStatusTripBooking(sc);
				break;
			case 3:
				deleteTripBookingById(sc);
				break;
			case 0:
				System.out.println("Thank You !");
				break;
			default :
				System.out.println("Choose Valid Selection");
			}
			
			
		}while(choice != 0);
	}

	static void insertCab(Scanner sc){
		System.out.print("Enter cab_Id ");
		int cab_id = sc.nextInt();
		System.out.print("Enter Cab Type ");
		String cabType = sc.next();
		System.out.print("Enter Avilabality ");
		boolean isAvailable = sc.nextBoolean();
		System.out.print("Enter Per KM Price ");
		float price = sc.nextFloat();
		System.out.print("Enter Cab Rating ");
		float rating = sc.nextFloat();
		System.out.print("Enter Driver user name ");
		String userName = sc.next();
		
		CabService cabSer = new CabServiceImpl();
		try {
			System.out.println();
			cabSer.insertCab(cab_id, cabType, isAvailable, price, rating, userName);
			System.out.println("Cab Added Successfully");
			System.out.println();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}
//	 cabId | available            | carType | perKmRate | rating
	static void updateCabById(Scanner sc){
		float price = 0.0f;
		float rating = 0.0f;
		
		System.out.print("Enter cab_id ");
		int cabId = sc.nextInt();
		System.out.print("Enter availability ");
		boolean isAvailable = sc.nextBoolean();
			
	
		
		System.out.println("Want to Change Per KM Price");
		System.out.print("Enter yes, if you want to change ");
		String pChoice = sc.next().toUpperCase();
		if(pChoice.equals("YES")) {
			System.out.print("Enter Per KM Price ");
			price = sc.nextFloat();
		}
		
		System.out.println("Want to Change Rating");
		System.out.print("Enter yes, if you want to change ");
		String rChoice = sc.next().toUpperCase();
		if(rChoice.equals("YES")) {
			System.out.print("Enter Rating ");
			rating = sc.nextFloat();
		}
		
		CabService cabSer = new CabServiceImpl();
		
		try {
			System.out.println();
			cabSer.updateCab(cabId, isAvailable, rating, price);
			System.out.println("Cab Updated Successfully");
			System.out.println();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		
	}
	
	static void deleteCabById(Scanner sc){
		System.out.print("Enter cab_id ");
		int cabId = sc.nextInt();
		
		CabService cabSer = new CabServiceImpl();
		try {
			System.out.println();
			cabSer.deleteCab(cabId);
			System.out.println("Cab Deleted Successfully");
			System.out.println();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	 
	static void viewCabOfType(Scanner sc){
		System.out.print("Enter Cab Type ");
		String cabType = sc.next();
		
		CabService cabSer = new CabServiceImpl();
		try {
			List<Cab> list = cabSer.viewCabsOfType(cabType);
			System.out.println();
			for(Cab c : list) {
				System.out.println("|Cab_Id "+c.getCabId()+" | Available "+c.getAvailable()+" | Car_Type "+c.getCarType()+" | Per_KM_Price "+c.getPerKmRate()+" | Rating "+c.getRating()+" | Driver_Id "+c.getDriver().getUserName());
	            System.out.println();		
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void countCabOfType(Scanner sc) {
		
	}
	
	
	
	
	
	
	
	static void cabService(Scanner sc) {
		int choice = 0;
		
		do {
			System.out.println("1.Add Cab");
			System.out.println("2.Update Cab");
			System.out.println("3.View List of Cab having Same type");
			System.out.println("4.Delete Cab");
			System.out.println("5.Count No of Cab of Particular Type");
			System.out.println("0.Exit!");
			System.out.print("Enter Selection ");
			while(true) {
				try {
					choice = sc.nextInt();
					break;
				}catch(InputMismatchException e) {
					System.out.println("Please enter an Integer value");
					sc.nextLine();
				}
			}

			switch(choice) {
			case 1:
				insertCab(sc);
				break;
			case 2:
				updateCabById(sc);
				break;
			case 3:
				viewCabOfType(sc);
				break;
			case 4:
				 deleteCabById(sc);
				break;
			case 5:
				countCabOfType(sc);
				break;
			case 0:
				System.out.println("Thank You !");
				break;
			default :
				System.out.println("Please enter correct Selection");
			}
			
			
			
			
		}while(choice != 0);
	}
	

}
