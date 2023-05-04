package com.masai.BookingUI;

import java.util.Scanner;

import com.masai.BookingDAO.CabBookingDao;
import com.masai.BookingDAO.CabBookingDaoImpl;
import com.masai.BookingService.BookingServise;

import jakarta.persistence.PersistenceException;

public class UIMain {
	

	
	static void addCabAndDetails() {
		
		CabBookingDao cabBookinDao = new CabBookingDaoImpl();
		cabBookinDao.addCabAndDetails();
	}
		
	static void logingToAdmin(Scanner sc) {
		System.out.print("Enter User Name ");
		String user_name = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		try {
			String str = BookingServise.loginAdminPage(user_name, password);
			System.out.println(str);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	static void addAdmin(Scanner sc) {
		System.out.print("Enter username ");
		String user_name = sc.next();
		System.out.print("Enter address ");
		String address = sc.next();
		System.out.println("Enter email ");
		String email = sc.next();
		System.out.print("Enter Mobile Number ");
		String mobileNumber = sc.next();
		System.out.print("Enter Password ");
		String password = sc.next();
		System.out.print("Enter admin Id ");
		int admin_id = sc.nextInt();
		CabBookingDao cabDao = new CabBookingDaoImpl();
		try {
			cabDao.addAdmin(user_name, password, address, mobileNumber, email, admin_id);
			System.out.println("Admin added Successfully");
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		
				
	}
	
	
	
	public static void main(String[] args) {
//		addCabAndDetails();
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		do {
			System.out.println("1.Admin Side");
			System.out.println("2.Customer Side");
			System.out.println("0.Exit");
			System.out.print("Enter Selection ");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				int loginChoice = 0;
				do {
					System.out.println("1.Login to existing account");
					System.out.println("2.Make new Account");
					System.out.println("0.Exit !");
					System.out.print("Enter Your Choice ");
					loginChoice = sc.nextInt();
					
					switch(loginChoice) {
					case 1:
						logingToAdmin(sc);
						break;
					case 2:
						addAdmin(sc);
						break;
					}
					
				}while(loginChoice != 0);
				
			}
			
			
		}while(choice != 0);
		sc.close();
	}

}
