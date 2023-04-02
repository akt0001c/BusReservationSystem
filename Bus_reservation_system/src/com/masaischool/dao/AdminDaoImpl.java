package com.masaischool.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.masaischool.dto.*;
import com.masaischool.exceptions.RecordNotFoundException;
import com.masaischool.exceptions.SomethingWentWrongException;

public class AdminDaoImpl implements AdminDao {

	@Override
	public String AddBusDetails(Buses bus) throws SomethingWentWrongException {
		Connection conn=null;
		try {
			conn= DbUtils.createConnection();
			String query= "insert into buses (BusNumber,Source,Destination,DepartureTime,ArrivalTime,TotalSeats,Fare,BusType) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, bus.getBusNo());
			ps.setString(2, bus.getSource());
			ps.setString(3, bus.getDestination());
			ps.setTime(4, Time.valueOf(bus.getDepartureTime()));
			ps.setTime(5, Time.valueOf(bus.getArrivalTime()));
			ps.setInt(6, bus.getTotalSeats());
			ps.setDouble(7, bus.getFare());
			ps.setString(8, bus.getBusType());
			
			int x=ps.executeUpdate();
			if(x==0)
			{
				throw new SomethingWentWrongException("Bus Details cannot be added");
			
			}
		}catch(SQLException ex) {
		    throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
		}
		finally {
			try {
				DbUtils.closeConnection(conn);
			} catch (SQLException e) {
				
				throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
			}
		};
		return "Bus Details added Succesfully ";
	}

	@Override
	public String updateBusDetails(Buses bus) throws SomethingWentWrongException {
		Connection conn=null;
		try {
			conn= DbUtils.createConnection();
			String query= "update Buses SET BusType=?,TotalSeats=? where BusNumber=?";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, bus.getBusType());
			ps.setInt(2, bus.getTotalSeats());
			ps.setString(3, bus.getBusNo());
			
			int x=ps.executeUpdate();
			if(x==0)
			{
				throw new SomethingWentWrongException("Bus Details cannot be updated");
			
			}
		}catch(SQLException ex) {
		    throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
		}
		finally {
			try {
				DbUtils.closeConnection(conn);
			} catch (SQLException e) {
				
				throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
			}
		};
		return "Records Updated Sucessfully";
	}

	@Override
	public String deleteBusDetails(String busno) throws SomethingWentWrongException, RecordNotFoundException {
		
		Connection conn=null;
		try {
			conn= DbUtils.createConnection();
			String query= "update  Buses SET is_deleted= 1 where BusNumber=?";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, busno);
			
			int x=ps.executeUpdate();
			if(x==0)
			{
				throw new RecordNotFoundException(" Given Bus Number is not  availabe in the system,Please try again with other one");
			
			}
		}catch(SQLException ex) {
		    throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
		}
		finally {
			try {
				DbUtils.closeConnection(conn);
			} catch (SQLException e) {
				
				throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
			}
		};
		return "Records Deleted sucessfully";
	}
	
	private boolean isResultEmpty(ResultSet rs) throws SQLException {
		if(!rs.isBeforeFirst() && rs.getRow()==0)
			  return true;
		else
			 return false;
	}
	
	private List<Bookings> getResult(ResultSet rs) throws SQLException {
		List<Bookings> list = new ArrayList<>();
		while(rs.next())
		{
		    Buses bus= new BusesImpl( rs.getString("BusNumber"),rs.getString("Source"),rs.getString("Destination"),rs.getTime("DepartureTime").toLocalTime(),rs.getTime("ArrivalTime").toLocalTime(),rs.getInt("TotalSeats"),rs.getDouble("Fare"),rs.getString("BusType"));
			User  user= new UserImpl();
			user.setFname(rs.getString("Fname"));
			user.setLname(rs.getString("Lname"));
			user.setMobileNo(rs.getString("mobileNo"));
			list.add(new BookingsImpl(bus,user,rs.getInt("no_of_passenger"),rs.getDate("BookingDate").toLocalDate(),rs.getString("ticketID")));
		}
		return list;
	}

	@Override
	public List<Bookings> viewAllBookings() throws SomethingWentWrongException, RecordNotFoundException {
		
		Connection conn=null; List<Bookings> list= null;
		try {
			conn= DbUtils.createConnection();
			String query= " select * from bookings inner join Buses ON  bookings.Bus_id= Buses.BusID  inner join Passenger ON bookings.user_ID= Passenger.PassengerID where Passenger.is_deleted=0 and Buses.is_deleted=0 and bookings.is_deleted=0 ";
			PreparedStatement ps= conn.prepareStatement(query);
			ResultSet rs= ps.executeQuery();
			
			if(isResultEmpty(rs))
				  throw new RecordNotFoundException("NO Booking Found");
			
			list= getResult(rs);
			
			
			
			
		}catch(SQLException ex) {
		    throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
		}
		finally {
			try {
				DbUtils.closeConnection(conn);
			} catch (SQLException e) {
				
				throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
			}
		};
		return list;
	}

	@Override
	public List<Bookings> viewBookingsByDateRange(LocalDate sDate, LocalDate eDate)
			throws SomethingWentWrongException, RecordNotFoundException {
		
		Connection conn=null; List<Bookings> list= null;
		try {
			conn= DbUtils.createConnection();
			String query= " select * from bookings inner join Buses ON  bookings.Bus_id= Buses.BusID  inner join Passenger ON bookings.user_ID= Passenger.PassengerID where  bookings.is_deleted=0 and  BookingDate BETWEEN ? AND ?  ";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setDate(1, Date.valueOf(sDate));
			ps.setDate(2, Date.valueOf(eDate));
			ResultSet rs= ps.executeQuery();
			
			if(isResultEmpty(rs))
				  throw new RecordNotFoundException("NO Booking Found");
			
			list= getResult(rs);
			
			
			
			
		}catch(SQLException ex) {
		    throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
		}
		finally {
			try {
				DbUtils.closeConnection(conn);
			} catch (SQLException e) {
				
				throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
			}
		};
		return list;
		
	}

	@Override
	public List<Bookings> viewBookingByBusNumber(String busNumber)
			throws SomethingWentWrongException, RecordNotFoundException {
		   
		Connection conn=null; List<Bookings> list= null;
		try {
			conn= DbUtils.createConnection();
			String query= " select * from bookings inner join Buses ON  bookings.Bus_id= Buses.BusID  inner join Passenger ON bookings.user_ID= Passenger.PassengerID where  bookings.is_deleted=0 and  BusNumber=?  ";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, busNumber);
			ResultSet rs= ps.executeQuery();
			
			if(isResultEmpty(rs))
				  throw new RecordNotFoundException("NO Booking Found");
			
			list= getResult(rs);
			
			
			
			
		}catch(SQLException ex) {
		    throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
		}
		finally {
			try {
				DbUtils.closeConnection(conn);
			} catch (SQLException e) {
				
				throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
			}
		};
		return list;
		
	}

	@Override
	public List<Bookings> viewBookingByMobileNo(String mobNo)
			throws SomethingWentWrongException, RecordNotFoundException {
		Connection conn=null; List<Bookings> list= null;
		try {
			conn= DbUtils.createConnection();
			String query= " select * from bookings inner join Buses ON  bookings.Bus_id= Buses.BusID  inner join Passenger ON bookings.user_ID= Passenger.PassengerID where  bookings.is_deleted=0 and  mobileNo=?  ";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, mobNo);
			ResultSet rs= ps.executeQuery();
			
			if(isResultEmpty(rs))
				  throw new RecordNotFoundException("NO Booking Found");
			
			list= getResult(rs);
			
			
			
			
		}catch(SQLException ex) {
		    throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
		}
		finally {
			try {
				DbUtils.closeConnection(conn);
			} catch (SQLException e) {
				
				throw new SomethingWentWrongException("Somthing went wrong with servers,Please try again");
			}
		};
		return list;
	}

}
