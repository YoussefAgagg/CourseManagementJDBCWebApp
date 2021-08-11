<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Teachers</title>
</head>
<body>
	<c:catch var="err">
		<jsp:useBean id="teacherBean"
			class="email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Teacher" />
		<c:set var="teachers" value="${teacherBean.getTeachers()}" />
	</c:catch>
	<c:choose>
		<c:when test="${err != null}">
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
			<th>First Name</th>
			<th>Last Name</th>
			<th>Designation</th>
		</tr>
		<c:forEach items="${teachers}" var="teacher">
			<tr>
				<td>${teacher.id}</td>
				<td>${teacher.firstName}</td>
				<td>${teacher.lastName}</td>
				<td>${teacher.designation}</td>
				<td><form action="initServlet" method="post">
						<input type="hidden" name="action" value="updateFromListTeacher">
						<input type="hidden" name="teacherid" value="${teacher.id }">
						<button type="submit" name="submit">update</button>
					</form></td>
				<td><form action="initServlet" method="post">
						<input type="hidden" name="action" value="deleteFromListTeacher">
						<input type="hidden" name="teacherid" value="${teacher.id }">
						<button type="submit" name="submit">delete</button>
					</form></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>