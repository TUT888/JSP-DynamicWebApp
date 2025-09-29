<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="styles.css">
<title>Login Form</title>
</head>
<body>
	<h1>Login Form</h1>
	
	<p class="error-message">${errorMessage}</p>
	
	<form action="login" method="POST">
		<label for="username">Username:</label><br> 
		<input type="text" id="username" name="username" value=""><br> 
		<label for="password">Password:</label><br> 
		<input type="password" id="password" name="password" value=""><br>
		<br> 
		<input type="submit" value="Submit">
	</form>
</body>
</html>