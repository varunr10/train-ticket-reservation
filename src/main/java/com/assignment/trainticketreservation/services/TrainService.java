package com.assignment.trainticketreservation.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.trainticketreservation.entities.ACSection;
import com.assignment.trainticketreservation.entities.GeneralSection;
import com.assignment.trainticketreservation.entities.Section;
import com.assignment.trainticketreservation.entities.Train;
import com.assignment.trainticketreservation.enums.Destination;

import jakarta.annotation.PostConstruct;

@Service
public class TrainService {

	List<Train> trainsList;
	
	@PostConstruct
	public void init() {
		trainsList = new ArrayList<>();
		createTrains();
	}
	
	private void createTrains() {
		Section acSection1 = new ACSection();
		Section generalSection1 = new GeneralSection();
		Train fromLondonToParis = new Train(11111, Destination.LONDON, Destination.PARIS, acSection1 , generalSection1);
		trainsList.add(fromLondonToParis);
		
		Section acSection2 = new ACSection();
		Section generalSection2 = new GeneralSection();
		Train fromLondonToMunich = new Train(12345, Destination.LONDON, Destination.MUNICH, acSection2 , generalSection2);
		trainsList.add(fromLondonToMunich);
		
		Section acSection3 = new ACSection();
		Section generalSection3 = new GeneralSection();
		Train fromMunichToParis = new Train(55555, Destination.MUNICH, Destination.PARIS, acSection3 , generalSection3);
		trainsList.add(fromMunichToParis);
		
		Section acSection4 = new ACSection();
		Section generalSection4 = new GeneralSection();
		Train fromPragueToParis = new Train(69690, Destination.PRAGUE, Destination.PARIS, acSection4 , generalSection4);
		trainsList.add(fromPragueToParis);
	}

	void addTrain(Train train) {
		trainsList.add(train);
	}
	
	public List<Train> getTrainsList(){
		return this.trainsList;
	}
	
	public Train getTrainByNumber(int trainNumber) {
		return this.trainsList.stream().filter(train -> train.getTrainNumber() == trainNumber).toList().get(0);
	}
}
