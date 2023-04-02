package com.masaischool.dto;

public class UserImpl implements User {
     private String Fname;
     private String Lname;
     private String mobileNo;
     private String username;
     private String password;
     
     public UserImpl() {};
     public  UserImpl(String Fname,String Lname,String mobileNo,String username,String password) {
    	 this.Fname=Fname;
    	 this.Lname=Lname;
    	 this.mobileNo=mobileNo;
    	 this.username=username;
    	 this.password=password;
     }
     
     
	@Override
	public String toString() {
		return "User First Name=" + Fname + ", Last Name=" + Lname + ", Mobile No=" + mobileNo + "\n";
	}
	
	@Override
	public String getFname() {
		return Fname;
	}
	
	@Override
	public void setFname(String fname) {
		Fname = fname;
	}
	
	@Override
	public String getLname() {
		return Lname;
	}
	
	@Override
	public void setLname(String lname) {
		Lname = lname;
	}
	
	@Override
	public String getMobileNo() {
		return mobileNo;
	}
	
	@Override
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
     
     
}
