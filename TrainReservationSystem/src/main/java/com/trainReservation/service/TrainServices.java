package com.trainReservation.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainReservation.entity.BookingDetails;
import com.trainReservation.entity.Passenger;
import com.trainReservation.entity.Train;
import com.trainReservation.entity.TrainAvailability;
import com.trainReservation.entity.User;
import com.trainReservation.repository.BookingDetailsRepository;
import com.trainReservation.repository.PassengerRepository;
import com.trainReservation.repository.TrainAvailabilityRepository;
import com.trainReservation.repository.TrainRepository;
import com.trainReservation.repository.UserRepository;

@Service
public class TrainServices {
	public final UserRepository userRepo;
	public final TrainRepository trainRepo;
	public final TrainAvailabilityRepository availabilityRepo;
	public final BookingDetailsRepository bookingDetailsRepo;
	public final PassengerRepository passengerRepo;
	
	@Autowired
	public TrainServices(UserRepository userRepo, 
						TrainRepository trainRepo,
						TrainAvailabilityRepository availabilityRepo, 
						BookingDetailsRepository bookingDetailsRepo, 
						PassengerRepository passengerRepo){
		this.userRepo = userRepo;
		this.trainRepo = trainRepo;
		this.availabilityRepo = availabilityRepo;
		this.bookingDetailsRepo = bookingDetailsRepo;
		this.passengerRepo = passengerRepo;
	}

	

	// Make a Reservation
	public BookingDetails makeReservation(BookingDetails bookingDetail) {
		
		

		// select name, salary from employee e join salary s on e.emp_id = s.emp_id;
		// select department, count(department) from employee group by department;)
		
		for (Passenger p : bookingDetail.getPassengers()) {
			p.setBookingDetails(bookingDetail);
		}

		String coach = bookingDetail.getCoach();
		int noOfSeats = bookingDetail.getNoOfSeats();
		int trainNumber = bookingDetail.getTrain().getTrainNumber();
		Date doj = bookingDetail.getDOJ();

		// Assigning seat numbers for Passengers
		int availableSeatsCount = getAvailableSeatsCount(trainNumber, doj, coach);

		List<Integer> ReservedSeatNumbers = passengerRepo.getBookedSeatNumbers(trainNumber, doj.toString(), coach);

		int totalSeatsCount = availableSeatsCount + ReservedSeatNumbers.size();
		int foundSeats = 0;
		for (int seatNumber = 1; seatNumber <= totalSeatsCount; seatNumber++) {
			if (!ReservedSeatNumbers.contains(seatNumber)) {
				bookingDetail.getPassengers().get(foundSeats).setSeatNumber(seatNumber);
				foundSeats += 1;
			}
			if (noOfSeats == foundSeats) {
				break;
			}
		}

		// Add to Booking Details and Passengers
		bookingDetailsRepo.save(bookingDetail);

		// Reduce number of seats
		switch (coach) {
		case "sl":
			availabilityRepo.reduceSLSeats(noOfSeats, trainNumber, doj);
			break;
		case "ac1":
			availabilityRepo.reduceAC1Seats(noOfSeats, trainNumber, doj);
			break;
		case "ac2":
			availabilityRepo.reduceAC2Seats(noOfSeats, trainNumber, doj);
			break;
		case "ac3":
			availabilityRepo.reduceAC3Seats(noOfSeats, trainNumber, doj);
			break;
		}
		return bookingDetail;
	}

	// Get all Bookings of a User
	public List<BookingDetails> getAllBookingDetails(String userId) {
		return bookingDetailsRepo.findByUserUserId(userId);
	}
	
	// Cancel a Reservation by PNR(Booking ID)
	public void cancelReservation(int bookingId, String userId) {
		List<BookingDetails> bookingDetails = bookingDetailsRepo.findByBookingIdAndUserUserId(bookingId, userId);
		if(bookingDetails.size()>0) {
			BookingDetails bookingDetail = bookingDetails.get(0);
			int trainNumber = bookingDetail.getTrain().getTrainNumber();
			Date doj = bookingDetail.getDOJ();
			int noOfSeats = bookingDetail.getNoOfSeats();
			String coach = bookingDetail.getCoach();
			
			// Deleting the booking detail 
			bookingDetailsRepo.deleteById(bookingId);
			
			// Increasing the Seats Count
			switch (coach) {
			case "sl":
				availabilityRepo.increaseSLSeats(noOfSeats, trainNumber, doj);
				break;
			case "ac1":
				availabilityRepo.increaseAC1Seats(noOfSeats, trainNumber, doj);
				break;
			case "ac2":
				availabilityRepo.increaseAC2Seats(noOfSeats, trainNumber, doj);
				break;
			case "ac3":
				availabilityRepo.increaseAC3Seats(noOfSeats, trainNumber, doj);
				break;
			}
		}
		
		
	}

	// Get available trains for given Source, Destination and DOJ
	public List<TrainAvailability> getAvailableTrains(String source, String destination, Date doj) {
		return availabilityRepo.findByTrainSourceAndDestinationAndDOJ(source, destination, doj);
	}

	// Get available Seats Count for a particular coach of a train on given DOJ
	public int getAvailableSeatsCount(int trainNumber, Date doj, String coach) {
		System.out.println(doj);
		System.out.println(coach);
		TrainAvailability availability = availabilityRepo.findByTrainNumberAndDOJ(trainNumber, doj).get(0);
		return availability.getSeatsCountByCoach(coach);
	}

	// Get Ticket Cost for a given coach of particular train
	public float getTicketCost(int trainNumber, String coach) {
		Train train = trainRepo.findById(trainNumber).get();
		switch (coach) {
		case "sl": {
			return train.getSlPrice();
		}
		case "ac1": {
			return train.getAc1Price();
		}
		case "ac2": {
			return train.getAc2Price();
		}
		case "ac3": {
			return train.getAc3Price();
		}
		default:
			return 0;
		}
	}

	public Train getTrainDetails(int trainNumber) {
		Train train = trainRepo.findById(trainNumber).get();
		return train;
	}

	public User findUser(String userId) {
		return userRepo.findById(userId).get();
	}

	public Train findTrain(int train) {
		return trainRepo.findById(train).get();
	}
	
	public Set<String> getStations(){
		Set<String> stations = new HashSet<>();
		List<Train> trains = trainRepo.findAll();
		for(Train train: trains) {
			stations.add(train.getSource());
			stations.add(train.getDestination());
		}
		return stations;
	}

	public BookingDetails getBookingDetailByPnr(int id) {
		BookingDetails booking;
		try {
			booking = bookingDetailsRepo.findByBookingId(id).get(0);
		}
		catch(Exception e) {
			booking = null;
		}
		
		return booking;
	}
}
