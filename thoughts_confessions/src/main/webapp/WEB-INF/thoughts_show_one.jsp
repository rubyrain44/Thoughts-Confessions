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
    <title>Thought</title>
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
		
		<div class="oneThoughtPage">
			<div class="oneThought">
				<p class="oneThoughtContent"><c:out value="${oneThought.thoughtContent}" /></p>
				
				<div class="thoughtComments">
					<a href="/thoughts/like/${oneThought.id}" class="likeActionsThoughts">👍🏻 Like This</a>
					<a href="/thoughts/unlike/${oneThought.id}" class="likeActionsThoughts">👎🏻 Unlike This</a>
					<p class="likesThoughts"><c:out value="${oneThought.userLikesOnThought.size()}" /> likes!</p>
					
					<c:forEach var="comment" items="${oneThought.thoughtComments}">
						<c:choose>
							<c:when test="${user.id == comment.user.id}">
							<div class="thoughtCommentBox">
								<p class="thoughtComment"><c:out value="${comment.commentContent}"/></p>
								<a href="/thoughts/delete/${oneThought.id}/comment/${comment.id}" class="commentThoughtActions">delete</a>
							</div>
							</c:when>
							
							<c:otherwise>
								<p class="thoughtComment"><c:out value="${comment.commentContent}"/></p>
							</c:otherwise>						
						</c:choose>	
					</c:forEach>
				</div>
			</div>
			
				<div class="thoughtCommentDiv">
			   		<form:form action="/thoughts/createComment/${oneThought.id}" method="POST" modelAttribute="commentThought">
				   		<div>
				   			<form:label path="commentContent">Leave a comment: </form:label>
				   			<form:textarea rows="3" cols="50" path="commentContent" class="form-control"/>
				   			<form:errors path="commentContent" style="color: red"/>
				   		</div>
			 		
				   		<input type=submit value="Submit" class="submitBttn">		   		
			   		</form:form>
				</div>
			</div>
		</div>
</body>
</html>
