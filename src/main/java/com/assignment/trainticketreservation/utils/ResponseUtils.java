package com.assignment.trainticketreservation.utils;

import java.util.ArrayList;
import java.util.List;

import com.assignment.trainticketreservation.entities.Booking;
import com.assignment.trainticketreservation.models.BookingResponse;
import com.assignment.trainticketreservation.models.SeatsResponse;

public class ResponseUtils {

	public static BookingResponse createBookingResponse(Booking booking) {
		return new BookingResponse(booking.getReceiptId(), booking.getSeat().getSeatNumber(), booking.getSeat().getSeatCategory(), booking.getSection(), booking.getUsername());
	}
	
	public static List<SeatsResponse> createSeatsResponse(List<Booking> bookings) {
		List<SeatsResponse> bookingsListResponse = new ArrayList<>();
		for(Booking booking : bookings) {
			bookingsListResponse.add(new SeatsResponse(booking.getSeat().getSeatNumber(), booking.getUsername()));
		}
		return bookingsListResponse;
	}
	
}
