package com.masaischool.dto;

import java.sql.Time;
import java.time.LocalTime;

public class BusesImpl implements Buses{
     private String BusNo;
     private String source;
     private String destination;
     private LocalTime departureTime;
     private LocalTime arrivalTime;
     private int totalSeats;
     private double fare;
     private String busType;
    
     
    public  BusesImpl(){};
     public BusesImpl(String BusNo,String source,String destination,LocalTime departureTime,LocalTime arrivalTime,int totalSeats,double fare,String busType){
    	 this.BusNo=BusNo;
    	 this.source=source;
    	 this.destination=destination;
    	 this.departureTime=departureTime;
    	 this.arrivalTime= arrivalTime;
    	 this.totalSeats=totalSeats;
    	
    	 this.fare=fare;
    	 this.busType=busType;
     }
	
	@Override
	public String toString() {
		return "Bus Number=" + BusNo + ", Source=" + source + ", Destination=" + destination + ", Departure Time="
				+ departureTime + ", Arrival Time=" + arrivalTime + ", Bus Type ="+ busType+ " Total Seats=" + totalSeats + ", Fare=" + fare
				+ "\n";
	}
	
	@Override
	public String getBusNo() {
		return BusNo;
	}
	
	@Override
	public void setBusNo(String busNo) {
		BusNo = busNo;
	}
	
	@Override
	public String getSource() {
		return source;
	}
	
	@Override
	public void setSource(String source) {
		this.source = source;
	}
	
	@Override
	public String getDestination() {
		return destination;
	}
	
	@Override
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	@Override
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	
	@Override
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	
	@Override
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	
	@Override
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	@Override
	public int getTotalSeats() {
		return totalSeats;
	}
	
	@Override
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	@Override
	public double getFare() {
		return fare;
	}
	
	@Override
	public void setFare(double fare) {
		this.fare = fare;
	}
	
	@Override
	public String getBusType() {
		return busType;
	}
	
	@Override
	public void setBusType(String busType) {
		this.busType = busType;
	}
	
	
     
     
	
     
     
}
