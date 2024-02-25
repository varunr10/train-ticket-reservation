package com.assignment.trainticketreservation.models;

public class SeatsResponse {

	int seatNumber;
	String userName;
	
	public SeatsResponse(int seatNumber, String userName) {
		super();
		this.seatNumber = seatNumber;
		this.userName = userName;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public String getUserName() {
		return userName;
	}
}
