<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
</head>
<body>
	<h1>Login Form</h1>
	
	<p class="error-message">${errorMessage}</p>
	
	<form action="login" method="POST">
		<label for="login_id">Login ID:</label><br> 
		<input type="text" id="login_id" name="login_id" value=""><br> 
		<label for="password">Password:</label><br> 
		<input type="password" id="password" name="password" value=""><br>
		<br> 
		<input type="submit" value="Submit">
	</form>
	
	<p>Or <a href="register.jsp" class="btn-link">create new account</a></p>
</body>
</html>