<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/ottomanStyle.css" />
<title>Empire Furniture</title>
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


Product shownProduct = (Product)session.getAttribute("shownProduct");
pageContext.setAttribute("shownProduct", shownProduct);
%>
 <c:choose>
		<c:when test="${!customer.isLoggedIn()}"><%@include file="HeadNavBar.jsp"%></c:when>
		<c:when test="${customer.isLoggedIn()}"><%@include file="LoggedInNavBar.jsp"%></c:when>
</c:choose>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
					<div id="containerToShow">
					 
						<img src="ottoImages/<c:out value="${shownProduct.getImageName()}"/>" alt="..." id="profileImageIndividual">
					  
					  <div class="caption">
					    <div id="individualProfileText">
								<div id="indvidualProfileName"><b><c:out value="${shownProduct.getName()}" /></b></div>
								<div id="indvidualProfilePrice"><p><c:out value="$${shownProduct.getPrice()}" /></p></div>
								<div id="indvidualProfileDesc"><p><c:out value="${shownProduct.getDescription()}" /></p></div>
							<div id="indvidualProfileButton"><p>
								<c:if test="${customer.isLoggedIn()}">
									<a href="AddToCartServlet?productToAdd=${shownProduct.getName()}" class="btn btn-primary" role="button">Add to Cart</a>
								</c:if>
								<c:if test="${!customer.isLoggedIn()}">
									<a href="OttoRegister.jsp" class="btn btn-primary" role="button">Login to Add</a>
								</c:if>
							</p>
							</div>
						  </div>
						</div>
					</div>
				</div>
</body>
</html>