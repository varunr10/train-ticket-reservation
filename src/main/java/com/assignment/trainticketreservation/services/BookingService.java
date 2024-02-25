package com.assignment.trainticketreservation.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.trainticketreservation.entities.Booking;
import com.assignment.trainticketreservation.entities.Seat;
import com.assignment.trainticketreservation.entities.Section;
import com.assignment.trainticketreservation.entities.Train;
import com.assignment.trainticketreservation.entities.User;
import com.assignment.trainticketreservation.enums.Destination;
import com.assignment.trainticketreservation.strategies.BookingStrategy;

import jakarta.annotation.PostConstruct;

@Service
public class BookingService {
	
	@Autowired
	TrainFindingService trainFindingService;
	
	@Autowired
	TrainService trainService;

	List<Booking> bookingsList;
	Map<Train, List<Booking>> bookingsByTrainMap;
	
	@PostConstruct
	public void init(){
		this.bookingsList = new ArrayList<>();
		this.bookingsByTrainMap = new HashMap<>();
	}
	
	public Booking createBooking(User user, Destination from, Destination to, double pricePaid, BookingStrategy bookingStrategy) {
		
		Train selectedTrain = trainFindingService.findTrainByCities(from, to, trainService);
		
		//No trains available matching the Start City and Destination City
		if(selectedTrain == null)
			return null;
		
		Booking currentBooking = bookingStrategy.bookSeat(selectedTrain, from, to, user, pricePaid);
		
		updateBookingListsAfterCreation(selectedTrain, currentBooking);
		
		return currentBooking;
	}
	
	public Booking findBookingByReceiptId(String receiptId) {
		return bookingsList.stream().filter(booking -> booking.getReceiptId().equals(receiptId)).toList().get(0);
	}
	
	public List<Booking> viewAllBookingOfTrainBySection(int trainNumber, String sectionName){
		Train train = trainService.getTrainByNumber(trainNumber);
		
		List<Booking> bookingsInTrain = bookingsByTrainMap.get(train);
		
		return bookingsInTrain.stream().filter(booking -> booking.getSection().toString().equals(sectionName)).toList();
	}
	
	public void deleteBookingByUserEmail(int trainNumber, String email) {
		Train train = trainService.getTrainByNumber(trainNumber);
		List<Booking> bookingsInTrain = bookingsByTrainMap.get(train);
	
		List<Booking> bookingsToDelete = bookingsInTrain.stream().filter(booking -> booking.getUserEmail().equals(email)).toList();
		
		bookingsList.removeAll(bookingsToDelete);
		updateBookingListAfterDeletion(train, bookingsToDelete);
	}

	
	
	public boolean modifyUserSeat(int trainNumber, String email, int newSeatNumber) {
		Train train = trainService.getTrainByNumber(trainNumber);
		List<Booking> bookingsInTrain = bookingsByTrainMap.get(train);
		
		Booking bookingToModify = bookingsInTrain.stream().filter(booking -> booking.getUserEmail().equals(email)).toList().get(0);
		Seat previousSeat =	bookingToModify.getSeat();
		previousSeat.releaseSeat();
		
		//We only allow seat modification for AC Section and not for General Sections
		Section acSection = train.getAcSection();
		Seat newSeat =	acSection.getSeat(newSeatNumber);
		newSeat.bookSeat();
		
		bookingToModify.setSeat(newSeat);
		
		return true;
	}

	private void updateBookingListsAfterCreation(Train selectedTrain, Booking currentBooking) {
		bookingsList.add(currentBooking);
		
		//Get all available user bookings and include the current booking
		List<Booking> trainBookings = bookingsByTrainMap.getOrDefault(selectedTrain, new ArrayList<>());
		trainBookings.add(currentBooking);
		bookingsByTrainMap.put(selectedTrain, trainBookings);
	}
	
	private void updateBookingListAfterDeletion(Train train, List<Booking> bookingsToDelete) {
		List<Booking> bookings = bookingsByTrainMap.get(train);
		bookings.removeAll(bookingsToDelete);
		bookingsToDelete.forEach(booking -> booking.getSeat().releaseSeat());
		bookingsByTrainMap.put(train, bookings);
	}
		
}
