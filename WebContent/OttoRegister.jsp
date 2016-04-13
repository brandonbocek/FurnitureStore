<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>


<html lang="en">
<head>
<link rel="stylesheet" href="css/ottomanStyle.css" />
<meta charset="UTF-8">
<title>Create Account</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

	<!-- Add the JQuery UI CSS -->
  <link href="css/jquery-ui.min.css" rel="stylesheet">
 
  <!-- Add the JQuery UI JS file -->
  <script src ="js/jquery-ui.min.js" > </script>

</head>
<body>
<%-- 	<%@include file="HeadNavBar.jsp"%> --%>

	<div id="registerTitle">Enter Your Information to Start Shopping
		<div id="leaveRegister">
		<a href="GoToHomeServlet" class="btn btn-Info" role="button" id="leaveRegisterButton">I Have An Account</a>
		</div>
	</div>
	<div id=registerInfo>
		<form action="AttemptRegister" method="post">
		<div id="nameInput">
			<span id="firstNameInput">First Name <input type="text" name="firstName" placeholder="First Name"></span>
			<span id="lastNameInput">Last Name <input type="text" name="lastName" placeholder="Last Name"></span>
		</div>
		<div id="passwordInput">
			<span id="firstPasswordInput">Password <input type="password" name="password" placeholder="Password"></span>
			<span id="rePasswordInput">Re-type Your Password <input type="password" name="rePassword" placeholder="Password Again"></span>
		</div>
		<div id="birthDayAndEmail">
			<span id="birthdayInput">
				Birthday:
					<input type="text" name="birthday" id="birthday">
			</span>
			
			<span id="enterEmailRegister">Email address <input type="text" name="email"></span>
		</div>
		<div id="physicalAddress">
			<span id="enterBillingRegister">Billing address <input type="text" name="addressBill"></span>
			 <span id="enterShippingRegister">Shipping address <input type="text" name="addressShip"></span>
		</div>
	<div id="pointsAndCreditCard">		
		<fieldset id="pointsChooseBox">
		    <legend>Points Enabled?</legend>
		    <span id="radioPoints">
			      <input type="radio" id="yesPoints" name="pointOptions">
			      <label for="yesPoints">Yes</label>
			 
			      <input type="radio" id="noPoints" name="pointOptions" checked="checked">
			      <label for="noPoints">No </label>
		    </span>
	    
	  </fieldset>
			  <div id="creditCardInput">	Credit Card Info <input type="password" id="first" name="card1" size="4"
					maxlength="4" placeholder="XXXX"/> - <input type="password" name="card2" id="second"
					size="4" maxlength="4" placeholder="XXXX"/> - <input type="password" name="card3"
					id="third" size="4" maxlength="4" placeholder="XXXX"/> - <input type="password"
					name="card4" id="fourth" size="4" maxlength="4" placeholder="XXXX"/>
			  </div> <br><br>
		   <input type="submit" id="completeNewAccount" value="Create Account">
		</div>
	</form>
		
</div>
<script type="text/javascript">
 
$(document).ready(function() {
 
	 // Style radio buttons
	  $("#radioPoints").buttonset();
	 
	// Style buttons
	  $("#completeNewAccount").button();
	
	// Style buttons
	  $("#leaveRegisterButton").button();
	
  $("#birthday").datepicker({
    // Show month dropdown
    changeMonth: true,
    // Show year dropdown
    changeYear: true,
    dateFormat: "MM dd, yy",
    // Number of months to display
    numberOfMonths: 1,
    // Define maxDate
    maxDate: 0,
    // Define minDate
    minDate: -7000000
  });
 
}); // End of JQuery
 
</script>
	
</body>

</html>