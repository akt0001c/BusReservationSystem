package com.masaischool.ui;

import java.util.Scanner;

import com.masaischool.dao.*;
import com.masaischool.dto.*;
import com.masaischool.exceptions.SomethingWentWrongException;
import com.masaischool.exceptions.UserNotFoundException;


public class UserLogin {
	static int count=0;
	
	private static boolean isCredentialValid(String user,String pass) throws UserNotFoundException, SomethingWentWrongException {
		UserDao ob= new UserDaoImpl();
		User tmp= new UserImpl();
		tmp.setUsername(user);
		tmp.setPassword(pass);
		
		User loggedUser= ob.userLogin(tmp);
		
		if(loggedUser==null)
		{
			return false;
		}
		else
		{
			LoggedUserDetails.Fname=loggedUser.getFname();
			LoggedUserDetails.Lname=loggedUser.getLname();
			LoggedUserDetails.mobileno=loggedUser.getMobileNo();
			LoggedUserDetails.username=loggedUser.getUsername();
			LoggedUserDetails.password=loggedUser.getPassword();
			
			 return true;
		}
		
	};

	public static void  login(Scanner scn) throws UserNotFoundException, SomethingWentWrongException {
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
		
		System.out.println("Welcome  "+LoggedUserDetails.Fname+" "+LoggedUserDetails.Lname);
		return;
	};
}
