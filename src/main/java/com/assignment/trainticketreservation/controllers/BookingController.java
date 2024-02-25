package com.assignment.trainticketreservation.controllers;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.trainticketreservation.entities.Booking;
import com.assignment.trainticketreservation.models.SeatsResponse;
import com.assignment.trainticketreservation.models.BookingResponse;
import com.assignment.trainticketreservation.models.CreateBookingRequest;
import com.assignment.trainticketreservation.services.BookingService;
import com.assignment.trainticketreservation.strategies.ACBookingStrategy;
import com.assignment.trainticketreservation.strategies.GeneralBookingStrategy;
import com.assignment.trainticketreservation.utils.ResponseUtils;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@PostMapping("/create")
	public BookingResponse createBooking(@RequestBody CreateBookingRequest bookingRequest){

		Booking booking;
		if(bookingRequest.getPricePaid() > 5.00) {
			booking = bookingService.createBooking(bookingRequest.getUser(), bookingRequest.getFrom(), bookingRequest.getTo(),
					bookingRequest.getPricePaid(), new ACBookingStrategy());
		} else {
			booking = bookingService.createBooking(bookingRequest.getUser(), bookingRequest.getFrom(), bookingRequest.getTo(),
					bookingRequest.getPricePaid(), new GeneralBookingStrategy());
		}

		return ResponseUtils.createBookingResponse(booking);
	}

	@GetMapping("/view/{receiptId}")
	public BookingResponse viewBookingByReceipt(@PathVariable("receiptId") @NotEmpty String receiptId){

		Booking booking = bookingService.findBookingByReceiptId(receiptId);
		return ResponseUtils.createBookingResponse(booking);
	}

	@GetMapping("/all/{trainNumber}/section/{section}")
	public List<SeatsResponse> viewAllBookingsOfTrain(@PathVariable("trainNumber") @NotEmpty int trainNumber,
			@PathVariable("section") String section) {

		List<Booking> bookings = bookingService.viewAllBookingOfTrainBySection(trainNumber, section);
		return ResponseUtils.createSeatsResponse(bookings);
	}

	@DeleteMapping("delete/{trainNumber}/email/{email}")
	public ResponseEntity<String> deleteBookingByUserEmail(@PathVariable("trainNumber") @NotEmpty int trainNumber,
			@PathVariable("email") @Email String email){
		bookingService.deleteBookingByUserEmail(trainNumber, email);
		return ResponseEntity.ok("Deleted ticket booking");
	}

	@PutMapping("update/{trainNumber}/email/{email}/newseat/{newseat}")
	public ResponseEntity<String> modifyUserSeat(@PathVariable("trainNumber") @NotEmpty int trainNumber,
			@PathVariable("email") @Email String email, @PathVariable("newseat") int newseat){
		bookingService.modifyUserSeat(trainNumber, email, newseat);
		return ResponseEntity.ok("Modified Seat Number for user");
	}
}
