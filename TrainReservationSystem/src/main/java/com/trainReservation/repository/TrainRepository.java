package com.trainReservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trainReservation.entity.Train;

public interface TrainRepository extends JpaRepository<Train, Integer>{
	
	@Query(value="select * from train t join train_availability ta using(train_number) where t.source = :source and t.destination = :destination and ta.DOJ = str_to_date(:DOJ,\"%Y-%m-%d\")", nativeQuery=true)
	public List<Train> getsTrainsBySourceAndDestinationAndDOJ(@Param("source") String source, @Param("destination") String destination, @Param("DOJ") String doj);
	
}

