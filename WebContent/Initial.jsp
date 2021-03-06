<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<link rel="stylesheet" href="css/ottomanStyle.css" />
<title>Welcome Ottoman</title>


<style type="text/css">
#mostOfInitialBody {
	font-size: 62.5%; 
	font-family: arial, sans-serif;
	background-color: yellow;
}

#nttLogo {
	height: 100px;
	width: 100px;
}

#websiteTitle {
	font-size: 10em;
	vertical-align: middle;
	font-family: 'Pacifico', cursive;
	padding-left: 15px;
	color: #191970;
}

#websiteSlogan {
	font-size: 5.4em;
}
/* Carousel Styling */
.slide1 {
	background-image: url('ottoImages/hurry_war.jpeg');
	height: 800px;
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
	margin-top: 50px;
}

.slide2 {
	background-image: url('ottoImages/display_1.jpeg');
	height: 800px;
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
	margin-top: 50px;
}

.slide3 {
	background-image: url('ottoImages/display_living_room2.jpeg');
	height: 800px;
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
	margin-top: 50px;
}

.carousel-caption h1 {
	font-size: 5.4em;
	font-family: 'Pacifico', cursive;
	padding-bottom: .4em;
}

.carousel-caption p {
	font-size: 2em;
}
#slide1Text{
	background-color:red;
	background-color: rgba(255, 0, 0, 0.6);
	padding-top:20px;
	padding-bottom:30px;
}

</style>
</head>
<%@ page import="java.util.*" %>
<%@ page import="model.Customer" %>
<%@ page import="controller.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<% String usersFullName  = (String)session.getAttribute("fullName"); 
pageContext.setAttribute("fullName", usersFullName);
Customer customer  = (Customer)session.getAttribute("theCustomer"); 
pageContext.setAttribute("customer", customer);
%>
 <c:choose>
		<c:when test="${customer.isLoggedIn()}"><%@include file="LoggedInNavBar.jsp"%></c:when>
		<c:when test="${!customer.isLoggedIn()}"><%@include file="HeadNavBar.jsp"%></c:when>
</c:choose>
<div id="mostOfInitialBody">
	<!-- Carousel -->
	<!-- Surround everything with a div with the class carousel slide -->
	<div id="theCarousel" class="carousel slide" data-ride="carousel">

		<!-- Define how many slides to put in the carousel -->
		<ol class="carousel-indicators">
			<li data-target="#theCarousel" data-slide-to="0" class="active">
			</li>
			<li data-target="#theCarousel" data-slide-to="1"></li>
			<li data-target="#theCarousel" data-slide-to="2"></li>
		</ol>

		<!-- Define the text to place over the image -->
		<div class="carousel-inner">
			<div class="item active">
				<div class="slide1"></div>
				<div class="carousel-caption">
				 <div id="slide1Text">
					<h1>Hurry Up and Buy Our Furniture</h1>
					<p>Become an Ottoman Today</p>
					<div id="startShoppingInitialButton">
						<p>
							<a href="GoToHomeServlet" class="btn btn-primary">Start Shopping</a>
						</p>
					</div>
					
			     </div>
				</div>
			</div>
			<div class="item">
				<div class="slide2"></div>
				<div class="carousel-caption" id="slide23Text">
					<h1>Make Your Residence Look Better</h1>
					<p>Fill It with Furniture</p>
				</div>
			</div>
			<div class="item">
				<div class="slide3"></div>
				<div class="carousel-caption" id="slide23Text">
					<h1>Best Furniture is Here</h1>
					<p>Not Anywhere Else</p>
				</div>
			</div>
		</div>

		<!-- Set the actions to take when the arrows are clicked -->
		<a class="left carousel-control" href="#theCarousel" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"> </span>
		</a> <a class="right carousel-control" href="#theCarousel"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right"></span>
		</a>
	</div>

</div>
	
</body>

</html>