package train.booking.controller;
import java.util.Scanner;



import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import train.booking.dao.TrainManagementDAO;
import train.booking.models.BookingDetails;
import train.booking.models.Train;

public class TrainManagement {
	Scanner sc = new Scanner(System.in);
	TrainManagementDAO tmd = new TrainManagementDAO();
	
	public void bookTicket(String userId) {
		try 
		{
			// getting source station from user
			String source;
			while(true) {
				System.out.print("Enter the source station:");
				source = sc.nextLine();
				if(!tmd.isSourceStationAvailable(source)) {
					System.out.println("Entered source station is not available.");
				}
				else {
					break;
				}
			}
			// getting destination station from user
			String destination;
			while(true) {
				System.out.print("Enter the destination station:");
				destination = sc.nextLine();
				if(!tmd.isDestinationStationAvailable(destination)) {
					System.out.println("Entered Destination station is not available.");
				}
				else {
					break;
				}
			}
			
			
			//  getting doj from user
			String doj;
			while(true) {
				System.out.print("Enter the Date of Journey:");
				doj = sc.nextLine();
				if(!doj.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
					System.out.println("Invalid date");
					continue;
				}
				break;
			}
			
			List<Train> trains ;
			
			// map to store available Train Number and their respective coaches.
			Map<Integer, ArrayList<String>> trainCoaches = new HashMap<>();
			
			// filtering trains by given source, destination, doj.
			trains = tmd.filterBySourceDestination(source, destination, doj);
			
			// Displaying all available trains
			for(Train train: trains) {
				System.out.println("_".repeat(40));
				
				trainCoaches.put(train.trainNumber, new ArrayList<>());
				System.out.println("    "+train.trainNumber + "  --  " + train.name );
				System.out.println("  "+"-".repeat(36));
				System.out.printf("%10s   %10s   %10s \n" , "COACH", "AVAIL. SEATS", "PRICE/SEAT");
				System.out.println("  "+"-".repeat(36));
				if(train.slSeats != -1) {
					trainCoaches.get(train.trainNumber).add("sl");
					System.out.printf(" %10s   %10d   %10.2f \n" , "sl", train.slSeats, train.slPrice);
				}
				if(train.ac1Seats != -1) {
					trainCoaches.get(train.trainNumber).add("ac1");
					System.out.printf(" %10s   %10d   %10.2f \n", "ac1", train.ac1Seats, train.ac1Price);
				}
				if(train.ac2Seats != -1) {
					trainCoaches.get(train.trainNumber).add("ac2");
					System.out.printf(" %10s   %10d   %10.2f \n", "ac2", train.ac2Seats, train.ac2Price);
				}
				if(train.ac3Seats != -1) {
					trainCoaches.get(train.trainNumber).add("ac3");
					System.out.printf(" %10s   %10d   %10.2f \n", "ac3", train.ac3Seats, train.ac3Price);
				}
				
				System.out.println("_".repeat(40));
				
			}
			
			
			if(trains.size()==0) {
				System.out.println("Trains Not Found");
			}
			else {
				
				// Getting train number from user
				int trainNumber;
				while(true) {
					System.out.print("Enter the train number which you want to book :");
					trainNumber = sc.nextInt() ;
					if(trainCoaches.keySet().contains(trainNumber)) {
						break;
					}
					else {
						System.out.println("Enter valid Train number");
					}
				}
				
				// Getting coach from user
				String coach;
				int availSeats;
				while(true) {
					System.out.print("Enter the coach :");
					coach = sc.next();
					if(trainCoaches.get(trainNumber).contains(coach)) {
						availSeats = tmd.getNoOfAvailableSeats(trainNumber, doj, coach);
						if(availSeats==0) {
							System.out.println("Seats in the selected coach got Completed.");
							continue;
						}
						break;
					}
					else {
						System.out.println("Enter valid Coach");
					}
				}
				
				// Getting No. of Tickets from user
				System.out.print("Enter the number of tickets :");
				int noOfSeats;
				while(true) {
					noOfSeats = sc.nextInt();
					if(noOfSeats==0) {
						break;
					}
					if(noOfSeats>availSeats) {
						System.out.println("Insufficient Seats. Only "+ availSeats + " seats available.");
						System.out.print("Enter the number of tickets / Enter 0 to exit:");
					}
					else {
						break;
					}
				}
				
				if(noOfSeats > 0) {
					sc.nextLine();
					String names[] = new String[noOfSeats];
					for(int z=1; z<=noOfSeats; z++) {
						System.out.print("Enter the name of Passenger "+z+" : ");	
						String name = sc.nextLine();
						names[z-1] = name;
					}
					
					tmd.executeNewBooking(userId, trainNumber, doj, coach, noOfSeats, names);
					System.out.println("Booking Successfull");
				}
				
			}
		}
		catch(SQLException e) {
			System.out.println("Booking Failed.");
		}
	}
	
	public List<BookingDetails> showMyBookings(String userId) {
		// List to store all booking details
		List<BookingDetails> myBookings = new ArrayList<>();
		
		try {
			myBookings = tmd.executeShowMyBookings(userId);
		}
		catch(SQLException e) {
			System.out.println("Could not fetch details.");
		}
				
		System.out.println("_".repeat(80));
		if(myBookings.size()!=0) {
			//Displaying all booking details
			for(BookingDetails detail :myBookings) {
				System.out.println("  PNR   : " + detail.booking_id);
				System.out.printf("  Train Number  : %7d        DOJ          : %s\n", detail.trainNumber, detail.doj);
				System.out.printf("  Coach         : %4.5s          No. Of Seats  :%3d\n\n", detail.coach, detail.noOfSeats);
				System.out.println("     "+"-".repeat(35));
				System.out.printf("       %-20s%-10s \n", "Passenger Name", "Seat Number");
				
				System.out.println("     "+"-".repeat(35));
				for(Map.Entry<Integer, String> passenger : detail.passengers.entrySet()) {
					System.out.printf("          %-20s%-10d \n", passenger.getValue(), passenger.getKey());
				}
				System.out.println("     "+"-".repeat(35));
				System.out.printf(" %15s : %.2f\n","TOTAL FARE",detail.totalFare);
				System.out.println("_".repeat(80));
			}
		}
		else {
			System.out.println("You have not booked any train.");
			System.out.println("_".repeat(80));
		}
		return myBookings;
	}

	public void cancelTicket(String userId) {
		
		// Gets a List of Booking Details of given user ID.
		List<BookingDetails> myBookings = showMyBookings(userId);
		
		// Storing all the available booking ID's in a List
		List<Integer> bookingIds = new ArrayList<>();
		for(BookingDetails detail : myBookings) {
			bookingIds.add(detail.booking_id);
		}
		
		if(myBookings.size()!=0) {
			// Getting the PNR(Booking Id) for which user wants to cancel booking
			int bId;
			while(true) {
				System.out.print("Enter the PNR for which you want to cancel the ticket : ");
				bId = sc.nextInt();
				
				// Checking whether the entered Booking ID is Available or not
				if(bookingIds.contains(bId)) {
					break;
				}
				else {
					System.out.println("Enter valid PNR.");
				}
			}
			
			try {
				// Initiates Canceling a Ticket
				tmd.executeCancelTicket(bId);
				System.out.println("Cancellation Successfull.");
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Cancellation Failed.");
			}
		}
	}
}
