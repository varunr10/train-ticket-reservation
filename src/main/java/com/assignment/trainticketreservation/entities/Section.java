package com.assignment.trainticketreservation.entities;

import java.util.List;

import com.assignment.trainticketreservation.enums.SeatCategory;

public abstract class Section {
	List<Seat> seats;
	
	public synchronized Seat getOneAvailableSeat() {
		for(int i = 0; i < this.seats.size(); i++) {
			if(!this.seats.get(i).isBooked) {
				Seat seat = this.seats.get(i);
				seat.bookSeat(); //Marks the seat as booked
				return seat;
			}
		}
		
		return null; // Section is full. All seats booked
	}
	
	abstract void createAllSeats();
	abstract SeatCategory createSeatCategory(int seatNumber);
	public abstract Seat getSeat(int seatNumber);
}
