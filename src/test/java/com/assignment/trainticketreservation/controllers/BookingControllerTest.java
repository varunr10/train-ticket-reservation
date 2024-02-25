package com.assignment.trainticketreservation.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.assignment.trainticketreservation.entities.User;
import com.assignment.trainticketreservation.enums.Destination;
import com.assignment.trainticketreservation.models.BookingResponse;
import com.assignment.trainticketreservation.models.CreateBookingRequest;
import com.assignment.trainticketreservation.models.SeatsResponse;

import java.util.List;

import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class BookingControllerTest {

	@InjectMocks
	BookingController controller;

	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		controller = new BookingController();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateBooking() {
		CreateBookingRequest request = new CreateBookingRequest();
		User user = new User();
		user.setEmail("varun123@gmail.com");
		user.setFirstName("Varun");
		user.setLastName("Ramachandran");
		request.setFrom(Destination.LONDON);
		request.setTo(Destination.PARIS);
		request.setUser(user);
		request.setPricePaid(6.00);

		BookingResponse response = controller.createBooking(request);
		
		Assert.assertNotNull(response.getReceiptId());
	}
	
	
	@Test
	public void testViewBookingByReceiptId() {
		
		BookingResponse response = controller.viewBookingByReceipt("ABCDEG-1234511-DEFACVGH-12345");
		
		Assert.assertNotNull(response.getSeatNumber());
	}
	
	@Test
	public void testViewAllTrainBookingBySection() {
		List<SeatsResponse> responses = controller.viewAllBookingsOfTrain(11111, "AC");
		Assert.assertTrue(!responses.isEmpty());
	}
	
	@Test
	public void testDeleteTrainBookingByUserEmail() {
		controller.deleteBookingByUserEmail(11111, "varun123@gmail.com");
	}
	
	@Test
	public void testModifyUserSeat() {
		controller.modifyUserSeat(11111, "varun123@gmail.com", 5);
	}
}
