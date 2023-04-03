package com.masaischool.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.masaischool.dao.*;
import com.masaischool.dto.*;
import com.masaischool.exceptions.RecordNotFoundException;
import com.masaischool.exceptions.SeatsNotAvailableException;
import com.masaischool.exceptions.SomethingWentWrongException;

public class UserFeatures {
	
	
	public static void  viewListOfBuses() {
		UserDao dao= new UserDaoImpl();
		List<Buses> list=null;
		try {
			list = dao.viewListOfBuses();
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		if(list!=null)
		{
			list.forEach(i->System.out.println(i));
		}
	}

	public static void  showOptions(Scanner scn) {
		int option=0;
	     do {
	    	 System.out.println("***********************************************");
	    	 System.out.println("|Enter your choice:                           |");
	    	 System.out.println("|1.VIEW LIST OF BUSES                         |");
	    	 System.out.println("|2.BOOK BUS TICKET                            |");
	    	 System.out.println("|3.VIEW LIST OF BOOKING                       |");
	    	 System.out.println("|4.LOGOUT                                     |");
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
	    		 viewListOfBuses();
	    		 break;
	    	 case 2:
	    		bookBusTicket();
	    		 break;
	    	 case 3:
	    		 viewListOfBookings();
	    		 break;
	    	 case 4:
	    		   LoggedUserDetails.Fname=null;
	    		   LoggedUserDetails.Lname=null;
	    		   LoggedUserDetails.mobileno=null;
	    		   LoggedUserDetails.username=null;
	    		   LoggedUserDetails.password=null;
	    		   option=0;
	    		   System.out.println("Thank for using it , Logout");
	    		 break;
	    	 
	    	default:
	    		  System.out.println("INVALID INPUT , PLEASE TRY AGAIN");
	    	 }
	    	 
	     }while(option!=0);
	}

	public static void viewListOfBookings() {
		UserDao dao= new UserDaoImpl();
		try {
			List<Bookings> list2= dao.viewListOfBooking();
			if(list2!=null)
			{
				list2.forEach(i->System.out.println(i));
			}
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}

	public  static void bookBusTicket() {
		
		UserDao dao= new UserDaoImpl();
		BufferedReader br= null;
		try {
			 br= new BufferedReader(new InputStreamReader(System.in));
			 System.out.println("Enter Bus Number:");
			 String bnum= br.readLine();
			 System.out.println("Enter Booking Date:");
			 LocalDate date= LocalDate.parse(br.readLine());
			 System.out.println("Enter Ticket id(This will be a unique identification for your booking)");
			 String tickedId= br.readLine();
			 System.out.println("Enter Number of Traveller:");
			 int num= Integer.parseInt(br.readLine());
			 
			 String str=dao.bookTicket(bnum, date, tickedId, num);
			  System.out.println(str);
			 
			}catch(IOException ex) {
				System.out.println("Error in input , Please try again");
			} catch (SomethingWentWrongException e) {
				   System.out.println(e);
			  } catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				   System.out.println(e);
			} catch (SeatsNotAvailableException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			
			
		
	}
	
}
	

