<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<%
    String name = request.getParameter("name");
    String class_name = request.getParameter("class_name");

	// create connect to mysql server
	String url = "jdbc:mysql://localhost:3306/be8"; // Replace with your database details
	String username = "root"; // Replace with your MySQL username
	String password = "alicelocal"; // Replace with your MySQL password
	Class.forName("com.mysql.cj.jdbc.Driver");

	String SqlQuery = "INSERT INTO students (name, class_name) VALUES (?, ?)";
	try (Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery)) {
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, class_name);
		
		// Use the connection to execute the sql query
		int rowsAffected = preparedStatement.executeUpdate();
		%>
		<p><%= rowsAffected %> row(s) inserted successfully</p>
		<%
	} catch (SQLException e) {
		System.err.println("Error connecting to the database: " + e.getMessage());
	}
%>

<br>
<br>
<button>
	<a href="/dynamic-web-demo/student-list.jsp" class="btn-link">Back to list</a>
</button>
</body>
</html>