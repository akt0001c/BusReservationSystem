package com.masaischool.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbUtils {
static String url;
static String username;
static String password;

static {
	ResourceBundle rb= ResourceBundle.getBundle("db");
	url=rb.getString("url");
	username=rb.getString("user");
	password= rb.getString("password");
}


  public static Connection createConnection() throws SQLException {
	  return DriverManager.getConnection(url, username, password);
  }
  
  public static void closeConnection(Connection conn) throws SQLException
  {
	  if(conn!=null)
	       conn.close();
  }
  
 
	
}
