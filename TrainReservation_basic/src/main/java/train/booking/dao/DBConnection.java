package train.booking.dao;
import java.sql.*;

public class DBConnection {
	public static Connection getConnection() {
		Connection con = null;
		try {
			String url =  "jdbc:mysql://localhost:3306/trainmanagement";
			con = DriverManager.getConnection(url, "root", "root");
		}
		catch(Exception e) {
			System.out.println("DB Connection Failed");
		}
		return con;
	}
}
