<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Student Form</h1>
	<form action="add-student.jsp" method="post">
		<label for="fname">Student name:</label><br> 
		<input type="text" id="name" name="name" value=""><br> 
		<label for="lname">Class name: </label><br> 
		<input type="text" id="class_name" name="class_name" value=""><br>
		<br> 
		<input type="submit" value="Submit">
	</form>
</body>
</html>