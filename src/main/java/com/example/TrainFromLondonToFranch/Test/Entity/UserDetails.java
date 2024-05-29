package com.example.TrainFromLondonToFranch.Test.Entity;

import org.hibernate.boot.model.source.internal.hbm.RelationalObjectBinder.ColumnNamingDelegate;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "emailId"}))

public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column(unique = true)
	private String emailId;
	
	public UserDetails(int userId, String firstName, String lastName, String emailId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}
	public UserDetails() {
		super();
	}
	public int getUserId() {
		return userId;
	}
	public void setUderId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	

}
