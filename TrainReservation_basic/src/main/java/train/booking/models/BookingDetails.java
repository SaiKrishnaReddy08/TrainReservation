package train.booking.models;

import java.util.HashMap;
import java.util.Map;

public class BookingDetails {
	
	public int booking_id;
	public String userId;
	public int trainNumber;
	public String doj;
	public String coach;
	public int noOfSeats;
	public float totalFare;
	public Map<Integer, String> passengers = new HashMap<Integer, String>();
	
	public BookingDetails(int booking_id, String userId, int trainNumber, String doj, String coach, int noOfSeats, float totalFare, Map<Integer, String> passengers) {
		this.booking_id = booking_id;
		this.userId = userId;
		this.trainNumber = trainNumber;
		this.doj = doj;
		this.coach = coach;
		this.noOfSeats = noOfSeats;
		this.totalFare = totalFare;
		this.passengers = passengers;
	}
}
