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
    <title>Share A Thought</title>
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
			<a href="/thoughts" class="navThoughtsLinks">Back to Thoughts</a>
			<a href="/logout" class="navThoughtsLinks">Logout</a>
		</div>
		
		<div class="createThought">
			<h3>Share Your Thought</h3>
			
   		<form:form action="/thoughts/create" method="POST" modelAttribute="thought">
	   		<div>
	   			<form:label path="thoughtContent">Title: </form:label>
	   			<form:textarea rows="4" cols="70" path="thoughtContent" class="form-control"/>
	   			<form:errors path="thoughtContent" style="color: red"/>
	   		</div>
 		
	   		<input type=submit value="Submit" class="submitBttn">
   		</form:form>
		</div>
	</div>
</body>
</html>
