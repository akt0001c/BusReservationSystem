package com.masaischool.ui;


import java.util.Scanner;

public class AdministratorLogin {
	
	static int count=0;
	private static boolean isCredentialValid(String username,String password) {
		if(username.equals("Admin") && password.equals("Admin"))
			  return true;
		else
			 return false;
	};

	public static void AdminLogin(Scanner scn) {
		
		String username=null; String password=null;
		while(true) {
			if(count>=3)
			{
				System.out.println("You have used all Three chances , Now Please try after some time");
				count=0;
				return;
			}
	
		System.out.println("Enter Username:");
		   username=scn.next();
		  
		  System.out.println("Enter Password:");
		   password=scn.next();
		   
		  
	     
	    
		
		  
		  boolean checker= isCredentialValid(username,password);
		  if(checker==true)
		  {
			  break;
		  }
		  else
		  {
			 System.out.println("Incorrect Username or Password , Please try again"); 
			 count++;
		  }
	} 
		  
		
		
		System.out.println("|--------------|\n"
				         + "|Welcome Admin |\n"
				         + "|______________|\n");
		
		AdministrtorFeatures.showOptions(scn);
	}  
		  
		  
		  
	}

