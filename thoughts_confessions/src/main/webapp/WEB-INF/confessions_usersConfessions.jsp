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
    <title>All Your Confessions</title>
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
			<a href="/confessions" class="navConfessionsLinks">Back to Confessions</a>
			<a href="/logout" class="navConfessionsLinks">Logout</a>
		</div>
	</div>
	
	<div class="confessionsMain">
			<h3 class="dashHeader">YOU HAVE BEEN CONFESSING...</h3>
			
	<div class="confessionsMainBottom">
		<c:forEach var="confession" items="${user.confessions}">
				<div class="eachConfessionDiv">
					<p><c:out value="${confession.confessionContent}" /></p>
						<div class="editLinks">
							<a href="/confessions/${confession.id}" class="confessionLinks">view</a>
							<a href="/confessions/edit/${confession.id}" class="confessionLinks">edit</a>
							<a href="/confessions/delete/${confession.id}" class="confessionLinks">delete</a>
						</div>
				</div>
		</c:forEach>
	</div>
	</div>
		
</body>
</html>
