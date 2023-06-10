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
   
   <div class="aboutDiv">
   		<p>What's On Your Mind? is a web site I built where users can anonymously share whatever is on their mind, be it a thought
   		or a confession. I wanted to build a fun social interactive web site where users can freely voice whatever they choose
   		with one another, while allowing the users of the web site to comment and like the content. I think we all have lots on our
   		minds sometimes- whether it be funny, ridiculous, or just heavy. I liked the idea of a place where people could
   		talk about whatever it may be without the fear or worry of who said what, and just interact based on content alone.</p>
   </div>
   
   <a href="/">Go Back</a>

</body>
</html>
