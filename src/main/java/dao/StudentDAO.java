package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Student;

public class StudentDAO {
	String url = "jdbc:mysql://localhost:3306/be8";
	String dbuser = "root";
	String dbpassword = "";
	
	public Student getStudentByLoginIdAndPassword(String loginId, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, dbuser, dbpassword);
			
			String SqlQuery = "SELECT * FROM students WHERE login_id = '" + loginId + "' AND password = '" + password + "'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SqlQuery);
			if (resultSet.next()) { 
				return Student.builder()
						.id(resultSet.getInt("id"))
						.name(resultSet.getString("name"))
						.className(resultSet.getString("class_name"))
						.build();
			} 
			
		} catch (SQLException e) {
			System.err.println("Error connecting to the database: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Student> getStudentListByClassName(String className) {
		ArrayList<Student> studentList = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, dbuser, dbpassword);
			
			String SqlQuery = "SELECT * FROM students";
			if (className != null) {
				SqlQuery = "SELECT * FROM students WHERE class_name = '" + className + "'";
			}
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SqlQuery);
			
			while (resultSet.next()) { 
				studentList.add(Student.builder()
						.id(resultSet.getInt("id"))
						.name(resultSet.getString("name"))
						.className(resultSet.getString("class_name"))
						.build());
			}
		} catch (SQLException e) {
			System.err.println("Error connecting to the database: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return studentList;
	}
	
	public boolean addNewStudent(String name, String className, String loginId, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, dbuser, dbpassword);

			String SqlQuery = "INSERT INTO students (name, class_name, login_id, password) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, className);
			preparedStatement.setString(3, loginId);
			preparedStatement.setString(4, password);

			int rowsAffected = preparedStatement.executeUpdate();
			
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Error connecting to the database: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
