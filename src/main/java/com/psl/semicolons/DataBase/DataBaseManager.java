package com.psl.semicolons.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseManager 
{
	public static Connection conn = null;
	
	public static Connection initiateConnection()
	{
		String myDriver = "com.mysql.jdbc.Driver";
	    String myUrl = "jdbc:mysql://localhost:3306/rest_data_schema";
	    try {
			Class.forName(myDriver);
			conn = DriverManager.getConnection(myUrl, "root", "root");
		}
	    catch (ClassNotFoundException e)
	    {
			e.printStackTrace();
		} 
	    catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static boolean disconnect()
	{
		boolean val = true;
		try {
			conn.close();
			val = true;
		} catch (SQLException e) 
		{
			e.printStackTrace();
			val = false;
		}
		return val;
	}
}
