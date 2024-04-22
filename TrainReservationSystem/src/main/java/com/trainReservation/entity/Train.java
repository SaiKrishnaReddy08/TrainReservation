package com.trainReservation.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "train")
public class Train {
    @Id
    @Column(name = "train_number")
    private int trainNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "source")
    private String source;

    @Column(name = "destination")
    private String destination;

    @Column(name = "sl_price")
    private Float slPrice;

    @Column(name = "ac1_price")
    private Float ac1Price;

    @Column(name = "ac2_price")
    private Float ac2Price;

    @Column(name = "ac3_price")
    private Float ac3Price;

    @OneToMany(mappedBy = "availabilityKey.train", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<TrainAvailability> availabilities;

	@Override
	public String toString() {
		return "Train [trainNumber=" + trainNumber + ", name=" + name + ", source=" + source + ", destination="
				+ destination + ", slPrice=" + slPrice + ", ac1Price=" + ac1Price + ", ac2Price=" + ac2Price
				+ ", ac3Price=" + ac3Price +  "]";
	}
    
}












