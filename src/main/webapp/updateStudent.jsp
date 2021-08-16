<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Student</title>
</head>
<body>
<c:set var="student" value="${student}" />
	<c:set var="errMsg" value="${message}"/>

<jsp:useBean id="courseBean" class="email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Course" scope="session"/>
	<c:catch var="courseBeanErr">
		<c:set var="courses" value="${courseBean.getCourses()}"/>
	</c:catch>
	<c:if test="${courseBeanErr != null}">
		<c:set var="errMsg" value="${err.message}"/>
	</c:if>

	<h2>Update Student:</h2>
	<c:if test="${errMsg != null}">
		<span style="color: red;">
			<c:out value="${errMsg}"></c:out>
		</span>
	</c:if>
	<form method="post" action="studentServlet">
	
		First Name: <input type="text" required="required" name="firstName" value="${student.firstName }"> <br>
		Last Name: <input type="text" required="required" name="lastName" value="${student.lastName }"> <br>
		course : <select name="coursesids" multiple="multiple" >
		
			<c:forEach items="${courses}" var="course">
			<option value="${course.id}">${course.name}</option>	
			</c:forEach>		
		</select>
		<br>
			<input type="hidden" name="studentid" value="${student.id }">
			<input type="hidden" name="action" value="updateStudent">
				<button type="submit" name="submit">update</button>
			
	
	</form>
</body>
</html>