package com.masaischool.ui;

import java.util.Scanner;

import com.masaischool.dao.UserDao;
import com.masaischool.dao.UserDaoImpl;
import com.masaischool.dto.UserImpl;
import com.masaischool.exceptions.SomethingWentWrongException;

public class UserSignup {
	static int count=0;
	
private static boolean isCredentialValid(String user,String pass,String no) {
	if(user.length()<=5 && pass.length()<=6 && no.length()==10)
		  return true;
	else
		 return false;
	
};

public static  void signup(Scanner scn) {
	UserDao ob= new UserDaoImpl();
	
	while(true)
	{
		if(count>=3)
		{
			System.out.println("You have used all three chances so please try aging after some time");
			count=0;
			return;
		}
	System.out.println("Enter First Name:");
	 String fname=scn.next();
	 System.out.println("Enter Last Name:");
	 String lname=scn.next();
	 
	 System.out.println("Create Username (It should be less than or equal to 5 in length):");
	 String username=scn.next();
	 
	 System.out.println("Create Password (It should be less than or equal to 6 in length:");
	 String password=scn.next();
	 
	 System.out.println("Enter your mobile no(It should be of ten digit in length :");
	 String mobileno= scn.next();
	 
	 boolean checker= isCredentialValid(username,password,mobileno);
	 if(checker==true)
	 {
			try {
				ob.userSignUp(new UserImpl(fname,lname,mobileno,username,password));
			} catch (SomethingWentWrongException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		   break;
	 }
	 else
	 {
		 System.out.println("Your entered username ,password or mobile no is not matching with given requirments so please reEnter them");
		 count++;
	 }
	 
	}
	

	
	System.out.println("User has been created");
	return ;
	 
	 
}
}
