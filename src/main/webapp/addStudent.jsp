<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Student</title>
</head>
<body>

	<c:set var="errMsg" value="${message}" />

	<jsp:useBean id="courseDAO"
		class="email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.CourseDAO"
		scope="session" />
	<c:catch var="courseErr">
		<c:set var="courses" value="${courseDAO.getCourses()}" />
	</c:catch>
	<c:if test="${courseErr != null}">
		<c:set var="errMsg" value="${err.message}" />
	</c:if>

	<h2>Add Student:</h2>
	<c:if test="${errMsg != null}">
		<span style="color: red;"> <c:out value="${errMsg}"></c:out>
		</span>
	</c:if>
	<form method="post" action="studentServlet">

		First Name: <input type="text" name="firstName" required="required">
		<br> Last Name: <input type="text" name="lastName"
			required="required"> <br> course : <select
			name="coursesids" multiple="multiple">

			<c:forEach items="${courses}" var="course">

				<option value="${course.id}">${course.name}</option>


			</c:forEach>
		</select> <br> <input type="hidden" name="action" value="addStudent">
		<button type="submit" name="submit">Add</button>


	</form>

</body>
</html>