<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- Set the page to the width of the device and set the zoon level -->
<meta name="viewport" content="width = device-width, initial-scale = 1">
<link rel="stylesheet" type="text/css"
	href="WebContent/css/bootstrap.css">
<link href='http://fonts.googleapis.com/css?family=Pacifico'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/ottomanStyle.css" />
<title>Create Account</title>

</head>
<body>
	<%@include file="HeadNavBar.jsp"%>
	<div id="registerTitle">Enter Your Information to Start Shopping
	</div>
	<div id=registerInfo>
		<form action="AttemptRegister" method="post">
			First Name<br> <input type="text" name="firstName"
				placeholder="First Name"><br> Last Name<br> <input
				type="text" name="lastName" placeholder="Last Name"><br>
			Password<br> <input type="password" name="password"
				placeholder="Password"><br> ReType Password<br> <input
				type="password" name="rePassword" placeholder="Password Again"><br>
			Birthday<br> Month <select name="birthMonth">
				<option value="1">January</option>
				<option value="2">February</option>
				<option value="3">March</option>
				<option value="4">April</option>
				<option value="5">May</option>
				<option value="6">June</option>
				<option value="7">July</option>
				<option value="8">August</option>
				<option value="9">September</option>
				<option value="10">October</option>
				<option value="11">November</option>
				<option value="12">December</option>
			</select> Day <select name="dayOfMonth">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
				<option value="16">16</option>
				<option value="17">17</option>
				<option value="18">18</option>
				<option value="19">19</option>
				<option value="20">20</option>
				<option value="21">21</option>
				<option value="22">22</option>
				<option value="23">23</option>
				<option value="24">24</option>
				<option value="25">25</option>
				<option value="26">26</option>
				<option value="27">27</option>
				<option value="28">28</option>
				<option value="29">29</option>
				<option value="30">30</option>
				<option value="31">31</option>
			</select> Year <input type="text" name="birthYear" size="4" maxlength="4"><br>
			Email address <br>
			<input type="text" name="email"><br> Billing address <br>
			<input type="text" name="addressBill"><br> Shipping
			address <br>
			<input type="text" name="addressShip"><br> Enable Points<br>Yes<input
				type="radio" name="points" value="yes"><br> No<input
				type="radio" name="points" value="no"><br> Credit Card
			Info <br> <input type="password" id="first" name="card1" size="4"
				maxlength="4" placeholder="XXXX"/> - <input type="password" name="card2" id="second"
				size="4" maxlength="4" placeholder="XXXX"/> - <input type="password" name="card3"
				id="third" size="4" maxlength="4" placeholder="XXXX"/> - <input type="password"
				name="card4" id="fourth" size="4" maxlength="4" placeholder="XXXX"/> <br>
			<br>
			<input type="submit" value="Create Account">
		</form>
	</div>

</body>


</body>
</html>