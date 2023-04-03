package com.masaischool.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import com.masaischool.ui.LoggedUserDetails;

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
		    if(isResultEmpty(rs)==true)
		    {
		    	
		    	  throw new UserNotFoundException("User not Found");
		    }
		    
		    logedUser = new UserImpl();
		    
		    while(rs.next())
		    {
		    logedUser.setUsername(rs.getString("username"));
		    logedUser.setPassword(rs.getString("password"));
		    logedUser.setFname(rs.getString("Fname"));
		    logedUser.setLname(rs.getString("Lname"));
		    logedUser.setMobileNo(rs.getString("mobileNo"));
		    }
		    
			
			
			
			
			
		}catch(SQLException ex) {
			System.out.println(ex);
		}
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
		    
		  while(rs.next()) {
			ans= rs.getInt("BusID");
		  }
			
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
	
	private int getuserId(String mobileNo) throws RecordNotFoundException, SomethingWentWrongException {
		int ans=0;
		Connection conn=null; 
		try {
			conn= DbUtils.createConnection();
			String query= "select * from Passenger where  is_deleted=0 and mobileNo=?";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1,mobileNo );
			
			
		    ResultSet rs= ps.executeQuery();
		    
		    if(isResultEmpty(rs))
		    	  throw new RecordNotFoundException("No user Found,Please try again later");
		    
		  while(rs.next()) {
			ans= rs.getInt("PassengerID");
		  }
			
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
	public String bookTicket(String busno,LocalDate date,String ticketID,int no_passenger) throws SomethingWentWrongException, SeatsNotAvailableException, RecordNotFoundException {
		int Busid= getBusId(busno);
		int UserID= getuserId(LoggedUserDetails.mobileno); 
		
		
		Connection conn=null;
		try {
			conn= DbUtils.createConnection();
			String query= "insert into Bookings (Bus_Id,user_ID,no_of_passenger,BookingDate,ticketID) values (?,?,?,?,?)";
			PreparedStatement ps= conn.prepareStatement(query);
			ps.setInt(1, Busid);
			ps.setInt(2, UserID);
			ps.setInt(3,no_passenger);
			ps.setDate(4, Date.valueOf(date));
			ps.setString(5, ticketID);
			
		    int x= ps.executeUpdate();
		    if(x==0)
		    	  throw new SomethingWentWrongException("Something went wrong , please try again");
			
			
		}catch(SQLException ex) {}
		finally {
			try {
				DbUtils.closeConnection(conn);
			} catch (SQLException e) {
				
			     throw new SomethingWentWrongException("Something Went Wrong with servers , Please Try again");
			}
		}
		
		return "Booking done sucessfully";
	}

	@Override
	public String cancelTicket() throws SomethingWentWrongException, NonCancelableTicketException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bookings> viewListOfBooking() throws SomethingWentWrongException, RecordNotFoundException {
		
		
		Connection conn=null; List<Bookings> list=null;
		try {
			conn= DbUtils.createConnection();
			String query= " select * from bookings inner join Buses ON  bookings.Bus_id= Buses.BusID  inner join Passenger ON bookings.user_ID= Passenger.PassengerID where Passenger.is_deleted=0 and Buses.is_deleted=0 and bookings.is_deleted=0 and mobileNo=? ";

			PreparedStatement ps= conn.prepareStatement(query);
			ps.setString(1,LoggedUserDetails.mobileno );
			
			
		    ResultSet rs= ps.executeQuery();
		    
		    if(isResultEmpty(rs))
		    	  throw new RecordNotFoundException("No Booking Found,Please try again later");
		    
		    list= getResult(rs);
		   
			
		}catch(SQLException ex) {}
		finally {
			try {
				DbUtils.closeConnection(conn);
			} catch (SQLException e) {
				
			     throw new SomethingWentWrongException("Something Went Wrong with servers , Please Try again");
			}
			
		}
		return list;
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
