package com.psl.semicolons.AccountManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.psl.semicolons.Account.AccountDetails;
import com.psl.semicolons.DataBase.DataBaseManager;

/*

INSERT INTO `rest_data_schema`.`walletaccountdetails` (userId, firstName, lastName, tokenValue, balanceAmount)
VALUES
(<{}>, <{Some First Name}>, <{Some Last Name}>, <{}>, <{0}>);




*/
public class Manager 
{
	private static List<AccountDetails> accounts = new ArrayList<AccountDetails>();
	
	private String md5Generator(String password)
	{
		String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
		return generatedPassword;
	}
	
	private boolean readFromDB()
	{
		boolean flag = false;
		AccountDetails details = null;
		DataBaseManager.conn = DataBaseManager.initiateConnection();
		String query = "SELECT * FROM walletaccountdetails";
		try 
		{
			Statement readStatement= DataBaseManager.conn.createStatement();
			accounts.clear();
		    ResultSet readResultSet = readStatement.executeQuery(query);
		    
		    while (readResultSet.next()) 
		    {
		    	
		    	details = new AccountDetails(readResultSet.getString(1),
		    			readResultSet.getString(2),
		    			readResultSet.getString(3),
		    			readResultSet.getString(4),
		    			readResultSet.getString(5));
		    	accounts.add(details);
		    }
		    flag = true;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			DataBaseManager.disconnect();
		}
		return flag;
	}
	
	private boolean writeToDB( AccountDetails toWrite)
	{
		boolean flag = false;
		DataBaseManager.conn = DataBaseManager.initiateConnection();
		String query = "INSERT INTO walletaccountdetails VALUES ( '" + toWrite.getUserID()+"','"+toWrite.getFirstName()+"','"+toWrite.getLastName()+"','"+md5Generator(toWrite.getTokenValue())+"','"+toWrite.getBalanceAmount()+"')";
		try 
		{
			Statement write = DataBaseManager.conn.createStatement();
			write.executeUpdate(query);
		    flag = true;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			DataBaseManager.disconnect(); 
		}
		return flag;
	}
	
	private boolean deleteFromDB(AccountDetails toDelete)
	{
		boolean flag = false;
		DataBaseManager.conn = DataBaseManager.initiateConnection();
		String query = "DELETE FROM walletaccountdetails WHERE userId = ? AND tokenValue = ?";
		try 
		{
			PreparedStatement deleteStatement= DataBaseManager.conn.prepareStatement(query);
			deleteStatement.setString(1, toDelete.getUserID());
			deleteStatement.setString(2, md5Generator(toDelete.getTokenValue()));
			flag = true;
		    deleteStatement.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			DataBaseManager.disconnect();
		}
		return flag;
	}
	
	private AccountDetails searchFromDB(AccountDetails toSearch)
	{
		AccountDetails det = new AccountDetails();
		DataBaseManager.conn = DataBaseManager.initiateConnection();
		String query = "SELECT * from walletaccountdetails WHERE userId = '"+toSearch.getUserID()+"' AND tokenValue = '"+md5Generator(toSearch.getTokenValue())+"'";
		try 
		{
			Statement searchStatement= DataBaseManager.conn.createStatement();
			ResultSet rs = searchStatement.executeQuery(query);
			if(rs.next())
			{
				det.setUserID(rs.getString(1));
				det.setFirstName(rs.getString(2));
				det.setLastName(rs.getString(3));
				det.setTokenValue(rs.getString(4));
				det.setBalanceAmount(rs.getString(5));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			DataBaseManager.disconnect();
		}
		return det;
	}
	
	private boolean updateToDB(AccountDetails toSearch, String newValue)
	{
		boolean flag = false;
		DataBaseManager.conn = DataBaseManager.initiateConnection();
		String query = "UPDATE walletaccountdetails SET balanceAmount = ?"
				+ "WHERE userid = ? AND tokenValue = ? ;";
		try 
		{
			PreparedStatement updateStatement = DataBaseManager.conn.prepareStatement(query);
			updateStatement.setString(1, newValue);
			updateStatement.setString(2, toSearch.getUserID());
			updateStatement.setString(3, md5Generator(toSearch.getTokenValue()));
			updateStatement.executeUpdate();
			flag = true;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return flag;
	}
	
	public AccountDetails searchAccount(String tokenValue, String userId)
	{
		AccountDetails searchedDetails = new AccountDetails();
		AccountDetails toSearch = new AccountDetails(userId,"","",tokenValue,"");
		searchedDetails = searchFromDB(toSearch);
		return searchedDetails;
	}
	
	public AccountDetails updateAccountAdd(String tokenValue, String userId, String addMoney )
	{
		AccountDetails updatedDetails = null;
		AccountDetails toSearch = new AccountDetails(userId,"","",tokenValue,"");
		AccountDetails searchDetails = searchFromDB(toSearch);
		Float oldValue = new Float(searchDetails.getBalanceAmount());
		Float addValue = new Float(addMoney);
		Float newValue = oldValue + addValue;
		if(updateToDB(searchDetails, newValue+""))
		{
			updatedDetails = new AccountDetails();
			updatedDetails = searchFromDB(searchDetails);
		}
		return updatedDetails;
	}

	public AccountDetails updateAccountSubstract(String tokenValue, String userId, String substractMoney )
	{
		AccountDetails updatedDetails = null;
		AccountDetails toSearch = new AccountDetails(userId,"","",tokenValue,"");
		AccountDetails searchDetails = searchFromDB(toSearch);
		Float oldValue = new Float(searchDetails.getBalanceAmount());
		Float subValue = new Float(substractMoney);
		Float newValue = oldValue - subValue;
		if(updateToDB(searchDetails, newValue+""))
		{
			updatedDetails = new AccountDetails();
			updatedDetails = searchFromDB(searchDetails);
		}
		return updatedDetails;
	}
	
	public AccountDetails addAccount(AccountDetails toAdd)
	{
		AccountDetails wroteDetails = null;
		if(writeToDB(toAdd))
		{
			wroteDetails = new AccountDetails();
			wroteDetails = searchFromDB(toAdd);
			System.out.println(wroteDetails.toString());
		}
		return wroteDetails;
	}
	
	public boolean terminateAccount(AccountDetails toRemove)
	{
		return deleteFromDB(toRemove);
	}
	
	public List<AccountDetails> getAllAcounts()
	{
		readFromDB();
		return accounts;
	}
}
