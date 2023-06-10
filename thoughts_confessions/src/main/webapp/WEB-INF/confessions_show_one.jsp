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
    <title>Confession</title>
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
		
		<div class="oneThoughtPage">
			<div class="oneConfession">
				<p class="oneConfessionContent"><c:out value="${oneConfession.confessionContent}" /></p>
				
				<div class="confessionComments">
					<a href="/confessions/like/${oneConfession.id}" class="likeActionsConfession">👍🏻 Like This</a>
					<a href="/confessions/unlike/${oneConfession.id}" class="likeActionsConfession">👎🏻 Unlike This</a>
					<p class="likesConfessions"> <c:out value="${oneConfession.userLikesOnConfession.size()}" /> likes!</p>
					
					<c:forEach var="comment" items="${oneConfession.confessionComments}">
						<c:choose>
							<c:when test="${user.id == comment.user.id}">
								<div class="confessionCommentBox">
									<p class="confessionComment"><c:out value="${comment.commentContent}" /></p>
									<a href="/confessions/delete/${oneConfession.id}/comment/${comment.id}" class="commentConfessionActions">delete</a>
								</div>
							</c:when>
							
							<c:otherwise>
								<p class="confessionComment"><c:out value="${comment.commentContent}"/></p>
							</c:otherwise>						
						</c:choose>	
					</c:forEach>
				</div>
			</div>
			
			<div class="confessionCommentDiv">
		   		<form:form action="/confessions/createComment/${oneConfession.id}" method="POST" modelAttribute="commentConfession">
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
