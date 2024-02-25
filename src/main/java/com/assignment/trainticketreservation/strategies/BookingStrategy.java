package com.assignment.trainticketreservation.strategies;

import com.assignment.trainticketreservation.entities.Booking;
import com.assignment.trainticketreservation.entities.Train;
import com.assignment.trainticketreservation.entities.User;
import com.assignment.trainticketreservation.enums.Destination;

public interface BookingStrategy {

	Booking bookSeat(Train train, Destination from, Destination to, User user, double pricePaid);
}
