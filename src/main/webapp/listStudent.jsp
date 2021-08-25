<%@page import="email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Student"%>
<%@page import="email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Students</title>
</head>
<body>
	<c:catch var="err">
		<jsp:useBean id="studentDAO"
			class="email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.StudentDAO" />
		<c:set var="students" value="${studentDAO.getStudents()}" />
	</c:catch>
	<c:choose>
		<c:when test="${err != null}">
			<c:set var="errMsg" value="${err.message}" />
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	<h2>Students:</h2>
	<c:if test="${errMsg != null}">
		<span style="color: red;"> <c:out value="${errMsg}"></c:out>
		</span>
	</c:if>
	<table>
		<tr>
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>courses</th>
		</tr>
		<c:forEach items="${students}" var="student">
			<tr>
				<td>${student.id}</td>
				<td>${student.firstName}</td>
				<td>${student.lastName}</td>
				<td>
				
				${student.courses}
				
				</td>
				<td><form action="studentServlet" method="post">
						<input type="hidden" name="action" value="updateFromListStudent">
						<input type="hidden" name="studentid" value="${student.id }">
						<button type="submit" name="submit">update</button>
					</form></td>
				<td><form action="studentServlet" method="post">
						<input type="hidden" name="action" value="deleteFromListStudent">
						<input type="hidden" name="studentid" value="${student.id }">
						<button type="submit" name="submit">delete</button>
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<p>number of students:${students.size() }</p>
	<a href="addStudent.jsp" >add student</a><br>
	<a href="index.html">main</a><br>
</body>
</html>