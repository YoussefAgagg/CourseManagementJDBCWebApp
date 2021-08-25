<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Course</title>
</head>
<body>
	<c:set var="errMsg" value="${message}" />
	<c:set var="course" value="${course}" />

	<jsp:useBean id="teacherDAO"
		class="email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.TeacherDAO" />

	<c:catch var="teacherErr">
		<c:set var="teachers" value="${teacherDAO.getTeachers()}" />
	</c:catch>

	<c:if test="${teacherErr != null}">
		<c:set var="errMsg" value="${err.message}" />
	</c:if>

	<h2>Update Course:</h2>
	<c:if test="${errMsg != null}">
		<span style="color: red;"> <c:out value="${errMsg}"></c:out>
		</span>
	</c:if>
	<form method="post" action="courseServlet">
		Name: <input required="required" type="text" name="name"
			value="${course.name }"> <br> Credits : <input
			required="required" type="number" min="1" name="credits"
			value="${course.credits }"> <br> Teacher : <select
			name="teacherid">
			<option style="display: none"></option>
			<c:forEach items="${teachers}" var="teacher">
				<c:choose>
					<c:when test="${course.teacher.id == teacher.id}">
						<option value="${teacher.id}" selected="selected">${teacher.firstName}</option>
					</c:when>
					<c:otherwise>
						<option value="${teacher.id}">${teacher.firstName}</option>
					</c:otherwise>
				</c:choose>

			</c:forEach>
		</select> <br> <input type="hidden" name="courseid" value="${course.id }">
		<input type="hidden" name="action" value="updateCourse">
		<button type="submit" name="submit">update</button>
	</form>
</body>
</html>