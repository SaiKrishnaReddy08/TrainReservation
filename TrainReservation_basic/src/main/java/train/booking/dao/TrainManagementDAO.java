package train.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import train.booking.models.BookingDetails;
import train.booking.models.Train;
import train.booking.models.User;

public class TrainManagementDAO {
	Connection con = DBConnection.getConnection();
	
	public void executeRegisterNewUser(User newUser) throws SQLException{
		PreparedStatement s = con.prepareStatement("INSERT INTO USERS VALUES(?,?,?,?,?,?,?)");
		s.setString(1, newUser.getUserId());
		s.setString(2, newUser.getPassword());
		s.setString(3, newUser.getFullName());
		s.setInt(4, newUser.getAge());
		s.setString(5, newUser.getGender());
		s.setString(6, newUser.getPhoneNumber());
		s.setString(7, newUser.getAddress());
		
		s.executeUpdate();
	}
	
	public void executeNewBooking(String userId, int trainNumber, String doj, String coach, int noOfSeats, String[] passengerNames ) throws SQLException {
		   
		int availableSeats = this.getNoOfAvailableSeats(trainNumber, doj, coach);
		// Reducing the number of seats available for the train in booked coach.
			PreparedStatement s1 = con.prepareStatement("UPDATE train_availability SET "+ coach + "_seats = "+ coach+"_seats -"+noOfSeats+ " where train_number = ? and DOJ =str_to_date(?,\"%d-%m-%Y\")");
			s1.setInt(1, trainNumber); 
			s1.setString(2, doj);
			s1.executeUpdate();
			
		// Calculate Total Fare
			PreparedStatement s2 = con.prepareStatement("SELECT "+coach+"_price from trains where train_number = ?");
			s2.setInt(1, trainNumber); 
			ResultSet rs = s2.executeQuery();
			rs.next();
			float totalFare = rs.getInt(coach+"_price") * noOfSeats;
			
		// Adding booking details.
			PreparedStatement s = con.prepareStatement("INSERT INTO booking_details(user_id, train_number, DOJ, coach, no_of_seats, total_fare) VALUES(?,?,str_to_date(?,\"%d-%m-%Y\"),?,?,?)");
			s.setString(1, userId);
			s.setInt(2, trainNumber);
			s.setString(3, doj);
			s.setString(4, coach);
			s.setInt(5, noOfSeats);
			s.setFloat(6, totalFare);
			s.executeUpdate();
			
	    // adding passenger details
			
			// getting PNR number
			ResultSet rs3 = con.createStatement().executeQuery("select max(booking_id) as pnr from booking_details");
			rs3.next();
			int pnr = rs3.getInt("pnr");
			
			// getting booked seat numbers
			ArrayList<Integer> bookedSeatNumbers = new ArrayList<>();
			PreparedStatement s4 = con.prepareStatement("select seat_number from booked_passenger_details where booking_id in (select booking_id from booking_details where train_number = ? and DOJ =str_to_date(?,\"%d-%m-%Y\") and coach = ?)");
			s4.setInt(1, trainNumber);
			s4.setString(2, doj);
			s4.setString(3, coach);
			ResultSet r = s4.executeQuery();
			while(r.next()) {
				bookedSeatNumbers.add(r.getInt("seat_number"));
			}
			

			
			int totalSeats = availableSeats + bookedSeatNumbers.size();
			
			// assigning vacant seat numbers to the passengers
			int foundSeats = 0;
			for(int j=1;j<=totalSeats; j++) {
				if(!bookedSeatNumbers.contains(j)) {
					int seatNumber = j;
					PreparedStatement s3 = con.prepareStatement("INSERT INTO booked_passenger_details VALUES(?,?,?)");
					s3.setInt(1, pnr);
					s3.setString(2, passengerNames[foundSeats]);
					s3.setInt(3, seatNumber);
					s3.executeUpdate();
					foundSeats +=1;
				}
				if(noOfSeats==foundSeats) {
					break;
				}
			}
	}
	
	public List<Train> filterBySourceDestination(String source, String destination, String doj) throws SQLException{
		List<Train> trains = new ArrayList<Train>();
		PreparedStatement s = con.prepareStatement("select * from trains join train_availability using(train_number) where source = ? and destination = ? and DOJ = str_to_date(?,\"%d-%m-%Y\")");
		s.setString(1, source);
		s.setString(2, destination);
		s.setString(3, doj);
		ResultSet rs = s.executeQuery();
		while(rs.next()) {
			int trainNummber = rs.getInt("train_number");
			String name = rs.getString("name");
			int slSeats = rs.getInt("sl_seats");
			int ac1Seats = rs.getInt("ac1_seats");
			int ac2Seats = rs.getInt("ac2_seats");
			int ac3Seats = rs.getInt("ac3_seats");
			float slPrice = rs.getInt("sl_price");
			float ac1Price = rs.getInt("ac1_price");
			float ac2Price  = rs.getInt("ac2_price");
			float ac3Price = rs.getInt("ac3_price");
			trains.add(new Train(trainNummber, name, source, destination, doj, slSeats,
					ac1Seats, ac2Seats, ac3Seats, slPrice, ac1Price, ac2Price, ac3Price));
		}
		return trains;
	}
	
	public List<BookingDetails> executeShowMyBookings(String userId) throws SQLException{
		List<BookingDetails> myBookings = new ArrayList<BookingDetails>();
		
		PreparedStatement s = con.prepareStatement("SELECT * from booking_details where user_id = ?" );
		s.setString(1, userId);
		ResultSet rs = s.executeQuery();
		while(rs.next()) {
			int bId = rs.getInt("booking_id");
			String uId = rs.getString("user_id");
			int tNo = rs.getInt("train_number");
			String doj = rs.getString("doj");
			String coach = rs.getString("coach");
			int noOfSeats = rs.getInt("no_of_seats");
			float totalFare = rs.getFloat("total_fare");
			
			// getting passenger details of respective booking ID (PNR)
			Map<Integer, String> passengers = new HashMap<Integer, String>();
			s = con.prepareStatement("select * from booked_passenger_details where booking_id = ?");
			s.setInt(1, bId);
			ResultSet rs1 = s.executeQuery();
			while(rs1.next()) {
				String name = rs1.getString("passenger_name");
				int seat = rs1.getInt("seat_number");
				passengers.put(seat, name);
			}
			
			myBookings.add(new BookingDetails(bId, uId, tNo, doj, coach, noOfSeats, totalFare, passengers));
		}
		return myBookings;
	}
	
	public void executeCancelTicket(int bId) throws SQLException {
		PreparedStatement s = con.prepareStatement("SELECT * FROM booking_details WHERE booking_id = ?" );
		s.setInt(1, bId);
		ResultSet rs = s.executeQuery();
		rs.next();
		String coach = rs.getString("coach");
		String doj = rs.getString("DOJ");
		int noOfSeats = rs.getInt("no_of_seats");
		int trainNumber = rs.getInt("train_number");
		
		s = con.prepareStatement("DELETE FROM booking_details WHERE booking_id = ?" );
		s.setInt(1, bId);
		s.executeUpdate();
		
		s = con.prepareStatement("DELETE FROM booked_passenger_details WHERE booking_id = ?" );
		s.setInt(1, bId);
		s.executeUpdate();

		s = con.prepareStatement("UPDATE train_availability SET "+ coach + "_seats = "+ coach+"_seats +"+noOfSeats+ " where train_number = ? and DOJ =str_to_date(?,\"%Y-%m-%d\")" );
		s.setInt(1, trainNumber);
		s.setString(2, doj);
		s.executeUpdate();
		
	}

	// Finds the number of available seats based on Train Number, DOJ, Coach.
	public int getNoOfAvailableSeats(int trainNumber, String doj, String coach) throws SQLException{
		PreparedStatement s0 = con.prepareStatement("SELECT * FROM train_availability WHERE train_number = ? and DOJ = str_to_date(?, \"%d-%m-%Y\")");
		s0.setInt(1, trainNumber);
		s0.setString(2, doj);
		ResultSet rs = s0.executeQuery();
		rs.next();
		return rs.getInt(coach+"_seats");
	}

	public boolean isSourceStationAvailable(String source) throws SQLException{
		PreparedStatement s0 = con.prepareStatement("SELECT * FROM trains WHERE source = ?");
		s0.setString(1, source);
		ResultSet rs = s0.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
		
	}
	
	public boolean isDestinationStationAvailable(String destination) throws SQLException{
		PreparedStatement s0 = con.prepareStatement("SELECT * FROM trains WHERE destination = ?");
		s0.setString(1, destination);
		ResultSet rs = s0.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	public boolean isUserIdAvailable(String userId) throws SQLException{
		Connection con = DBConnection.getConnection();
		PreparedStatement s = con.prepareStatement("select user_id from users where user_id = ?" );
		s.setString(1,userId);
		ResultSet rs = s.executeQuery();
		return !rs.next();
	}
	
	public boolean isPhoneRegistered(String phNo) throws SQLException{
		Connection con = DBConnection.getConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select phone_number from users where phone_number = " + phNo);
		
		return rs.next();
	}
}
