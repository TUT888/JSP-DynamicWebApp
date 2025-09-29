package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static String url = "jdbc:mysql://localhost:3306/be8";
	private static String dbuser = "root";
	private static String dbpassword = "alicelocal";
	 
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, dbuser, dbpassword);
			
			return connection;
		} catch (SQLException e) {
			System.err.println("Error connecting to the database: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
