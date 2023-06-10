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
    <title>Thoughts Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/styles.css"> <!-- change to match your file/naming structure -->
    	<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body class="thoughts">
	<div class="navBar">
		<h1>Welcome, <c:out value="${user.username}" /></h1>
		<div>
			<a href="/dashboard" class="navThoughtsLinks">Back to Choices</a>
			<a href="/logout" class="navThoughtsLinks">Logout</a>
		</div>
	</div>
	
	<div class="thoughtsMain">
		<div class="thoughtsMainTop">
				<div class="dashTopLinks">
				<a href="/thoughts/add" class="addThought">Add A Thought</a>
				<a href="/thoughts/myThoughts" class="addThought">View All My Thoughts</a>
			</div>
			<h3 class="dashHeader">PEOPLE HAVE BEEN THINKING...</h3>
		</div>
		
		<div class="thoughtsMainBottom">
			<c:forEach var="userThought" items="${allThoughts}">
				<c:choose>			
					<c:when test="${user.id == userThought.user.id }">
						<div class="eachThoughtDiv">
							<p><c:out value="${userThought.thoughtContent}" /></p>
							<div class="editLinks">
								<a href="/thoughts/${userThought.id}" class="thoughtsLinks">view</a>
								<a href="/thoughts/edit/${userThought.id}" class="thoughtsLinks">edit</a>
								<a href="/thoughts/delete/${userThought.id}" class="thoughtsLinks">delete</a>
							</div>
						</div>
					</c:when>					
				
					<c:otherwise>
						<div class="eachThoughtDiv">
							<p><c:out value="${userThought.thoughtContent}" /></p>
							<a href="/thoughts/${userThought.id}" class="thoughtsLinks">view</a>
						</div>
					</c:otherwise>								
				</c:choose>
			</c:forEach>
		</div>
	</div>
</body>
</html>
