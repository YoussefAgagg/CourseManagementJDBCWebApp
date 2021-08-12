<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Course</title>
</head>
<body>
	<c:set var="errMsg" value="${message}" />
	<c:set var="course" value="${course}" />
	<c:set var="displayForm" value="${true}" />

	<jsp:useBean id="teacherBean"
		class="email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Teacher"
		scope="session" />
	<c:catch var="teacherBeanErr">
		<c:set var="teachers" value="${teacherBean.getTeachers()}" />
	</c:catch>
	<c:if test="${teacherBeanErr != null}">
		<c:set var="errMsg" value="${err.message}" />
	</c:if>

	<h2>Add Course:</h2>
	<c:if test="${errMsg != null}">
		<span style="color: red;"> <c:out value="${errMsg}"></c:out>
		</span>
	</c:if>
	<form method="post" action="initServlet">
		 Name: <input
			type="text" name="name" value="${course.name }"> <br>
		Credits : <input type="text" name="credits" value="${course.credits }">
		<br> Teacher : <select name="teacherid">
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
		</select> <br>
		<c:choose>
			<c:when test="${course != null}">
			<input type="hidden" name="courseid" value="${course.id }">
			<input type="hidden" name="action" value="updateCourse">
				<button type="submit" name="submit">update</button>
			</c:when>
			<c:otherwise>
			<input type="hidden" name="action" value="addCourse">
				<button type="submit" name="submit">Add</button>
			</c:otherwise>
		</c:choose>
		
	</form>
<c:set var="course" value="${null }"> </c:set>
</body>
</html>