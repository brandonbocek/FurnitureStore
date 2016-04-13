<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/ottomanStyle.css" />
<title>Your Orders</title>
</head>
<%@ page import="java.util.*" %>
<%@ page import="model.Customer" %>
<%@ page import="model.Product" %>
<%@ page import="model.Search" %>
<%@ page import="controller.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<% Customer customer  = (Customer)session.getAttribute("theCustomer"); 
pageContext.setAttribute("customer", customer);
%>
<%@include file="LoggedInNavBar.jsp"%>
<!-- This is where I list all of the customer's orders -->
<c:choose>
<c:when test="${customer.getMaxInCart()==0}">
<div id="cartIsEmptyWarning">Your shopping cart is currently empty!</div>
<div id="emptyCartSuggestion">Search for some furniture and fill your cart up</div>
</c:when>
<c:when test="${customer.getMaxInCart()!=0}">
<div id="shopCartTitle">Here are your orders</div>
<div class="container" style="margin-left: 25%">

	  <c:forEach var="i" begin="0" end="${customer.getMaxInCart()-1}">
	  	<div class="row">
			<c:choose>
			<c:when test="${customer.getCartAmount() > 0}">
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
					<div class="thumbnail">
						<img src="ottoImages/<c:out value="${customer.getAnOrder(i).getImageName()}"/>" alt="..." id="resImg">
						<div class="caption">
							<h3><c:out value="${customer.getAnOrder(i).getName()}" /></h3>
							<h3><c:out value="${customer.getAnOrder(i).getPrice()}" /></h3>
							<p><c:out value="${sr.getAResult(i*2).getDescription()}" /></p>
							<p>
								<a href="RemoveItemServlet?productToRemove=${customer.getAnOrder(i).getOrderId()}" class="btn btn-primary" role="button">Remove</a>
							</p>
						</div>
					</div>
				</div>
			</c:when>
			</c:choose>
		</div>
	</c:forEach>
  </div>
	<div id="yourTotal">
	<p>Your total is <c:out value="${customer.getCart().getPriceTotal()}" /></p>
	</div>
	<div id="checkoutButton">
		<p>
			<a href="GoToCheckoutServlet" class="btn btn-success" role="button">Go To Checkout</a>
		</p>
	</div>
  </c:when>
</c:choose>
</body>
</html>