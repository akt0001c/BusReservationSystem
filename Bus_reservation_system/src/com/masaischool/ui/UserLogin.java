package com.masaischool.ui;

import java.util.Scanner;

public class UserLogin {
	static int count=0;
	
	private static boolean isCredentialValid(String user,String pass) {
		UserDao ob= new UserDaoImpl();
		
		int x= ob.isCredentialValid(user,pass);
		if(x==0)
		{
			return false;
		}
		else
			 return true;
		
	};

	public static void  login(Scanner scn) {
		String username=null; String password=null;
		
		while(true) {
			      if(count>=3)
			      {
			    	  System.out.println("You have entered Wrong Credential Three times now ,Please try againg later");
			    	  count=0;
			    	  return;
			      }
				  System.out.println("Enter User Name:");
				    username= scn.next();
				  System.out.println("Enter Password :");
				    password= scn.next();
				    
				  boolean ans= isCredentialValid(username,password);
				  if(ans==true)
				  {
					  break;
				  }
				  else
				  {
					  System.out.println("Incorrect Username and Password ,Please try again");
					  count++;
				  }
				  
		}
		
		System.out.println("Welcome user ");
		return;
	};
}
