package com.trainReservation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "train_availability")
public class TrainAvailability {
	
	@EmbeddedId
	TrainAvailabilityKey availabilityKey;

    @Column(name = "sl_seats", columnDefinition = "default -1")
    private int slSeats;

    @Column(name = "ac1_seats", columnDefinition = "default -1")
    private int ac1Seats;

    @Column(name = "ac2_seats", columnDefinition = "default -1")
    private int ac2Seats;

    @Column(name = "ac3_seats", columnDefinition = "default -1")
    private int ac3Seats;

	public int getSeatsCountByCoach(String coach) {
		switch(coach) {
			case "sl":
				return slSeats;
				
			case "ac1":
				return ac1Seats;
			
			case "ac2":
				return ac2Seats;
				
			case "ac3":
				return ac3Seats;
			
			default:
				return -1;
		}
	}

	@Override
	public String toString() {
		return "TrainAvailability [availabilityKey=" + availabilityKey + ", slSeats=" + slSeats + ", ac1Seats="
				+ ac1Seats + ", ac2Seats=" + ac2Seats + ", ac3Seats=" + ac3Seats + "]";
	}
   
}



