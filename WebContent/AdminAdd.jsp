<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<link rel="stylesheet" href="css/ottomanStyle.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Empire Furniture</title>
</head>
<%@ page import="java.util.*" %>
<%@ page import="model.Customer" %>
<%@ page import="controller.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<!-- Variables tell the user if they added successfully or not -->
<% 
Customer customer  = (Customer)session.getAttribute("theCustomer"); 
pageContext.setAttribute("customer", customer);
try{
	boolean cardAdd  = (boolean)session.getAttribute("cardAddStatus"); 
	pageContext.setAttribute("cardAdd", cardAdd);
	boolean productAdd = (boolean)session.getAttribute("productAddStatus"); 
	pageContext.setAttribute("productAdd", productAdd);
}catch(NullPointerException exc){
	pageContext.setAttribute("productAdd", false);
	pageContext.setAttribute("cardAdd", false);
}

%>
<!-- Should I even include this? Probably. You can only log in as admin if you aren't logged in already
but when you log in as admin, you can still log in as a user from there -->
 <c:choose>
		<c:when test="${customer.isLoggedIn()}"><%@include file="LoggedInNavBar.jsp"%></c:when>
		<c:when test="${!customer.isLoggedIn()}"><%@include file="HeadNavBar.jsp"%></c:when>
</c:choose>
<div id="adminAddStatusInformer">
<c:choose>
		<c:when test="${cardAdd}">New Card was Added</c:when>
		<c:when test="${productAdd}">New Product was Added</c:when>
		<c:when test="${!cardAdd && !productAdd}">Nothing New was Added to Either Table</c:when>
</c:choose>
</div>
<div id="adminAddNewCard">
	<br><br>
	<div id="adminAddNewCardLabel">Add a Card Number Here</div>
		<form action="AdminServlet" method="post"><input type="text" name="part1" id="one" size="4"
				maxlength="4" placeholder="XXXX"/> - <input type="text" name="part2" id="two"
				size="4" maxlength="4" placeholder="XXXX"/> - <input type="text" name="part3"
				id="three" size="4" maxlength="4" placeholder="XXXX"/> - <input type="text" name="part4"
				value="" id="four" size="4" maxlength="4" placeholder="XXXX"/>
				<div id="adminAddNewCardSubmit"> <input type="submit" name="submitCard" value="Add New Card"></div>
		</form><br><br>
	</div>
<div id="adminAddProduct">
	<div id="addProductHere">Add a Product Here</div>
		<div id="addProductForm"> 
			<form action="AdminServlet" method="post" enctype="multipart/form-data">
			<div id="adminProductNameAndPrice">
				<span id="adminAddNameOfProduct">Name of New Product <input type="text" name="adminProd" placeholder="ex: Bunk Bed" /></span>		
				<span id="adminAddPriceOfProduct">Price of New Product <input type="text" name="adminPrice" placeholder="ex: 60.00" /></span>
			</div>
			<div id="adminImageFileProduct">
					<div id="adminImageFileLabel">Image File Name</div>
					<input type="file" name="photo" size="50"/>
				</div>
			<div id="adminAddDescriptionAndKeyWords">	
				<span id="adminAddDescOfProduct">Description of new Product<br> <textarea rows="4" cols="50" name="adminDesc" maxlength="100"></textarea></span>
				<div id="adminAddKeyWordsOfProduct">Key Words for Product<br> <textarea rows="4" cols="50" name="adminKeyWords" maxlength="100"></textarea></div>
			</div>
			
				
				<div id="adminAddSubmit"><input type="submit" name="submitProduct" value="Add New Product"></div>	
			
			</form>
		</div>
	</div>
</body>
</html>