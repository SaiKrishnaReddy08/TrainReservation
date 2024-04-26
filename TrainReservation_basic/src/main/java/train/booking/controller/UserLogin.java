package train.booking.controller;
import java.sql.*;

import train.booking.dao.DBConnection;

public class UserLogin {
	public static boolean login(String userId, String password){
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement s = con.prepareStatement("SELECT * FROM users WHERE user_id = ? and password = ?");
			s.setString(1, userId);
			s.setString(2, password);
			ResultSet rs = s.executeQuery();
			
			if(rs.next()) {
				System.out.println("Login Successfull.");
				return true;
			}
			System.out.println("Wrong Username or Password.");
			return false;
		}
		catch(SQLException e) {
			System.out.println("Login Failed. Try again later....");
			return false;
		}
	}
}
