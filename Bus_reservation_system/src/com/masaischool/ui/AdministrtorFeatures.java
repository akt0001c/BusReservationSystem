package com.masaischool.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.masaischool.dao.*;
import com.masaischool.dto.*;
import com.masaischool.exceptions.RecordNotFoundException;
import com.masaischool.exceptions.SomethingWentWrongException;

public class AdministrtorFeatures {
	
	public static void addBusIntoDatabase() {
		AdminDao  dao= new AdminDaoImpl();
		
		BufferedReader br= null;
		try {
		 br= new BufferedReader(new InputStreamReader(System.in));
		 
		 System.out.println("Enter Bus Number:");
		 String bnum= br.readLine();
		
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
		 
		 
		 Buses obj= new BusesImpl(bnum,spName,desName,DTime,ATime,num,fare,bType);
		 String str=dao.AddBusDetails(obj);
		 System.out.println(str);
		}catch(IOException ex) {
			System.out.println("Error in input , Please try again");
		} catch (SomethingWentWrongException e) {
			   System.out.println(e);
		  }
		
		finally {
			try {
				br.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	};
	public static void updateBusDetails() {
		AdminDao  dao= new AdminDaoImpl();
		BufferedReader br= null;
		try {
			 br= new BufferedReader(new InputStreamReader(System.in));
			 System.out.println("Enter Bus Number");
			 String bnum= br.readLine();
			 System.out.println("Enter new updated bus type and Total seat number");
			 String btype=br.readLine();
			 int seats= Integer.parseInt(br.readLine());
			 
			 Buses ob= new BusesImpl();
			 ob.setBusNo(bnum);
			 ob.setBusType(btype);
			 ob.setTotalSeats(seats);
			 String str= dao.updateBusDetails(ob);
			 System.out.println(str);
			 
			}catch(IOException ex) {
				System.out.println("Error in input , Please try again");
			} catch (SomethingWentWrongException e) {
				   System.out.println(e);
			  }
			
			finally {
				try {
					br.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
	};
	public static void deleteBusFromDatabase() {
		AdminDao  dao= new AdminDaoImpl();
		BufferedReader br= null;
		try {
			 br= new BufferedReader(new InputStreamReader(System.in));
			 System.out.println("Enter Bus Number");
			 String bnum= br.readLine();
			
			 String str= dao.deleteBusDetails(bnum);
			 System.out.println(str);
			 
			}catch(IOException ex) {
				System.out.println("Error in input , Please try again");
			} catch (SomethingWentWrongException e) {
				   System.out.println(e);
			  } catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				   System.out.println(e);
			}
			
			finally {
				try {
					br.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		
	};
	public static void viewAllBookings() {
		AdminDao  dao= new AdminDaoImpl();
		try {
			List<Bookings> list= dao.viewAllBookings();
			
			if(list!=null)
			{
				list.forEach(i->System.out.println(i));
			}
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	};
	public static void viewBookingsForDateRange() {
		AdminDao  dao= new AdminDaoImpl();
		BufferedReader br= null;
		try {
			 br= new BufferedReader(new InputStreamReader(System.in));
			 System.out.println("Enter the date range:");
			 LocalDate sdate= LocalDate.parse(br.readLine());
			 LocalDate edate= LocalDate.parse(br.readLine());
			 List<Bookings> list1 = dao.viewBookingsByDateRange(sdate, edate);
			 if(list1!=null)
			 {
				 list1.forEach(i->System.out.println(i));
			 }
			 
			}catch(IOException ex) {
				System.out.println("Error in input , Please try again");
			} catch (SomethingWentWrongException e) {
				   System.out.println(e);
			  } catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				   System.out.println(e);
			}
			
			finally {
				try {
					br.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
	};
	public static void viewBookingByBusNumber() {
		AdminDao  dao= new AdminDaoImpl();
		BufferedReader br= null;
		try {
			 br= new BufferedReader(new InputStreamReader(System.in));
			 System.out.println("Enter the Bus Number:");
			 String bnum= br.readLine();
			 List<Bookings> list2= dao.viewBookingByBusNumber(bnum);
			 if(list2!=null)
			 {
				 list2.forEach(i->System.out.println(i));
			 }
			 
			}catch(IOException ex) {
				System.out.println("Error in input , Please try again");
			} catch (SomethingWentWrongException e) {
				   System.out.println(e);
			  } catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				   System.out.println(e);
			}
			
			finally {
				try {
					br.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
	};
	public static void viewBookingByMobile() {
		AdminDao  dao= new AdminDaoImpl();
		BufferedReader br= null;
		try {
			 br= new BufferedReader(new InputStreamReader(System.in));
			 System.out.println("Enter the Mobile Number:");
			 String num= br.readLine();
			 List<Bookings> list3= dao.viewBookingByMobileNo(num);
			 if(list3!=null)
			 {
				 list3.forEach(i->System.out.println(i));
			 }
			 
			}catch(IOException ex) {
				System.out.println("Error in input , Please try again");
			} catch (SomethingWentWrongException e) {
				   System.out.println(e);
			  } catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				   System.out.println(e);
			}
			
			finally {
				try {
					br.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
	};
	
	
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
	    		 viewBookingByBusNumber();
	    		 break;
	    	 case 7:
	    		 viewBookingByMobile();
	    		 break;
	    	 case 8:
	    		    System.out.println("Thank you for using our services ,Logout");
	    		    option=0;
	    		 break;
	    	default:
	    		  System.out.println("INVALID INPUT , PLEASE TRY AGAIN");
	    	 }
	    	 
	     }while(option!=0);
	}
	
}
