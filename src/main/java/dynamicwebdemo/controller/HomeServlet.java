package dynamicwebdemo.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dynamicwebdemo.model.Student;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestParameter = request.getParameter("class");
		
		ArrayList<Student> studentList = new ArrayList<>();
		try {
			String url = "jdbc:mysql://localhost:3306/be8";
			String username = "root";
			String password = "alicelocal";
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected successfully!");
			System.out.println(requestParameter);
			
			String SqlQuery = "SELECT * FROM students";
			if (requestParameter != null) {
				SqlQuery = "SELECT * FROM students WHERE class_name = '" + requestParameter + "'";
			}
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SqlQuery);
			
			while(resultSet.next()){ 
				Student newStudent = new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("class_name"));
				studentList.add(newStudent);
			} 
		} catch (SQLException e) {
			System.err.println("Error connecting to the database: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: " + request.getContextPath());
		request.setAttribute("studentList", studentList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
