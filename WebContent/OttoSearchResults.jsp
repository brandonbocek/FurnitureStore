<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/ottomanStyle.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
  <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

<title>Otto Empire</title>
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

boolean hasResults = (boolean)session.getAttribute("hasResults");
pageContext.setAttribute("hasResults", hasResults);
%>
 <c:choose>
		<c:when test="${hasResults}">
		<% Search sr = (Search)session.getAttribute("searchResults");
		pageContext.setAttribute("sr", sr);%></c:when>
</c:choose>

	<!-- This page displays random furniture

users get here if they click "start shopping" on the initial page
 or the home button on the toolbar 
 
 if they get here from the search button, the page displays the search results in pagination
 if neccessary
 
 pagination and -->
 
 <c:choose>
		<c:when test="${!customer.isLoggedIn()}"><%@include file="HeadNavBar.jsp"%></c:when>
		<c:when test="${customer.isLoggedIn()}"><%@include file="LoggedInNavBar.jsp"%></c:when>
</c:choose>

	<!-- Thumbnails -->
	<!-- I added the style in the container div -->
	<c:choose>
		<c:when test="${sr.getSearchArray().size()==0}">
			No matches found...
		</c:when>
	</c:choose>
	<div class="container">
		Change View<p></p>
		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
				<a href="#" class="btn btn-primary" role="button">Grid View</a>
			</div>
		</div>
		<p></p>
		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
				<a href="#" class="btn btn-primary" role="button">Text Only</a>
			</div>
		</div>
	</div>
	
<!-- First Result on row -->
	<div class="container" style="margin-left: 25%">
	  <c:forEach var="i" begin="${sr.getStart()}" end="${sr.getStart()+4}">
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
									<a href="AddToCartServlet?productToAdd=${sr.getAResult(i*2).getName()}&cameFrom=search" class="btn btn-primary" role="button">Add to Cart</a>
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
									<a href="AddToCartServlet?productToAdd=${sr.getAResult((i*2)+1).getName()}&cameFrom=search" class="btn btn-primary" role="button">Add to Cart</a>
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
	
						<!-- Pagination -->
		<div id="paginationMain">									<!-- Previous Button -->
	  		<c:choose>													
		      <c:when test="${sr.getSearchArray().size()>10}">
			    <div class="container">
				  <nav>
			 		 <ul class="pagination">
			  		    <c:choose>
						  <c:when test="${sr.getStart()>4}">
				           <li>
				             <a href="UpdateSearchlet?pageNum=${sr.getStart()-5}" aria-label="Previous">
				              <span aria-hidden="true">&laquo;</span>
				             </a>
				          </li>
				         </c:when>
			            </c:choose>
			   																<!-- Page Number Buttons -->
			   <c:forEach var="i" begin="0" end="${sr.getNumOfPagesNeeded()-1}">
			    <li><a href="UpdateSearchlet?pageNum=${i*5}"><c:out value="${i+1}" /></a></li>
			    
				</c:forEach>												<!-- Next Button -->
						<c:choose>
							<c:when test="${sr.isShowNextIcon()}">
								<li>
									<a href="UpdateSearchlet?pageNum=${sr.getStart()+5}" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
								</li>
							</c:when>
						</c:choose>

					</ul>
			</nav>
			</div>
		</c:when>
	</c:choose>	
	</div>	
	<!-- End Pagination -->

</body>
</html>