package com.psl.semicolons.Wallet;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.psl.semicolons.Account.AccountDetails;
import com.psl.semicolons.AccountManager.Manager;

@Path("walletAccounts/")
public class MyResource 
{
	private Manager accounts = new Manager();
	private AccountDetails accountDetail = null;
	
    @POST
    @Path("addMoney")
    @Produces(MediaType.TEXT_XML)
    public Response addMoney(@FormParam("userid") String uID ,
    						@FormParam("tokenValue") String tokenValue,
    						@FormParam("add") String value)
    {
    	accountDetail = accounts.updateAccountAdd(tokenValue, uID, value);
    	Response res = null;
    	if(accountDetail.getUserID().isEmpty()==false)
    		res = Response.ok(accountDetail).build();
    	else
    		res = Response.serverError().build();
    	return res;
    }
    
    @POST
    @Path("subMoney")
    @Produces(MediaType.TEXT_XML)
    public Response subMoney(@FormParam("userid") String uID ,
    						@FormParam("tokenValue") String tokenValue,
    						@FormParam("sub") String value)
    {
    	accountDetail = accounts.updateAccountSubstract(tokenValue, uID, value);
    	Response res = null;
    	if(accountDetail.getUserID().isEmpty()==false)
    		res = Response.ok(accountDetail).build();
    	else
    		res = Response.serverError().build();
    	return res;
    }
    
    
    @POST
    @Path("user")
    @Produces(MediaType.TEXT_XML)
    public Response userAccountDetails(@FormParam("userid") String uID ,
    						@FormParam("tokenValue") String tokenValue)
    {
    	accountDetail = accounts.searchAccount(tokenValue, uID);
    	Response res = null;
    	if(accountDetail.getUserID().isEmpty()==false)
    		res = Response.ok(accountDetail).build();
    	else
    		res = Response.serverError().build();
    	return res;
    }
    
    
    @POST
    @Path("removeuser")
    @Produces(MediaType.TEXT_XML)
    public Response terminateUserAccount(@FormParam("userid") String uID ,
    						@FormParam("tokenValue") String tokenValue)
    {
    	boolean flag = accounts.terminateAccount(new AccountDetails(uID,"","",tokenValue,""));
    	Response res = null;
    	if(flag == true)
    		res = Response.ok().build();
    	else
    		res = Response.serverError().build();
    	return res;
    }
    
    
    @POST
    @Path("adduser")
    @Produces(MediaType.TEXT_XML)
    public Response addUserAccount(
    						@FormParam("userid") String uID ,
    						@FormParam("firstName") String fNm,
    						@FormParam("lastName") String lNm ,
    						@FormParam("tokenValue") String tokenValue,
    						@FormParam("balanceAmount") String value
    						)
    {
    	accountDetail = accounts.addAccount(new AccountDetails(uID,fNm,lNm,tokenValue,value));
    	return Response.ok(accountDetail).build();
    }
    
    
    
    
}
