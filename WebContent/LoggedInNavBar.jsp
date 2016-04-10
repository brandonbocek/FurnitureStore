<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
</head>
<%@ page import="java.util.*" %>
<%@ page import="model.Customer" %>
<%@ page import="model.Product" %>
<%@ page import="model.Search" %>
<%@ page import="controller.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>

<body>
	<!-- Collapsible Navigation Bar -->
	<div class="container">

		<!-- .navbar-fixed-top, or .navbar-fixed-bottom can be added to keep the nav bar fixed on the screen -->
		<nav class="navbar navbar-default">
		<div class="container-fluid">

			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">

				<!-- Button that toggles the navbar on and off on small screens -->
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">

					<!-- Hides information from screen readers -->
					<span class="sr-only"></span>

					<!-- Draws 3 bars in navbar button when in small mode -->
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

				<!-- You'll have to add padding in your image on the top and right of a few pixels (CSS Styling will break the navbar) -->
				<a class="pull-left" href="Initial.jsp"><img
					src="ottoImages/otto_corner_navbar.jpeg" width="45px" height="45px"
					style="margin-top:3px"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class=""><a href="OttoHome.jsp">Home <span class="sr-only">(current)</span></a></li>
					<li><a href="AboutUs.jsp">About</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">More <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Email</a></li>
							<li><a href="#">Phone</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="FindCheapestServlet">Deals</a></li>
						</ul></li>

					<li><form class="navbar-form navbar-center"
							action="LogoutServlet" method="post">

							<button type="submit" class="btn btn-default">Logout</button>
						</form></li>
					<!-- navbar-left will move the search to the left -->
					<li>
						<form class="navbar-form navbar-center" action="SearchServlet" method="post">
							<div class="form-group">
								<input type="text" class="form-control" name="searchInput" placeholder="Search">
								<input type="hidden" class="form-control" name="page" value="1">
							</div>
							<button type="submit" class="btn btn-default">
									<span class="glyphicon glyphicon-search"></span>
							</button>
						</form>
					</li>
					<li>
						<div id="shopCartButton">
						<form class="navbar-form navbar-center" action="FillCartServlet" method="post">
							<input type="hidden" name="theCustomer" value="${customer}" />
							<button type="submit" class="btn btn-default">
								<span class="glyphicon glyphicon-shopping-cart"> ${customer.cartAmount}</span>
							</button>
						</form>
						</div>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid --> </nav>
	</div>
	<br>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>