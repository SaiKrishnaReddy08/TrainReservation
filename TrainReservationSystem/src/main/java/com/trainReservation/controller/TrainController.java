package com.trainReservation.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.trainReservation.entity.BookingDetails;
import com.trainReservation.entity.TrainAvailability;
import com.trainReservation.service.TrainServices;

@SessionAttributes({"userId", "bookingDetail", "isLoggedIn"})
@Controller
public class TrainController {
	
	@Autowired
	public TrainServices trainService;
	
	@GetMapping("")
	public String redirectToHome(ModelMap model) {
		model.put("isLoggedIn", false);
		return "redirect:home";
	}
	
	@GetMapping("home")
	public String home(ModelMap model) {
		boolean status = isLoggedIn(model);
		model.put("loggedIn", status);
		return "home";
	}
	
	@GetMapping("book")
	public String showBookingPage(ModelMap model) {
		model.put("stations", trainService.getStations());
		return "bookingPage";
	}
	

	@PostMapping("trains")    
	public String showTrains(@RequestParam String source, @RequestParam String destination, @RequestParam Date doj, ModelMap model) {
		List<TrainAvailability> availableTrains = trainService.getAvailableTrains(source, destination, doj);
		
		model.put("availableTrains", availableTrains);
		return "availableTrains";
	}
	
	@GetMapping("passenger_details")
	public String showNoOfPassengerPage(@RequestParam int train,@RequestParam String coach, @RequestParam String doj, @ModelAttribute BookingDetails bookingDetail, ModelMap model) {
		String view = "";
		
		if(!isLoggedIn(model)) {
			view = "redirect:login";
		}
		else {
			bookingDetail.setTrain(trainService.findTrain(train));
			bookingDetail.setCoach(coach);
			try {
				Date doj1 = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(doj).getTime());
				bookingDetail.setDOJ(doj1);
			}catch(Exception e) {}
			
			model.put("bookingDetail", bookingDetail);
			view = "NoOfPassengers";
		}
		return view;
	}
	
	@PostMapping("passenger_details")
	public String showPassengerDetailsPage(BookingDetails bookingDetail, ModelMap model) {
		int trainno = bookingDetail.getTrain().getTrainNumber();
		Date doj = bookingDetail.getDOJ();
		String coach =bookingDetail.getCoach();
		
		int availSeats = trainService.getAvailableSeatsCount(trainno, doj, coach);
		if(bookingDetail.getNoOfSeats()> availSeats) {
			model.put("invalid", "Insufficient Seats. Only "+availSeats+" seat(s) available.");
			return "NoOfPassengers";
		}
		if(bookingDetail.getNoOfSeats()==0) {
			model.put("invalid", "Enter Valid number of Seats.");
			return "NoOfPassengers";
		}

		BookingDetails bd=(BookingDetails)model.get("bookingDetail");
		bd.setNoOfSeats(bookingDetail.getNoOfSeats());
		bd.setPassengers(new ArrayList<>());
		
		return "PassengerDetails"; 
	}
	
	@PostMapping("confirm")
	public String showConfirmationPage(BookingDetails bookingDetail, ModelMap model) {
		// Finding single Ticket Cost
		BookingDetails bookingDetailsObj = (BookingDetails)model.get("bookingDetail");
		float ticketCost = trainService.getTicketCost(bookingDetailsObj.getTrain().getTrainNumber(), bookingDetail.getCoach());
		float totalFare = ticketCost * bookingDetail.getNoOfSeats();  // Total Cost
		
		bookingDetailsObj.setTotalFare(totalFare);  
		bookingDetailsObj.setPassengers(bookingDetail.getPassengers());
		bookingDetailsObj.setUser(trainService.findUser(model.get("userId").toString()));
		
		return "confirmationPage";
	}
	
	@PostMapping("BookingStatus")
	public String showBookingStatusPage(ModelMap model) {
		BookingDetails bookingDetail = (BookingDetails) model.get("bookingDetail");
		BookingDetails finalBookingDetail = trainService.makeReservation(bookingDetail);
		model.put("finalBookingDetail", finalBookingDetail);
		bookingDetail = null;
		return "successPage";
	}
	
	@GetMapping("MyBookings")
	public String showMyBookingsPage(ModelMap model) {
		String view = "";
		if(!isLoggedIn(model)) {
			view = "redirect:login";
		}
		else {
			List<BookingDetails> bookings = trainService.getAllBookingDetails(model.get("userId").toString());
			model.put("myBookings",bookings);
			view = "my_bookings_page";
		}
		return view;
	}
	
	@GetMapping("MyBookings/{id}")
	public String showMyBookingDetailsPage(@PathVariable int id, ModelMap model) {
		String view = "";
		if(!isLoggedIn(model)) {
			view = "redirect:login";
		}
		else {
			BookingDetails booking = trainService.getBookingDetailByPnr(id);
			model.put("booking", booking);
			view = "booking_detail_page";
		}
		return view;
	}
	
	@GetMapping("cancellation")
	public String showCancellationPage(ModelMap model) {
		String view = "";
		if(!isLoggedIn(model)) {
			view = "redirect:login";
		}
		else {
			List<BookingDetails> bookings = trainService.getAllBookingDetails(model.get("userId").toString());
			model.put("myBookings",bookings);
			view =  "cancelPage";
		}
		return view;
	}
	
	@GetMapping("cancel")
	public String makeCancellation(@RequestParam("pnr") int pnr, ModelMap model) {
		String view = "";
		if(!isLoggedIn(model)) {
			view = "redirect:login";
		}
		else {
			trainService.cancelReservation(pnr, model.get("userId").toString());
			view =  "redirect:cancellation";
		}
		return view;
		
	}

	private boolean isLoggedIn(ModelMap model) {
		if(model.get("isLoggedIn")!=null && (boolean)model.get("isLoggedIn")) {
			return true;
		}
		else {
			return false;
		}
	}

	
}
