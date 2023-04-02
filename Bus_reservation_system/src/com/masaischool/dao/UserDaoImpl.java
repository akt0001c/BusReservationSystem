package com.masaischool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masaischool.dto.Bookings;
import com.masaischool.dto.BookingsImpl;
import com.masaischool.dto.Buses;
import com.masaischool.dto.BusesImpl;
import com.masaischool.dto.User;
import com.masaischool.dto.UserImpl;
import com.masaischool.exceptions.NonCancelableTicketException;
import com.masaischool.exceptions.RecordNotFoundException;
import com.masaischool.exceptions.SeatsNotAvailableException;
import com.masaischool.exceptions.SomethingWentWrongException;
import com.masaischool.exceptions.UserNotFoundException;

public class UserDaoImpl implements UserDao {
	
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
	
	private List<Buses> getResultBuses(ResultSet rs) throws SQLException {
		List<Buses> list = new ArrayList<>();
		while(rs.next())
		{
		    Buses bus= new BusesImpl( rs.getString("BusNumber"),rs.getString("Source"),rs.getString("Destination"),rs.getTime("DepartureTime").toLocalTime(),rs.getTime("ArrivalTime").toLocalTime(),rs.getInt("TotalSeats"),rs.getDouble("Fare"),rs.getString("BusType"));

		   list.add(bus);
		}
		return list;
	}

	@Override
	public String userSignUp(User user) throws SomethingWentWrongException {
		Connection conn=null;
		try {
			conn= DbUtils.createConnection();
			String query= "insert into Passenger (mobileNo,Fname,Lname,username,password) values(?,?,?,?,?)";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1,user.getMobileNo());
			ps.setString(2, user.getFname());
			ps.setString(3, user.getLname());
			ps.setString(4, user.getUsername());
			ps.setString(5, user.getPassword());
			
		    int x= ps.executeUpdate();
		    if(x==0)
		    	  throw new SomethingWentWrongException("Something went wrond , please try again");
			
			
		}catch(SQLException ex) {}
		finally {
			try {
				DbUtils.closeConnection(conn);
			} catch (SQLException e) {
				
			     throw new SomethingWentWrongException("Something Went Wrong with servers , Please Try again");
			}
		}
		return "User added Sucessfully";
	}

	@Override
	public User userLogin(User user) throws UserNotFoundException, SomethingWentWrongException {
		User logedUser=null;
		Connection conn=null;
		try {
			conn= DbUtils.createConnection();
			String query= "select * from Passenger where username=? and password=? and is_deleted=0";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			
		    ResultSet rs= ps.executeQuery();
		    if(isResultEmpty(rs))
		    	  throw new UserNotFoundException("User not Found");
		    
		    logedUser = new UserImpl();
		    
		    logedUser.setUsername(rs.getString("username"));
		    logedUser.setPassword(rs.getString("password"));
		    logedUser.setFname(rs.getString("Fname"));
		    logedUser.setLname(rs.getString("Lname"));
		    logedUser.setMobileNo(rs.getString("mobileNo"));
		 
			
			
		}catch(SQLException ex) {}
		finally {
			try {
				DbUtils.closeConnection(conn);
			} catch (SQLException e) {
				
			     throw new SomethingWentWrongException("Something Went Wrong with servers , Please Try again");
			}
		}
		return logedUser;
	}

	@Override
	public List<Buses> viewListOfBuses() throws SomethingWentWrongException, RecordNotFoundException {
		Connection conn=null; List<Buses> buslist=null;
		try {
			conn= DbUtils.createConnection();
			String query= "select * from Buses where  is_deleted=0";
			PreparedStatement ps= conn.prepareStatement(query);
			
			
		    ResultSet rs= ps.executeQuery();
		    if(isResultEmpty(rs))
		    	  throw new RecordNotFoundException("No Bus Found,Please try again later");
		    
		  
			buslist= getResultBuses(rs);
			
		}catch(SQLException ex) {}
		finally {
			try {
				DbUtils.closeConnection(conn);
			} catch (SQLException e) {
				
			     throw new SomethingWentWrongException("Something Went Wrong with servers , Please Try again");
			}
		}
		return buslist;
	}
	
	private int getBusId(String busno) throws RecordNotFoundException, SomethingWentWrongException {
		int ans=0;
		Connection conn=null; 
		try {
			conn= DbUtils.createConnection();
			String query= "select * from Buses where  is_deleted=0 and BusNumber=?";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1, busno);
			
			
		    ResultSet rs= ps.executeQuery();
		    
		    if(isResultEmpty(rs))
		    	  throw new RecordNotFoundException("No Bus Found,Please try again later");
		    
		  
			ans= rs.getInt("BusID");
			
		}catch(SQLException ex) {}
		finally {
			try {
				DbUtils.closeConnection(conn);
			} catch (SQLException e) {
				
			     throw new SomethingWentWrongException("Something Went Wrong with servers , Please Try again");
			}
			
		}
		return ans;
	}

	@Override
	public String bookTicket(String busno) throws SomethingWentWrongException, SeatsNotAvailableException, RecordNotFoundException {
		int Busid= getBusId(busno);
		int userid= 
		return null;
	}

	@Override
	public String cancelTicket() throws SomethingWentWrongException, NonCancelableTicketException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bookings> viewListOfBooking() throws SomethingWentWrongException, RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAccount() throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeUserDetails() throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		return null;
	}

}
