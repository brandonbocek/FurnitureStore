<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<link rel="stylesheet" href="css/ottomanStyle.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>About Empire</title>
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
<div id="aboutUsDescription">
We are the Ottoman Empire. Founded in 2016 we will invade your home with our ottomans<br>
 and other lovely furniture if you give us money. Give us money today!
 </div>
</body>
</html>