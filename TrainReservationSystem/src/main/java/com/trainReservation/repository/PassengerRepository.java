package com.trainReservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trainReservation.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
	@Query(value="select seat_number from passenger join booking_details using(booking_id) where train_number = :trainNumber and DOJ =str_to_date(:DOJ,\"%Y-%m-%d\") and coach = :coach", nativeQuery=true)
	List<Integer> getBookedSeatNumbers(@Param("trainNumber") int trainNumber, @Param("DOJ") String doj, @Param("coach") String coach);

}
