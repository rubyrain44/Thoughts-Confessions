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
    <title>Login</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/styles.css"> <!-- change to match your file/naming structure -->
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body class="index">
   <div class="indexHeader">
   		<h1>What's On Your Mind?</h1>
   </div>
   
   <div class="loginForm">
		<div>
   			<h3>LOGIN</h3>
   		</div>
   		<div>
	   		<form:form action="/login" method="POST" modelAttribute="loginUser">
		   		<div>
		   			<form:label path="email">Email: </form:label>
		   			<form:input type="email" path="email" class="form-control"/>
		   			<form:errors path="email" style="color: red"/>
		   		</div>
				<div>
		   			<form:label path="password">Password: </form:label>
		   			<form:input type="password" path="password" class="form-control"/>
		   			<form:errors path="password" style="color: red"/>
		   		</div>	   			   		
		   		<input type=submit value="Submit" class="submitBttn">
	   		</form:form>
   		</div>
   </div>
   		
   		<div class="footer">
   			<a href="/register">Create An Account</a>
   			<a href="/about" class="about">About Page</a>
   		</div>
</body>
</html>
