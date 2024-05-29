package com.example.TrainFromLondonToFranch.Test.Entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Component
@Entity
@Table
public class TrainDetails {
	@Id
	private Long trainId;
	@Column
	private String from;
	@Column
	private String to;
	@Column
	private double price;
	@Column
	private int totalSeats;

	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getTrainId() {
		return trainId;
	}
	public void setTrainId(Long trainId) {
		this.trainId = trainId;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	
}
