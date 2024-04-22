package com.trainReservation.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class TrainAvailabilityKey {
	
    @ManyToOne
    @JoinColumn(name = "train_number")
    private Train train;

    @Column(name = "DOJ")
    private Date DOJ;

	@Override
	public String toString() {
		return "TrainAvailabilityKey [train=" + train + ", DOJ=" + DOJ + "]";
	}

}
