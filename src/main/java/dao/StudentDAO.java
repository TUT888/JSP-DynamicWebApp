package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Student;
import db.DatabaseConnection;

public class StudentDAO {
	public Student getStudentByLoginIdAndPassword(String loginId, String password) {
		String SqlQuery = "SELECT * FROM students WHERE login_id = ? AND password = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery);) {
			
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, password);
			
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) { 
					return Student.builder()
							.id(resultSet.getInt("id"))
							.name(resultSet.getString("name"))
							.className(resultSet.getString("class_name"))
							.build();
				}
			} catch (SQLException e) {
				System.err.println("Error reading result set: " + e.getMessage());
			}
		} catch (SQLException e) {
			System.err.println("Error connecting to the database: " + e.getMessage());
		}
		
		return null;
	}
	
	public ArrayList<Student> getStudentListByClassName(String className) {
		if (className == null) {
			className = "be8";
		}
		String SqlQuery = "SELECT * FROM students WHERE class_name = ?";
		
		ArrayList<Student> studentList = new ArrayList<>();
		try (Connection connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery);) {
			
			preparedStatement.setString(1, className);
			
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) { 
					while (resultSet.next()) { 
						studentList.add(Student.builder()
								.id(resultSet.getInt("id"))
								.name(resultSet.getString("name"))
								.className(resultSet.getString("class_name"))
								.build());
					}
				}
			} catch (SQLException e) {
				System.err.println("Error reading result set: " + e.getMessage());
			}
		} catch (SQLException e) {
			System.err.println("Error connecting to the database: " + e.getMessage());
		}
		
		return studentList;
	}
	
	public boolean addNewStudent(String name, String className, String loginId, String password) {
		String SqlQuery = "INSERT INTO students (name, class_name, login_id, password) VALUES (?, ?, ?, ?)";
		
		try (Connection connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery);){
			
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, className);
			preparedStatement.setString(3, loginId);
			preparedStatement.setString(4, password);

			int rowsAffected = preparedStatement.executeUpdate();
			
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Error connecting to the database: " + e.getMessage());
		}
		
		return false;
	}
}
