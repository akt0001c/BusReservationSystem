package com.masaischool.dao;

import java.time.LocalDate;
import java.util.List;

import com.masaischool.dto.*;
import com.masaischool.exceptions.*;

public interface AdminDao {

  public String AddBusDetails(Buses bus) throws SomethingWentWrongException;
  public String updateBusDetails(Buses bus)throws SomethingWentWrongException;
  public String deleteBusDetails(String busno) throws SomethingWentWrongException,RecordNotFoundException;
  public List<Bookings> viewAllBookings() throws SomethingWentWrongException,RecordNotFoundException;
  public List<Bookings> viewBookingsByDateRange(LocalDate sDate ,LocalDate eDate) throws SomethingWentWrongException,RecordNotFoundException;
  public List<Bookings> viewBookingByBusNumber(String busNumber)throws SomethingWentWrongException,RecordNotFoundException;
  public List<Bookings> viewBookingByMobileNo(String mobNo)throws SomethingWentWrongException,RecordNotFoundException;
  
}
