package com.assignment.trainticketreservation.models;

import com.assignment.trainticketreservation.enums.SeatCategory;
import com.assignment.trainticketreservation.enums.SelectedSection;

public class BookingResponse {

	String receiptId;
	int seatNumber;
	SeatCategory seatCategory;
	SelectedSection section;
	String username;

	public BookingResponse(String receiptId, int seatNumber, SeatCategory seatCategory, SelectedSection selectedSection, String username) {
		super();
		this.receiptId = receiptId;
		this.seatNumber = seatNumber;
		this.seatCategory = seatCategory;
		this.section = selectedSection;
		this.username = username;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public int getSeatNumber() {
		return seatNumber;
	}
	public SeatCategory getSeatCategory() {
		return seatCategory;
	}
	public SelectedSection getSection() {
		return section;
	}

	public String getUsername() {
		return this.username;
	}
}
