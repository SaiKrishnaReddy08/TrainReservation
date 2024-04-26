package train.booking.view;
import java.sql.SQLException;
import java.util.Scanner;

import exceptions.EmptyEntryException;
import exceptions.InvalidEntryException;
import train.booking.controller.TrainManagement;
import train.booking.controller.UserLogin;
import train.booking.controller.UserRegistration;
import train.booking.models.User;


public class App 
{	
	// Storing the User ID of User who logged in. NULL if logout.
	public static String loggedInUserId = null;
	
	static Scanner sc = new Scanner(System.in);
	static UserRegistration registrar = new UserRegistration();
	
	// Method to get User details and return User Object.
	public static User getUserDetails() {
		try {
			System.out.println("\nEnter the below User Details");
			System.out.printf("%10s : ","UserID");
			sc.nextLine();
			String userId = sc.nextLine();
			if(userId.trim().equals("")) {throw new EmptyEntryException();}
			if(!registrar.isValidUserId(userId)) {throw new InvalidEntryException("User Id already taken.");}
			
			System.out.printf("%10s : ","Full Name");
			String name = sc.nextLine();
			if(name.trim().equals("")) {throw new EmptyEntryException();}  
			
			
			int age;
			while(true) {
				System.out.printf("%10s : ","Age");
				try{
					age = sc.nextInt();
					break;
				}
				catch(Exception e){
					System.out.println("    Invalid Age.");
					sc.nextLine();
				}
			}
			
			 
			System.out.printf("%10s : ","Gender");
			sc.nextLine();
			String gender = sc.nextLine();
			if(gender.trim().equals("")) {throw new EmptyEntryException();}
			
			System.out.printf("%10s : ","Phone Number");
			String phNo = sc.nextLine();
			if(phNo.trim().equals("")) {throw new EmptyEntryException();}
			if(!registrar.isValidPhoneNumber(phNo)) {throw new InvalidEntryException("");}
			
			System.out.printf("%10s : ","Address");
			String address = sc.nextLine();
			if(address.trim().equals("")) {throw new EmptyEntryException();}
			
			System.out.printf("%10s : ","Set Password");
			String pwd = sc.nextLine();
			if(pwd.trim().equals("")) {throw new EmptyEntryException();}
			if(!registrar.isValidPassword(pwd)) {throw new InvalidEntryException("Password should contain atleast 4 characters.");}
			
			
			User newUser = new User(userId, pwd, name, age, gender, phNo, address);
			return newUser;
		}
		catch(EmptyEntryException e) {
			System.out.println(e.getMessage());
			return null;
		} 
		catch (InvalidEntryException e) {
			System.out.println(e.getMessage());
			return null;
		}
		catch (SQLException e1) {
			System.out.println("Registration Failed.");
			return null;
		} 
	}
	
	public static String getLoginDetails() {
		try {
			System.out.println("Enter the Login Details :");
			System.out.print("User ID   : ");
			sc.nextLine();
			String id = sc.nextLine();
			if(id.trim().equals("")) {throw new EmptyEntryException();}
			
			System.out.print("Password  : ");
			String pwd = sc.nextLine();
			if(pwd.trim().equals("")) {throw new EmptyEntryException();}
			
			return id+" "+pwd;
		}
		catch(EmptyEntryException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
    public static void main( String[] args )
    {
    	boolean quit = false;
    	while (!quit) {
    		System.out.println("\n 1. Register New User\n 2. Login\n 3. Book a train ticket\n 4. Show My Booking\n 5. Cancel Ticket\n 6. Logout\n 7. Quit");
    		System.out.print("Enter your choice:");
    		
    		// Getting Choice from user
    		int choice;
    		try {
    			choice = sc.nextInt();
    		}catch(Exception e) {
    			System.out.println("Select Valid Option.");
    			sc.nextLine();
    			continue;
    		}
			
            TrainManagement tm = new TrainManagement();
            
            switch(choice){
            	case 1: // Registering New User
            		User details = getUserDetails();
            		if(details!=null) {
            			registrar.registerNewUser(details);
            		}
            		
            		break;
            		
            	case 2: // Login
            		if(loggedInUserId != null) {
            			System.out.println("Already Logged In...");
            			break;
            		}
            		String loginDetails = getLoginDetails();
            		if(loginDetails!=null) {
            			String arr[] = loginDetails.split(" ");
                		String id = arr[0];
                		String pwd = arr[1];
                		boolean loginSuccess = UserLogin.login(id, pwd);
                		if(loginSuccess) {
                			loggedInUserId = id;
                		}
            		}
            		
            		break;
            		
            	case 3: // Book a train ticket
            		// Checking whether user is logged in or not
            		if(loggedInUserId==null) {
            			System.out.println("Login to your account to initiate Booking...");
            			break;
            		}
            		// If logged in, initiates booking.
            		else {
            			tm.bookTicket(loggedInUserId);
            		}
            		break;
            		
            	case 4:
            		if(loggedInUserId==null) {
            			System.out.println("Login to your account see your Bookings...");
            			break;
            		}
            		else {
            			tm.showMyBookings(loggedInUserId);
            		}
            		break;
            		
            	case 5:
            		if(loggedInUserId==null) {
            			System.out.println("Login to your account...");
            			break;
            		}
            		// If logged in, initiates booking.
            		else {
            			tm.cancelTicket(loggedInUserId);
            		}
            		break;
            		
            	case 6:
            		if(loggedInUserId==null) {
            			System.out.println("You are already Logged Out...");
            			break;
            		}
            		// If logged in, initiates booking.
            		else {
            			loggedInUserId = null;
            		}
            		break;
            		
            	case 7:
            		loggedInUserId = null;
            		quit = true;
            		System.out.println("   THANK YOU. VISIT AGAIN.  ");
            		break;
            		
            	default:
            		System.out.println("Select Valid Option");
            		break;
            }
        
        }
    }
}
