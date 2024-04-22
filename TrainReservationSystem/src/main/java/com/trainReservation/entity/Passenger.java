package com.trainReservation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int passengerId;

	@ManyToOne
	@JoinColumn(name = "booking_id")
	BookingDetails bookingDetails;

	@Column(name = "passenger_name")
	private String passengerName;

	@Column(name = "seat_number")
	private int seatNumber;

}
