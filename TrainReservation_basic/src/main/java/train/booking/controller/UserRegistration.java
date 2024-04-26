package train.booking.controller;

import java.sql.*;

import train.booking.dao.TrainManagementDAO;
import train.booking.models.User;

public class UserRegistration {
	TrainManagementDAO tmd = new TrainManagementDAO();
	
	public boolean isValidUserId(String userId) throws SQLException{
		return tmd.isUserIdAvailable(userId);
	}
	
	public boolean isValidPassword(String pwd){
		if(pwd.length()<4) {
			return false;
		}
		return true;
	}
	
	public boolean isValidPhoneNumber(String phNo) throws SQLException{
		
		if(tmd.isPhoneRegistered(phNo)) {
			System.out.println("Entered Phone Number is already registered.");
			return false;	
		}
		if(!phNo.matches("[6-9][0-9]{9}")) {
			System.out.println("Please enter the 10 digit Phone Number starting with 6-9.");
			return false;
		}
		return true;
	}
	
	
	// Registers a New User and return a boolean value representing the success status of Registration.
	public boolean registerNewUser(User newUser){
		try {
			tmd.executeRegisterNewUser(newUser);
			System.out.println("User Registered Successfully.");
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("User Not Registered. Issue with DB.");
			return false;
		}
		
			
		
	}
}
