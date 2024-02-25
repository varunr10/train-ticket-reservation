package com.assignment.trainticketreservation.entities;

import com.assignment.trainticketreservation.enums.SeatCategory;

public class Seat {

	int seatNumber;
	SeatCategory category;
	boolean isBooked = false;
	
	public Seat(int seatNumber, SeatCategory category) {
		super();
		this.seatNumber = seatNumber;
		this.category = category;
	}
	
	public void bookSeat() {
		this.isBooked = true;
	}
	
	public void releaseSeat() {
		this.isBooked = false;
	}
	
	public int getSeatNumber() {
		return this.seatNumber;
	}
	
	public SeatCategory getSeatCategory() {
		return this.category;
	}
}	
