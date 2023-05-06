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
import com.masai.BookingService.AdminService;
import com.masai.BookingService.AdminServiceImpl;
import com.masai.BookingService.CustomerService;
import com.masai.BookingService.CustomerServiceImpl;
import com.masai.BookingService.DriverService;
import com.masai.BookingService.DriverServiceImpl;

import jakarta.persistence.PersistenceException;

public class All_Service_Class {
	
	public static void showTheServices(Scanner sc) {
		int choice = 0;
		do {
			System.out.println("1.Customer Service");
			System.out.println("2.Driver Service");
			System.out.println("3.Admin Service");
			System.out.println("4.Trip Booking Service");
			System.out.println("5.Cab Service");
			System.out.println("0.Exit!");
			System.out.print("Enter selection ");
			
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
				customerService(sc);
				break;
			case 2:
				driverService(sc);
				break;
			case 3:
				adminService(sc);
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
	
	static void updateCustomer(Scanner sc) {
		
		System.out.print("Enter address ");
		String address = sc.next();
		System.out.print("Enter email ");
		String email = sc.next();		
		System.out.print("Enter Mobile Number ");
		String mobileNumber = sc.next();
		System.out.print("Enter customerId ");
		int customerId = sc.nextInt();
		
		CustomerService custSer = new CustomerServiceImpl();
		try {
			custSer.updateCustomer(customerId, address, email, mobileNumber);
			System.out.println();
			System.out.println("Customer Updated Successfully");
			System.out.println();
		}catch(PersistenceException e) {
			System.out.println(e.getMessage());
		}
	}
	
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
	
	static void customerService(Scanner sc) {
		int choice = 0;
		do {
			System.out.println("1.Insert The Customer");
			System.out.println("2.Uddate Customer");
			System.out.println("3.Delete Customer");
			System.out.println("4.View All Customers");
			System.out.println("5.View Customer using Id");
			System.out.println("6.Validate Customer");
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
				insertCustomer(sc);
				break;
			case 2:
				updateCustomer(sc);
				break;
			case 3:
				deleteCustomer(sc);
				break;
			case 4:
				viewAllCustomer();
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
		
		System.out.print("Enter Cab Id ");
		int cabId = sc.nextInt();
		System.out.print("Enter Cab Type ");
		String cabType = sc.next();
		System.out.print("Enter cab rating ");
		float cabRating = sc.nextFloat();
		System.out.print("Enter Per KM Price ");
		float pkPrice = sc.nextFloat();
		
		dr.setUserName(user_name);
		dr.setPassword(password);
		dr.setAddress(address);
		dr.setEmail(email);
		dr.setMobileNo(mobileNO);
		dr.setLicenceNo(licence);
		dr.setRating(rating);
		dr.setDriverId(driverId);
		
		Cab cab = new Cab(cabId, cabType, cabRating, pkPrice, dr);
		dr.setCab(cab);
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
		System.out.print("Enter Admin Id ");
		int admin_id = sc.nextInt();
		
		System.out.print("Enter New Password ");
		String password = sc.next();
		System.out.print("Enter New Address ");
		String address = sc.next();
		System.out.print("Enter New Email ");
		String email = sc.next();
		System.out.print("Enter New Mobile Number ");
		String mobileNo = sc.next();
		
		AdminService adminSer = new AdminServiceImpl();
		try {
			System.out.println();
			adminSer.updateAdmin(password, address, email, mobileNo, admin_id);
			System.out.println("Admin Updated Successfully");
			System.out.println();
		}catch(PersistenceException ex) {
			System.out.println(ex.getMessage());
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
	
	public static void getAllTrips() {
		// TODO Auto-generated method stub
		
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
			System.out.println("4.Get All Trips");
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
				getAllTrips();
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
		

	static void tripBookingService(Scanner sc) {
		
	}
	static void cabService(Scanner sc) {
		
	}
	

}
