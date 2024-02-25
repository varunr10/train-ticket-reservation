package com.assignment.trainticketreservation.models;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;

import com.assignment.trainticketreservation.entities.User;
import com.assignment.trainticketreservation.enums.Destination;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBookingRequest {

	Destination from;
	Destination to;
	
	@Valid
	User user;
	
	@DecimalMin(value = "0.01", message = "Price must be above 0.00")
	double pricePaid;

	@JsonProperty("from")
	public Destination getFrom() {
		return from;
	}

	public void setFrom(Destination from) {
		this.from = from;
	}

	@JsonProperty("to")
	public Destination getTo() {
		return to;
	}

	public void setTo(Destination to) {
		this.to = to;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonProperty("pricePaid")
	public double getPricePaid() {
		return pricePaid;
	}

	public void setPricePaid(double pricePaid) {
		this.pricePaid = pricePaid;
	}
	
	
	
}
