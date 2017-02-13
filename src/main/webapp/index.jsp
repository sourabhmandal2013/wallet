<html>
<body>
    ADD
	    <form action="http://localhost:9090/Wallet/webapi/walletAccounts/adduser" method ="post">
		    User ID : <input type="text" id="userid" name = "userid">
		    F Nm : <input type="text" id="firstName" name = "firstName">
		    L Nm : <input type="text" id="lastName" name = "lastName">
		    Token Value : <input type="password" id="tokenValue" name = "tokenValue">
		    Balance : <input type="text" id="balanceAmount" name = "balanceAmount">
			    <input type="submit" value="Proceed">
		    
	    </form>
    <br>
    
    
    DELETE
    <form action="http://localhost:9090/Wallet/webapi/walletAccounts/removeuser" method = "post">
    	User ID : <input type="text" id="userid" name = "userid">
    	Token Value : <input type="password" id="tokenValue" name = "tokenValue">
    		    <input type="submit" value="Proceed">
    </form>
    
    SEARCH
    <form action="http://localhost:9090/Wallet/webapi/walletAccounts/user" method = "post">
    	User ID : <input type="text" id="userid" name = "userid">
    	Token Value : <input type="password" id="tokenValue" name = "tokenValue">
    		    <input type="submit" value="Proceed">
    </form>
    
    ADD Money
    <form action="http://localhost:9090/Wallet/webapi/walletAccounts/addMoney" method = "post">
    	User ID : <input type="text" id="userid" name = "userid">
    	Token Value : <input type="password" id="tokenValue" name = "tokenValue">
    	Add : <input type="text" id="add" name = "add">
    		    <input type="submit" value="Proceed">
    </form>
    
    SUBSTRACT Money
    <form action="http://localhost:9090/Wallet/webapi/walletAccounts/subMoney" method = "post">
    	User ID : <input type="text" id="userid" name = "userid">
    	Token Value : <input type="password" id="tokenValue" name = "tokenValue">
    	Sub : <input type="text" id="sub" name = "sub">
    		    <input type="submit" value="Proceed">
    </form>
    
    
</body>
</html>
