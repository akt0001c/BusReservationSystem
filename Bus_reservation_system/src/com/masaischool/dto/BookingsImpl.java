package com.masaischool.dto;

import java.time.LocalDate;

public class BookingsImpl implements Bookings {

	private Buses bus;
	private User user;
	private int No_Of_Passenger;
	private LocalDate bookingDate;
	private String ticketId;
	
	public BookingsImpl(){};
	public BookingsImpl(Buses bus,User user,int No_Of_Passenger,LocalDate bookingDate,String ticketId){
		this.bus=bus;
		this.user=user;
		this.No_Of_Passenger=No_Of_Passenger;
		this.bookingDate=bookingDate;
		this.ticketId=ticketId;
	}
	
	@Override
	public String toString() {
		return "Booking Details: Ticket Id ->"+ticketId+ "  Booking Date -> "+bookingDate+" Bus Details-> " + bus + ", Passenger Details-> =" + user + ", No Of Passenger=" + No_Of_Passenger + "\n";
	}
	
	@Override
	public Buses getBus() {
		return bus;
	}
	
	@Override
	public void setBus(Buses bus) {
		this.bus = bus;
	}
	
	@Override
	public User getUser() {
		return user;
	}
	
	@Override
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int getNo_Of_Passenger() {
		return No_Of_Passenger;
	}
	
	@Override
	public void setNo_Of_Passenger(int no_Of_Passenger) {
		No_Of_Passenger = no_Of_Passenger;
	}
	@Override
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	
	@Override
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	@Override
	public String getTicketId() {
		return ticketId;
	}
	
	@Override
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	};
	
	
	
	
	
}
