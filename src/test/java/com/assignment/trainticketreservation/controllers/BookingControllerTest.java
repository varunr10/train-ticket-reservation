package com.assignment.trainticketreservation.controllers;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assignment.trainticketreservation.entities.User;
import com.assignment.trainticketreservation.enums.Destination;
import com.assignment.trainticketreservation.enums.SelectedSection;
import com.assignment.trainticketreservation.models.BookingResponse;
import com.assignment.trainticketreservation.models.CreateBookingRequest;
import com.assignment.trainticketreservation.models.SeatsResponse;
import com.assignment.trainticketreservation.services.BookingService;
import com.assignment.trainticketreservation.services.TrainFindingService;
import com.assignment.trainticketreservation.services.TrainService;

public class BookingControllerTest {

	BookingController controller;
	BookingService bookingService;
	
	@BeforeEach
	public void init() {
		TrainService trainService = new TrainService();
		trainService.init();
		bookingService = new BookingService(new TrainFindingService(), trainService);
		bookingService.init();
		controller = new BookingController(bookingService);
	}

	@Test
	public void testCreateBooking() {
		CreateBookingRequest request = initBookingRequest();

		BookingResponse response = controller.createBooking(request);
		
		Assert.assertNotNull(response.getReceiptId());
		Assert.assertNotNull(response.getSeatNumber());
		Assert.assertEquals(response.getSection(), SelectedSection.AC);
	}

	private CreateBookingRequest initBookingRequest() {
		CreateBookingRequest request = new CreateBookingRequest();
		User user = new User();
		user.setEmail("varun123@gmail.com");
		user.setFirstName("Varun");
		user.setLastName("Ramachandran");
		request.setFrom(Destination.LONDON);
		request.setTo(Destination.PARIS);
		request.setUser(user);
		request.setPricePaid(6.00);
		return request;
	}
	
	
	@Test
	public void testViewBookingByReceiptId() {
		
		//Create a booking
		BookingResponse firstBooking = controller.createBooking(initBookingRequest());
		
		BookingResponse response = controller.viewBookingByReceipt(firstBooking.getReceiptId());
		
		Assert.assertEquals(response.getSeatNumber(), firstBooking.getSeatNumber());
		Assert.assertEquals(response.getUsername(), firstBooking.getUsername());
	}
	
	@Test
	public void testViewAllTrainBookingBySection() {
		testCreateBooking();
		
		List<SeatsResponse> responses = controller.viewAllBookingsOfTrain(11111, "AC");
		Assert.assertTrue(!responses.isEmpty());
	}
	
	@Test
	public void testDeleteTrainBookingByUserEmail() {
		
		testCreateBooking();
		
		controller.deleteBookingByUserEmail(11111, "varun123@gmail.com");
		
		List<SeatsResponse> responses = controller.viewAllBookingsOfTrain(11111, "AC");
		Assert.assertTrue(responses.isEmpty());
	}
	
	@Test
	public void testModifyUserSeat() {
		//Create a booking
		BookingResponse response = controller.createBooking(initBookingRequest());
		
		controller.modifyUserSeat(11111, "varun123@gmail.com", 5);
		
		BookingResponse modifiedBooking = controller.viewBookingByReceipt(response.getReceiptId());
		
		Assert.assertEquals(modifiedBooking.getSeatNumber(), 5);
	}
}
