package com.masai.AllServices;

import java.util.List;
import java.util.Scanner;

import com.masai.BookingDAO.CabBookingDao;
import com.masai.BookingDAO.CabBookingDaoImpl;
import com.masai.BookingEntity.Customer;
import com.masai.BookingService.CustomerService;
import com.masai.BookingService.CustomerServiceImpl;

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
			choice = sc.nextInt();
			
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
		Customer cust = new Customer(user_name, password, address, mobileNumber, email, customerId);
		try {
			CustomerService custSer = new CustomerServiceImpl();
			custSer.insertCustomer(cust);
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
			choice = sc.nextInt();
			
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
	static void driverService(Scanner sc) {
		
	}
	static void adminService(Scanner sc) {
		
	}
	static void tripBookingService(Scanner sc) {
		
	}
	static void cabService(Scanner sc) {
		
	}
	

}
