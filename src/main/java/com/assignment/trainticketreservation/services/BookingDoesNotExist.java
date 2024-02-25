package com.assignment.trainticketreservation.services;

public class BookingDoesNotExist extends Exception {

	public BookingDoesNotExist() {
        super();
    }

    public BookingDoesNotExist(String message) {
        super(message);
    }

    public BookingDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingDoesNotExist(Throwable cause) {
        super(cause);
    }
}
