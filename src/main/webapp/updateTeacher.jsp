<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Teacher</title>
</head>
<body>
	<c:set var="teacher" value="${teacher }">
	</c:set>
	<c:set var="errMsg" value="${message}" />


	<h2>Update Teacher:</h2>
	<c:if test="${errMsg != null}">
		<span style="color: red;"> <c:out value="${errMsg}"></c:out>
		</span>
	</c:if>
	<form method="post" action="teacherServlet">

		First Name: <input type="text" required="required" name="firstName"
			value="${teacher.firstName }"> <br> Last Name: <input
			type="text" required="required" name="lastName"
			value="${teacher.lastName }"> <br> Designation : <input
			type="text" required="required" name="designation"
			value="${teacher.designation }"> <br> <input
			type="hidden" name="teacherid" value="${teacher.id }"> <input
			type="hidden" name="action" value="updateTeacher">
		<button type="submit" name="submit">update</button>

	</form>

</body>
</html>