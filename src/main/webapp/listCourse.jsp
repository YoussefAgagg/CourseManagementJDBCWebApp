<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Courses</title>
</head>
<body>
	<c:catch var="err">
		<jsp:useBean id="courseDAO"
			class="email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.CourseDAO"></jsp:useBean>
		<c:set var="courses" value="${courseDAO.getCourses() }"></c:set>
	</c:catch>
	<c:choose>
		<c:when test="${err!=null }">
			<c:set var="errMsg" value="${err.message}" />
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	<h2>Courses:</h2>
	<c:if test="${errMsg != null}">
		<span style="color: red;"> <c:out value="${errMsg}"></c:out>
		</span>
	</c:if>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Credits</th>
			<th>Teacher</th>
		</tr>
		<c:forEach items="${courses }" var="course">
			<tr>
				<td>${course.id}</td>
				<td>${course.name}</td>
				<td>${course.credits}</td>

				<c:choose>
					<c:when test="${course.teacher != null}">
						<td>${course.teacher.firstName}</td>
					</c:when>
					<c:otherwise>
						<td></td>
					</c:otherwise>
				</c:choose>
				<td><form action="courseServlet" method="post">
						<input type="hidden" name="action" value="updateFromListCourse">
						<input type="hidden" name="courseid" value="${course.id }">
						<button type="submit" name="submit">update</button>
					</form></td>
				<td><form action="courseServlet" method="post">
						<input type="hidden" name="action" value="deleteFromListCourse">
						<input type="hidden" name="courseid" value="${course.id }">
						<button type="submit" name="submit">delete</button>
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<p>number of courses:${courses.size() }</p>
	<a href="addCourse.jsp">add course</a>
	<br>
	<a href="index.html">main</a>
	<br>
</body>

</html>