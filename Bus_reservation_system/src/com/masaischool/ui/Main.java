package com.masaischool.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.masaischool.exceptions.SomethingWentWrongException;
import com.masaischool.exceptions.UserNotFoundException;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int choice= 0;
		do {
			    System.out.println("\n\n      |------------------------------------|\n"
						         + "      | Welcome in Bus Reservation System. |\n"
						         + "      | Enter Your choice:                 |\n"
						         + "      |  1.Login As Administrator          |\n"
						         + "      |  2.Sign up AS Passanger            |\n"
						         + "      |  3.Login As Passanger              |\n"
						         + "      |  0.Exit                            |\n"
						         + "      |------------------------------------|");
		
			    
		while(true) {	    
		try {	
		  choice = scn.nextInt();
		  break;
		}catch(InputMismatchException ex) {
			System.out.println("You have entered wrong format input  , Please enter digit and Try again");
			scn.nextLine();
	     
		}
		
		}
		  switch(choice) {
		  case 1:
			  AdministratorLogin.AdminLogin(scn);
			  break;
		  case 2:
			  try {
				UserLogin.login(scn);
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				    System.out.println(e);
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				 System.out.println(e);
			}
			  break;
		  case 3:
			  UserSignup.signup(scn);
			  break;
		  case 0:
			    System.out.println("Exit");
			  break;
		  default:
			     System.out.println("Invalid Choice , please Try again");
		  }
		
		
		}while(choice!=0);
         
		
		scn.close();
	}
	

}
