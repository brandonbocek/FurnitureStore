<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/ottomanStyle.css" />
<title>Empire Furniture</title>
</head>
<%@ page import="java.util.*" %>
<%@ page import="model.Customer" %>
<%@ page import="model.Search" %>
<%@ page import="controller.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<% String usersFullName  = (String)session.getAttribute("fullName"); 
pageContext.setAttribute("fullName", usersFullName);
Customer customer  = (Customer)session.getAttribute("theCustomer"); 
pageContext.setAttribute("customer", customer);
Search sr = (Search)session.getAttribute("randomResults");
pageContext.setAttribute("sr", sr);%>


 <c:choose>
		<c:when test="${customer.isLoggedIn()}"><%@include file="LoggedInNavBar.jsp"%><div id="ottoHomeHeadText">Welcome back <%= usersFullName %></div></c:when>
		<c:when test="${!customer.isLoggedIn()}"><%@include file="HeadNavBar.jsp"%><div id="ottoHomeHeadText">You are not logged in</div></c:when>
</c:choose>

<!-- Maybe put every Product and the pagination in a random order? -->
 <c:choose>
		<c:when test="${customer.isLoggedIn()}"><div id="ottoHomeSubHeadText">You Might be Interested in some of this Furniture</div></c:when>
		<c:when test="${!customer.isLoggedIn()}"><div id="ottoHomeSubHeadText">But Feel Free to Look Around Anyway</div></c:when>
</c:choose>

<div class="container" style="margin-left: 25%">
	  <c:forEach var="i" begin="0" end="4">
	  	<div class="row">
			<c:choose>
			<c:when test="${sr.getSearchArray().size() > (i*2)}">
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" id="productContainer">
					<div class="">
						<a href="SeeProductServlet?productToView=${sr.getAResult(i*2).getName()}">
							<img src="ottoImages/<c:out value="${sr.getAResult((i*2)).getImageName()}"/>" alt="..." id="resImg">
						</a>
						<div class="caption">
							<h3><c:out value="${sr.getAResult(i*2).getName()}" /></h3>
							<p><c:out value="$${sr.getAResult(i*2).getPrice()}" /></p>
							<p><c:out value="${sr.getAResult(i*2).getDescription()}" /></p>
							<p>
								<c:if test="${customer.isLoggedIn()}">
									<a href="AddToCartServlet?productToAdd=${sr.getAResult(i*2).getName()}&cameFrom=home" class="btn btn-primary" role="button">Add to Cart</a>
								</c:if>
								<c:if test="${!customer.isLoggedIn()}">
									<a href="OttoRegister.jsp" class="btn btn-primary" role="button">Login to Add</a>
								</c:if>
							</p>
						</div>
					</div>
				</div>
			</c:when>
			</c:choose>
<!-- Second Result on row -->
			<c:choose>
				<c:when test="${sr.getSearchArray().size() > (i*2)+1}">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" id="productContainer">
						<div class="">
						  <a href="SeeProductServlet?productToView=${sr.getAResult((i*2)+1).getName()}">
							<img src="ottoImages/<c:out value="${sr.getAResult((i*2)+1).getImageName()}"/>" alt="..." id="resImg">
						  </a>
							<div class="caption">
								<h3><c:out value="${sr.getAResult((i*2)+1).getName()}" /></h3>
								
								<p><c:out value="$${sr.getAResult((i*2)+1).getPrice()}" /></p>
								<p><c:out value="${sr.getAResult((i*2)+1).getDescription()}" /></p>
								<p>
								<c:if test="${customer.isLoggedIn()}">
									<a href="AddToCartServlet?productToAdd=${sr.getAResult((i*2)+1).getName()}&cameFrom=home" class="btn btn-primary" role="button">Add to Cart</a>
								</c:if>
								<c:if test="${!customer.isLoggedIn()}">
									<a href="OttoRegister.jsp" class="btn btn-primary" role="button">Login to Add</a>
								</c:if>
								</p>
							</div>
						</div>
					</div>
				</c:when>
			</c:choose>
		</div>
	  </c:forEach>
	</div>

</body>
</html>