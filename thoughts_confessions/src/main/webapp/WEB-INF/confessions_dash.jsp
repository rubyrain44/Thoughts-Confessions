<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confessions Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/styles.css"> <!-- change to match your file/naming structure -->
    	<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body class="confessions">
	<div class="navBar">
		<h1>Welcome, <c:out value="${user.username}" /></h1>
		<div>
			<a href="/dashboard" class="navConfessionsLinks">Back to Choices</a>
			<a href="/logout" class="navConfessionsLinks">Logout</a>
		</div>
	</div>
	
	<div class="confessionsMain">
		<div class="confessionsMainTop">
			<div class="dashTopLinks">
				<a href="/confessions/add" class="addConfession">Add A Confession</a>
				<a href="/confessions/myConfessions" class="addConfession">View All My Confessions</a>
			</div>
			<h3 class="dashHeader">PEOPLE HAVE BEEN CONFESSING...</h3>
		</div>
	
		<div class="confessionsMainBottom">
			<c:forEach var="userConfession" items="${allConfessions}">
				<c:choose>			
					<c:when test="${user.id == userConfession.user.id }">
						<div class="eachConfessionDiv">
							<p><c:out value="${userConfession.confessionContent}" /></p>
							<div class="editLinks">
								<a href="/confessions/${userConfession.id}" class="confessionLinks">view</a>
								<a href="/confessions/edit/${userConfession.id}" class="confessionLinks">edit</a>
								<a href="/confessions/delete/${userConfession.id}" class="confessionLinks">delete</a>
							</div>
						</div>
					</c:when>					
				
					<c:otherwise>
						<div class="eachConfessionDiv">
							<p><c:out value="${userConfession.confessionContent}" /></p>
							<a href="/confessions/${userConfession.id}" class="confessionLinks">view</a>
						</div>
					</c:otherwise>								
				</c:choose>
			</c:forEach>
		</div>
	</div>
</body>
</html>
