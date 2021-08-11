<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Teacher</title>
</head>
<body>
<c:set var="teacher" value="${teacher }"> </c:set>
	<c:set var="errMsg" value="${message}"/>
	<c:set var="displayForm" value="${true}"/>


	<h2>Add Teacher:</h2>
	<c:if test="${errMsg != null}">
		<span style="color: red;">
			<c:out value="${errMsg}"></c:out>
		</span>
	</c:if>
	<form method="post" action="initServlet">
	
		First Name: <input type="text" name="firstName" value="${teacher.firstName }"> <br>
		Last Name: <input type="text" name="lastName"  value="${teacher.lastName }"> <br>
		Designation : <input type="text" name="designation"  value="${teacher.designation }"> <br>
		<c:choose>
			<c:when test="${teacher != null}">
			<input type="hidden" name="teacherid" value="${teacher.id }">
			<input type="hidden" name="action" value="updateTeacher">
				<button type="submit" name="submit">update</button>
			</c:when>
			<c:otherwise>
			<input type="hidden" name="action" value="addTeacher">
				<button type="submit" name="submit">Add</button>
			</c:otherwise>
		</c:choose>
	</form>

</body>
</html>