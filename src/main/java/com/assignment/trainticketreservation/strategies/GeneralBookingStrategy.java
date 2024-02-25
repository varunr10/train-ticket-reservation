package com.assignment.trainticketreservation.strategies;

import com.assignment.trainticketreservation.entities.Booking;
import com.assignment.trainticketreservation.entities.Seat;
import com.assignment.trainticketreservation.entities.Section;
import com.assignment.trainticketreservation.entities.Train;
import com.assignment.trainticketreservation.entities.User;
import com.assignment.trainticketreservation.enums.Destination;
import com.assignment.trainticketreservation.enums.SelectedSection;

public class GeneralBookingStrategy implements BookingStrategy{

	@Override
	public Booking bookSeat(Train selectedTrain, Destination from, Destination to, User user, double pricePaid) {
		Section generalSection =  selectedTrain.getGeneralSection();
		
		Seat availableSeat = generalSection.getOneAvailableSeat();
		
		//No seats available in the given selection
		if(availableSeat == null) {
			return null;
		}		
		
		return  new Booking(selectedTrain, user, pricePaid, availableSeat, SelectedSection.GENERAL);
	}
}
