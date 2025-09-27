<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="styles.css">
<title>Student</title>
</head>
<body>
	<h1>Student List</h1>
	
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
	
	<br>
	<button>
		<a href="student-form.html" class="btn-link">Add new student</a>
	</button>
</body>
</html>