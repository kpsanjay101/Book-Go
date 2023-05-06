package com.masai.BookingUI;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.masai.AllServices.All_Service_Class;
import com.masai.BookingDAO.CabBookingDao;
import com.masai.BookingDAO.CabBookingDaoImpl;
import com.masai.BookingService.BookingServise;
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
			String str = BookingServise.loginAdminPage(user_name, password);
			System.out.println();
			System.out.println(str);
			System.out.println();
			All_Service_Class. showTheServices(sc);
		}catch(Exception ex) {
			System.out.println();
			System.out.println(ex.getMessage());
			System.out.println();
		}
	}
	
	static void viewListOfCabFromCustomerSide() {
		BookingServise.viewListOfCabCustomerSide();
	}
	
	static void loginToCustomer(Scanner sc) {
		System.out.print("Enter User Name ");
		String user_name = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		try {
			String str = BookingServise.loginCustomerPage(user_name, password);
			System.out.println();
			System.out.println(str);
			System.out.println();
			int choice = 0;
			do {
				System.out.println("1.View The List of Cab");
				System.out.println("2.Book Cab");
				System.out.println("0.Exit!");
				System.out.print("Enter Your Choice ");
				choice = sc.nextInt();
				
				switch(choice) {
				case 1:
					viewListOfCabFromCustomerSide();
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
		int customerId = sc.nextInt();
		CabBookingDao cabDao = new CabBookingDaoImpl();
		try {
			cabDao.addCustomer(user_name, password, address, mobileNumber, email,customerId);
			System.out.println("Customer added Successfully");
			
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
				int adminChoice = 0;
				do {
					System.out.println("1.Login to existing account");
					System.out.println("0.Exit !");
					System.out.print("Enter Your Choice ");
					
					
					while(true) {
						try {
							adminChoice = sc.nextInt();
							System.out.println();
							break;
						}catch(InputMismatchException ex) {
							System.out.print("Invalid Input. Please Inter Integer Value :- ");
							sc.nextLine();
						}
					}
					
					switch(adminChoice) {
					case 1:
						logingToAdmin(sc);
						break;
					
					case 0:
						System.out.println("Please Explore Other Functionality");
						break;
					default :
						System.out.println("Enter Correct choice");
					}
					
				}while(adminChoice != 0);
				
			case 2:
				int customerChoice = 0;
				do {
					System.out.println("1.Login to existing account");
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
						System.out.println("Please Explore Other Functionality");
						break;
					}
					
					
					
				}while(customerChoice != 0);
				
				
				
			}
			
		}while(choice != 0);
		sc.close();
	}

}
