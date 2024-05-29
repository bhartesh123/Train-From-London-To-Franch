package com.example.TrainFromLondonToFranch.Test.Entity;

import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class TrainSeatsAvailability {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seatId;
	@ManyToOne
    @JoinColumn(name = "trainId", nullable = false)
    private TrainDetails trainDetails;
	@Column
	private String seats;
	@OneToOne
	@JoinColumn(name = "ticketId")
	private TravelDetails travelDetails;
	@Column
	private Boolean availability;
	
	
	public TrainDetails getTrainDetails() {
		return trainDetails;
	}
	public void setTrainDetails(TrainDetails trainDetails) {
		this.trainDetails = trainDetails;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public Boolean getAvailability() {
		return availability;
	}
	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public TravelDetails getTravelDetails() {
		return travelDetails;
	}
	public void setTravelDetails(TravelDetails travelDetails) {
		this.travelDetails = travelDetails;
	}
	
}
