<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Teacher</title>
</head>
<body>
	<c:set var="errMsg" value="${message}" />



	<h2>Add Teacher:</h2>
	<c:if test="${errMsg != null}">
		<span style="color: red;"> <c:out value="${errMsg}"></c:out>
		</span>
	</c:if>
	<form method="post" action="teacherServlet">

		First Name: <input type="text" name="firstName" required="required">
		<br> Last Name: <input type="text" name="lastName"
			required="required"> <br> Designation : <input
			type="text" name="designation" required="required"> <br>

		<input type="hidden" name="action" value="addTeacher">
		<button type="submit" name="submit">Add</button>

	</form>

</body>
</html>