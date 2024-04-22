package com.trainReservation.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trainReservation.entity.TrainAvailability;
import com.trainReservation.entity.TrainAvailabilityKey;

import jakarta.transaction.Transactional;

public interface TrainAvailabilityRepository extends JpaRepository<TrainAvailability, TrainAvailabilityKey>{
	
	// Find Train Availability by Train Number and DOJ
	@Query("from TrainAvailability where availabilityKey.train.trainNumber = :trainNumber and availabilityKey.DOJ = :DOJ")
	List<TrainAvailability> findByTrainNumberAndDOJ( @Param("trainNumber") int trainNumber, @Param("DOJ") Date doj);
	
	// Finding All Available Trains
	@Query("from TrainAvailability where availabilityKey.train.source = :source and  availabilityKey.train.destination = :destination and availabilityKey.DOJ = :DOJ")
	List<TrainAvailability> findByTrainSourceAndDestinationAndDOJ(@Param("source") String source, @Param("destination") String destination, @Param("DOJ") Date doj);
	
	@Modifying
	@Transactional
	@Query("UPDATE TrainAvailability SET slSeats = slSeats - :noOfSeats where availabilityKey.train.trainNumber = :trainNumber and availabilityKey.DOJ = :DOJ")
	void reduceSLSeats(@Param("noOfSeats") int noOfSeats, @Param("trainNumber") int trainNumber, @Param("DOJ") Date doj);
	
	@Modifying
	@Transactional
	@Query("UPDATE TrainAvailability SET ac1Seats = ac2Seats - :noOfSeats where availabilityKey.train.trainNumber = :trainNumber and availabilityKey.DOJ = :DOJ")
	void reduceAC1Seats(@Param("noOfSeats") int noOfSeats, @Param("trainNumber") int trainNumber, @Param("DOJ") Date doj);
	
	@Modifying
	@Transactional
	@Query("UPDATE TrainAvailability SET ac2Seats = ac2Seats - :noOfSeats where availabilityKey.train.trainNumber = :trainNumber and availabilityKey.DOJ = :DOJ")
	void reduceAC2Seats(@Param("noOfSeats") int noOfSeats, @Param("trainNumber") int trainNumber, @Param("DOJ") Date doj);
	
	@Modifying
	@Transactional
	@Query("UPDATE TrainAvailability SET ac3Seats = ac3Seats - :noOfSeats where availabilityKey.train.trainNumber = :trainNumber and availabilityKey.DOJ = :DOJ")
	void reduceAC3Seats(@Param("noOfSeats") int noOfSeats, @Param("trainNumber") int trainNumber, @Param("DOJ") Date doj);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE TrainAvailability SET slSeats = slSeats + :noOfSeats where availabilityKey.train.trainNumber = :trainNumber and availabilityKey.DOJ = :DOJ")
	void increaseSLSeats(@Param("noOfSeats") int noOfSeats, @Param("trainNumber") int trainNumber, @Param("DOJ") Date doj);
	
	@Modifying
	@Transactional
	@Query("UPDATE TrainAvailability SET ac1Seats = ac2Seats + :noOfSeats where availabilityKey.train.trainNumber = :trainNumber and availabilityKey.DOJ = :DOJ")
	void increaseAC1Seats(@Param("noOfSeats") int noOfSeats, @Param("trainNumber") int trainNumber, @Param("DOJ") Date doj);
	
	@Modifying
	@Transactional
	@Query("UPDATE TrainAvailability SET ac2Seats = ac2Seats + :noOfSeats where availabilityKey.train.trainNumber = :trainNumber and availabilityKey.DOJ = :DOJ")
	void increaseAC2Seats(@Param("noOfSeats") int noOfSeats, @Param("trainNumber") int trainNumber, @Param("DOJ") Date doj);
	
	@Modifying
	@Transactional
	@Query("UPDATE TrainAvailability SET ac3Seats = ac3Seats + :noOfSeats where availabilityKey.train.trainNumber = :trainNumber and availabilityKey.DOJ = :DOJ")
	void increaseAC3Seats(@Param("noOfSeats") int noOfSeats, @Param("trainNumber") int trainNumber, @Param("DOJ") Date doj);
	
}
