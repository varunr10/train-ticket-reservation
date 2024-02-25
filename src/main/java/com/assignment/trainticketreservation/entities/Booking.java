package com.assignment.trainticketreservation.entities;

import java.util.UUID;

import com.assignment.trainticketreservation.enums.SelectedSection;

public class Booking {

	String receiptId = UUID.randomUUID().toString();
	Train train;
	User user;
	double PricePaid;
	Seat seat;
	SelectedSection selectedSection;
	
	public Booking(Train train, User user, double pricePaid, Seat seat, SelectedSection section){
		this.train = train;
		this.user = user;
		this.PricePaid = pricePaid;
		this.seat = seat; 
		this.selectedSection = section;
	}
	
	public Seat getSeat() {
		return this.seat;
	}
	
	//To support Seat modification later for AC Section
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	
	public SelectedSection getSection() {
		return this.selectedSection;
	}
	
	public String getReceiptId() {
		return this.receiptId;
	}
	
	public String getUsername() {
		return this.user.firstName + " " + this.user.lastName;
	}
	
	public String getUserEmail() {
		return this.user.email;
	}
	
	public int getSeatNumber() {
		return this.seat.getSeatNumber();
	}
}
