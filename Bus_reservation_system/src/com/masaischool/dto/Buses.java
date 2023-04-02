package com.masaischool.dto;

import java.time.LocalTime;

public interface Buses {
	public String getBusNo();
	public void setBusNo(String busNo);
	public String getSource();
	public void setSource(String source);
	public String getDestination();
	public void setDestination(String destination);
	public LocalTime getDepartureTime();
	public void setDepartureTime(LocalTime departureTime);
	public LocalTime getArrivalTime();
	public void setArrivalTime(LocalTime arrivalTime);
	public int getTotalSeats();
	public void setTotalSeats(int totalSeats) ;
	public double getFare();
	public void setFare(double fare);
	public String getBusType() ;
	public void setBusType(String busType);
}
