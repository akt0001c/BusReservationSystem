package com.masaischool.dao;

import java.time.LocalDate;
import java.util.List;

import com.masaischool.dto.*;
import com.masaischool.exceptions.*;

public interface UserDao {

	public String userSignUp(User user)throws SomethingWentWrongException;
	public User userLogin(User user) throws UserNotFoundException,SomethingWentWrongException;
	public List<Buses> viewListOfBuses() throws SomethingWentWrongException,RecordNotFoundException;
	public String bookTicket(String busno,LocalDate date,String id,int no_passenger) throws SomethingWentWrongException,SeatsNotAvailableException, RecordNotFoundException;
	public String cancelTicket()throws SomethingWentWrongException,NonCancelableTicketException;
	public List<Bookings> viewListOfBooking() throws SomethingWentWrongException ,RecordNotFoundException;
	public String deleteAccount()throws SomethingWentWrongException;
	public String changeUserDetails()throws SomethingWentWrongException;
}
