package train.booking.models;

public class Train {
	public int trainNumber;
	public String name;
	public String source;
	public String destination;
	public String doj;
	public int slSeats;
	public int ac1Seats;
	public int ac2Seats;
	public int ac3Seats;
	public float slPrice;
	public float ac1Price;
	public float ac2Price;
	public float ac3Price;
	
	public Train(int trainNumber, String name, String source, String destination, String doj, int slSeats,
			int ac1Seats, int ac2Seats, int ac3Seats, float slPrice, float ac1Price, float ac2Price, float ac3Price) {
		
		this.trainNumber = trainNumber;
		this.name = name;
		this.source = source;
		this.destination = destination;
		this.doj = doj;
		this.slSeats = slSeats;
		this.ac1Seats = ac1Seats;
		this.ac2Seats = ac2Seats;
		this.ac3Seats = ac3Seats;
		this.slPrice = slPrice;
		this.ac1Price = ac1Price;
		this.ac2Price = ac2Price;
		this.ac3Price = ac3Price;
	}
	
}
