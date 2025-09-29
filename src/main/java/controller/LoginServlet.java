package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Student;

import java.io.IOException;

import dao.StudentDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check login status
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("loginId") != null) {
			response.sendRedirect("home");
		} else {
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check login detail
		String loginId = request.getParameter("login_id");
		String password = request.getParameter("password");
		StudentDAO studentDAO = new StudentDAO();

		Student student = studentDAO.getStudentByLoginIdAndPassword(loginId, password);
		
		if (student != null) {
			// Create new session with login id
			HttpSession session = request.getSession(true);
			session.setAttribute("loginId", loginId);
			session.setAttribute("studentName", student.getName());
			
//			request.setAttribute("studentName", student.getName());
			RequestDispatcher dispatcher = request.getRequestDispatcher("home");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Login failed");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
