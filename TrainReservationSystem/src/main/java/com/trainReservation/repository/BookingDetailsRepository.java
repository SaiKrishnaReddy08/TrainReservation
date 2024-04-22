package com.trainReservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainReservation.entity.BookingDetails;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer> {

	List<BookingDetails> findByUserUserId(String userId);
	
	List<BookingDetails> findByBookingId(int bookingId);

	List<BookingDetails> findByBookingIdAndUserUserId(int bookingId, String userId);

}