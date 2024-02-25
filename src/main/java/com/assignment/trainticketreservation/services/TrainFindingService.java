package com.assignment.trainticketreservation.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.trainticketreservation.entities.Train;
import com.assignment.trainticketreservation.enums.Destination;

@Service
public class TrainFindingService {

	
	public Train findTrainByCities(Destination fromCity, Destination toCity, TrainService trainService) {
		//FROM - LONDON  TO - PARIS
		
		//LONDON - MUNICH, LONDON - PARIS, LONDON - MUMBAI, MUNICH - PARIS, MUMBAI-PARIS, BERLIN - BENGALURU
		List<Train> trainsList = trainService.getTrainsList();
		
		//LONDON - MUNICH, LONDON - PARIS, LONDON - MUMBAI
		List<Train> matchingFrom = trainsList.stream().filter(train -> train.getFromCity().equals(fromCity)).toList();
		
		//MUNICH - PARIS, LONDON - PARIS, MUMBAI-PARIS
		List<Train> matchingTo = trainsList.stream().filter(train -> train.getToCity().equals(toCity)).toList();
		
		
		for(int i = 0; i < matchingFrom.size() ; i++) {
			if(matchingTo.contains(matchingFrom.get(i))) {
				return matchingFrom.get(i);
			}
		}
		return null;
	}
}
