package com.assignment.trainticketreservation.entities;

import java.util.ArrayList;

import com.assignment.trainticketreservation.enums.SeatCategory;

public class ACSection extends Section {

	public ACSection(){
		this.seats = new ArrayList<>();
		createAllSeats();
	}

	@Override
	void createAllSeats(){
		for(int i = 1; i <= 200; i++) {
			SeatCategory category = createSeatCategory(i);
			seats.add(new Seat(i, category));
		}
	}

	@Override
	public SeatCategory createSeatCategory(int seatNumber) {
		int modulus = seatNumber % 3;

		switch(modulus) {
		case 1:
			return SeatCategory.LOWER;
		case 2:
			return SeatCategory.MIDDLE;
		case 0:
			return SeatCategory.UPPER;
		default:
			return SeatCategory.LOWER;
		}
	}
	
	@Override
	public Seat getSeat(int seatNumber) {
		return this.seats.get(seatNumber - 1); //Because Arrays elements range from 0 to n-1
	}
}
