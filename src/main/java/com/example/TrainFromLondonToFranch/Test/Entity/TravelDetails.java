package com.example.TrainFromLondonToFranch.Test.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Travel")
public class TravelDetails {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;
	@Column(nullable = false)
	private String from;
	@Column(nullable = false)
	private String to;
	@ManyToOne
	@JoinColumn(name ="userId", nullable = false)
	private UserDetails user;
	@ManyToOne
	@JoinColumn(name ="trainId", nullable = false)
	private TrainDetails trainDetails;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	private String seatNo;

	/*
	 * public TravelDetails() { super(); } public TravelDetails(int id, String from,
	 * String to, UserDetails userId, double price) { super(); ticketId = id;
	 * this.from = from; this.to = to; this.userId = userId.getUserId(); this.price
	 * = price; }
	 */
	public Long getId() {
		return ticketId;
	}
	public void setId(Long id) {
		ticketId = id;
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
	public UserDetails getUser() {
		return user;
	}
	public void setUser(UserDetails user) {
		this.user = user;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long generatedId) {
		this.ticketId = generatedId;
	}
	public TrainDetails getTrainDetails() {
		return trainDetails;
	}
	public void setTrainDetails(TrainDetails trainDetails) {
		this.trainDetails = trainDetails;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	
}
