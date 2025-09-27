<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dynamicwebdemo.model.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<title>Student</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
	<h1>Student List</h1>
	
	<%
	ArrayList<Student> studentList = (ArrayList<Student>) request.getAttribute("studentList");
	
	if (studentList.size() == 0) {
	%>
		<p>There isn't any student in this class</p>
	<%
	} else {
	%>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>name</th>
				<th>class_name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var = "student" items = "${studentList}">
				<tr>
					<td>${student.id}</td>
					<td>${student.name}</td>
					<td>${student.className}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%
	}
	%>
	
	<br>
	<button>
		<a href="student-form.jsp" class="btn-link">Add new student</a>
	</button>
</body>
</html>