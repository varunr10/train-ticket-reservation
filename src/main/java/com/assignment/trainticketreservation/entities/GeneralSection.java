package com.assignment.trainticketreservation.entities;

import java.util.ArrayList;

import com.assignment.trainticketreservation.enums.SeatCategory;

public class GeneralSection extends Section {
	
	public GeneralSection(){
		this.seats = new ArrayList<>();
		createAllSeats();
	}
	
	@Override
	void createAllSeats(){
		for(int i = 1; i <= 100; i++) {
			SeatCategory category = createSeatCategory(i);
			seats.add(new Seat(i, category));
		}
	}

	@Override
	SeatCategory createSeatCategory(int seatNumber) {
		int modulus = seatNumber % 5;
		
		switch(modulus) {
		case 1:
			return SeatCategory.LOWER;
		case 2:
			return SeatCategory.MIDDLE;
		case 3:
			return SeatCategory.UPPER;
		case 4:
			return SeatCategory.SIDE_LOWER;
		case 0:
			return SeatCategory.SIDE_UPPER;
		default:
			return SeatCategory.LOWER;
		}
	}

	@Override
	public Seat getSeat(int seatNumber) {
		// Do nothing as General Section doesn't allow seat modification 
		return null;
	}
}
