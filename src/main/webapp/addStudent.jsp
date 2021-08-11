<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Student</title>
</head>
<body>
<c:set var="student" value="${student}" />
	<c:set var="errMsg" value="${message}"/>
	<c:set var="displayForm" value="${true}"/>
<jsp:useBean id="courseBean" class="email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Course" scope="session"/>
	<c:catch var="courseBeanErr">
		<c:set var="courses" value="${courseBean.getCourses()}"/>
	</c:catch>
	<c:if test="${courseBeanErr != null}">
		<c:set var="errMsg" value="${err.message}"/>
	</c:if>

	<h2>Add Student:</h2>
	<c:if test="${errMsg != null}">
		<span style="color: red;">
			<c:out value="${errMsg}"></c:out>
		</span>
	</c:if>
	<form method="post" action="initServlet">
	
		First Name: <input type="text" name="firstName" value="${student.firstName }"> <br>
		Last Name: <input type="text" name="lastName" value="${student.lastName }"> <br>
		course : <select name="coursesids" multiple="multiple" >
		
			<c:forEach items="${courses}" var="course">
			<c:set var="isAdded" value="${false }"/>
			<c:if test="${student!=null }">
			
			</c:if>
			<c:if test="${!isAdded }">
			<option value="${course.id}">${course.name}</option>
			</c:if>
				
			</c:forEach>		
		</select>
		<br>
		<c:choose>
			<c:when test="${student != null}">
			<input type="hidden" name="studentid" value="${student.id }">
			<input type="hidden" name="action" value="updateStudent">
				<button type="submit" name="submit">update</button>
			</c:when>
			<c:otherwise>
			<input type="hidden" name="action" value="addStudent">
				<button type="submit" name="submit">Add</button>
			</c:otherwise>
		</c:choose>
	
	</form>

</body>
</html>