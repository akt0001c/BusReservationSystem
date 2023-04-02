package com.masaischool.dto;

import java.time.LocalDate;

public interface Bookings {

	public Buses getBus() ;
	public void setBus(Buses bus);
	public User getUser();
	public void setUser(User user) ;
	public int getNo_Of_Passenger();
	public void setNo_Of_Passenger(int no_Of_Passenger) ;
	public LocalDate getBookingDate();
	public void setBookingDate(LocalDate bookingDate);
	public String getTicketId();
	public void setTicketId(String ticketId) ;
}
