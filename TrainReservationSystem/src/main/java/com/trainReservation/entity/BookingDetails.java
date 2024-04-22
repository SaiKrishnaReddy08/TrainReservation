package com.trainReservation.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity
@Data
public class BookingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "train_number")
    private Train train;
    
    @Column(name = "DOJ")
    private Date DOJ;
    
    @Column(name = "coach")
    private String coach;
    
    @Column(name = "no_of_seats")
    private int noOfSeats;
    
    @Column(name = "total_fare")
    private float totalFare;

    @OneToMany(mappedBy = "bookingDetails", cascade=CascadeType.ALL)
    private List<Passenger> passengers;

   
    
	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", user=" + user + ", train=" + train + ", DOJ=" + DOJ
				+ ", coach=" + coach + ", noOfSeats=" + noOfSeats + ", totalFare=" + totalFare + ", passengers="
				+ passengers + "]";
	}
    
}




















