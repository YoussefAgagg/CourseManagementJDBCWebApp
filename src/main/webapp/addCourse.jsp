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

	<jsp:useBean id="teacherDAO"
		class="email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.TeacherDAO"
		 />
		
	<c:catch var="teacherErr">
		<c:set var="teachers" value="${teacherDAO.getTeachers()}" />
	</c:catch>
	
	<c:if test="${teacherErr != null}">
		<c:set var="errMsg" value="${err.message}" />
	</c:if>

	<h2>Add Course:</h2>
	
	<c:if test="${errMsg != null}">
		<span style="color: red;"> <c:out value="${errMsg}"></c:out>
		</span>
	</c:if>
	
	<form method="post" action="courseServlet">
		Name: <input required="required" type="text" name="name" /> <br>
		Credits : <input type="number" required="required" min="1"
			name="credits" /> <br> Teacher : <select name="teacherid">
			<option style="display: none"></option>
			<c:forEach items="${teachers}" var="teacher">
				<option value="${teacher.id}">${teacher.firstName}</option>
			</c:forEach>
		</select> <br> <input type="hidden" name="action" value="addCourse">
		<button type="submit" name="submit">Add</button>
	</form>

</body>
</html>