package com.masaischool.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdministrtorFeatures {
	
	public static void addBusIntoDatabase() {
		
		BufferedReader br= null;
		try {
		 br= new BufferedReader(new InputStreamReader(System.in));
		 
		 System.out.println("Enter Bus Number:");
		 String bnum= br.readLine();
		 System.out.println("Enter Bus Name:");
		 String bname= br.readLine();
		 System.out.println("Enter Bus Starting point:");
		 String spName=br.readLine();
		 System.out.println("Enter Bus Destination:");
		 String desName= br.readLine();
		 System.out.println("Enter Bus Type:");
		 String bType= br.readLine();
		 System.out.println("Enter Bus Departur Time(hh:mm:ss) :");
		 LocalTime DTime= LocalTime.parse( br.readLine());
		 System.out.println("Enter Bus Arrival Time(hh:mm:ss)");
		 LocalTime ATime= LocalTime.parse(br.readLine());
		 System.out.println("Total number of seates:");
		 int num= Integer.parseInt(br.readLine());
		 System.out.println("Enter Bus Fare:");
		 int fare= Integer.parseInt(br.readLine());
		}catch(IOException ex) {}
		
		finally {
			try {
				br.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	};
	public static void updateBusDetails() {};
	public static void deleteBusFromDatabase() {};
	public static void viewAllBookings() {};
	public static void viewBookingsForDateRange() {};
	public static void viewBookingByBusName() {};
	public static void viewBookingByMobile() {};
	public static void logout() {};
	
	
	public static void  showOptions(Scanner scn) {
		int option=0;
	     do {
	    	 System.out.println("***********************************************");
	    	 System.out.println("|Enter your choice:                           |");
	    	 System.out.println("|1.ADD BUS INTO DATABASE                      |");
	    	 System.out.println("|2.UPDATE BUS DETAILS                         |");
	    	 System.out.println("|3.DELETE BUS FROM DATABASE                   |");
	    	 System.out.println("|4.VIEW ALL BOOKINGS                          |");
	    	 System.out.println("|5.VIEW BOOKINGS FOR A DATE RANGE             |");
	    	 System.out.println("|6.VIEW BOOKINGS BY BUS NAME                  |");
	    	 System.out.println("|7.VIEW BOOKINGS BY MOBILE NUMBER OF PASSENGER|");
	    	 System.out.println("|8.LOGOUT                                     |");
	    	 System.out.println("***********************************************");
	    	 while(true)
	    	 {
		    	 try {
		    	    option= scn.nextInt();
		    	    break;
		    	 }catch(InputMismatchException ex) {
		    		 System.out.println("YOU HAVE ENTERED WRONG INPUT , PLEASE TRY AGAING WITH A DIGTI");
		    		 scn.nextLine();
		    	 }
	    	 }
	    	 
	    	 switch(option) {
	    	 case 1:
	    		 addBusIntoDatabase();
	    		 break;
	    	 case 2:
	    		  updateBusDetails();
	    		 break;
	    	 case 3:
	    		 deleteBusFromDatabase();
	    		 break;
	    	 case 4:
	    		 viewAllBookings();
	    		 break;
	    	 case 5:
	    		 viewBookingsForDateRange();
	    		 break;
	    	 case 6:
	    		 viewBookingByBusName();
	    		 break;
	    	 case 7:
	    		 viewBookingByMobile();
	    		 break;
	    	 case 8:
	    		  logout();
	    		 break;
	    	default:
	    		  System.out.println("INVALID INPUT , PLEASE TRY AGAIN");
	    	 }
	    	 
	     }while(option!=0);
	}
	
}
