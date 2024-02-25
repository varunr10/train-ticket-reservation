package com.assignment.trainticketreservation.entities;

import com.assignment.trainticketreservation.enums.Destination;
import com.assignment.trainticketreservation.enums.SelectedSection;

public class Train {

	Destination fromCity;
	Destination toCity;
	Section acSection;
	Section generalSection;
	int trainNumber;

	public Train(int trainNumber, Destination fromCity, Destination toCity, Section acSection, Section generalSection) {
		super();
		this.trainNumber = trainNumber;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.acSection = acSection;
		this.generalSection = generalSection;
	}

	public int getTrainNumber() {
		return trainNumber;
	}

	public Destination getFromCity() {
		return fromCity;
	}

	public Destination getToCity() {
		return toCity;
	}

	public Section getAcSection() {
		return acSection;
	}

	public Section getGeneralSection() {
		return generalSection;
	}

	public Section getSection(SelectedSection sectionName) {
		if(SelectedSection.AC.equals(sectionName))
		{
			return this.getAcSection();
		} else {
			return this.getGeneralSection();
		}
	}
}
