package com.psl.semicolons.Account;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Account")
public class AccountDetails 
{
	private String userId;
	private String firstName;
	private String lastName;
	private String tokenValue;
	private String balanceAmount;
	
	public String getUserID() {
		return userId;
	}
	public void setUserID(String userID) {
		this.userId = userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	public String getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	
	public AccountDetails(String userID, String firstName, String lastName, String tokenValue, String balanceAmount) {
		super();
		this.userId = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.tokenValue = tokenValue;
		this.balanceAmount = balanceAmount;
	}
	
	public AccountDetails() {
		setUserID("");
		setFirstName("");
		setLastName("");
		setTokenValue("");
		setBalanceAmount("");
	}
	
	//////////////////////////////////////////////////////Optional Testing Delete Later On
	
	@Override
	public String toString() {
		return "AccountDetails [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", tokenValue=" + tokenValue + ", balanceAmount=" + balanceAmount + "]";
	}
	
	
	
}
