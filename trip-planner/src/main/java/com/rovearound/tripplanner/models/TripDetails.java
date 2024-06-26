package com.rovearound.tripplanner.models;

import java.util.Date;
import java.util.List;

import com.rovearound.tripplanner.entities.ItineraryNotes;
import com.rovearound.tripplanner.payloads.BudgetDto;
import com.rovearound.tripplanner.payloads.ExpenseDto;
import com.rovearound.tripplanner.payloads.ItineraryLocationDto;
import com.rovearound.tripplanner.payloads.TripDto;
import com.rovearound.tripplanner.payloads.TripLocationDto;
import com.rovearound.tripplanner.payloads.TripNotesDto;
import com.rovearound.tripplanner.payloads.UserDto;

public class TripDetails {

	TripDto trip;
	List<UserDto> travelers;
	List<TripLocationDto> tripLocations;
	List<ItineraryDetails> itineraries;
	BudgetDto budget;
	List<ExpenseDto> expenses;
	List<TripNotesDto> tripNotes;
	public TripDto getTrip() {
		return trip;
	}
	public void setTrip(TripDto trip) {
		this.trip = trip;
	}
	public List<UserDto> getTravelers() {
		return travelers;
	}
	public void setTravelers(List<UserDto> travelers) {
		this.travelers = travelers;
	}
	public List<TripLocationDto> getTripLocations() {
		return tripLocations;
	}
	public void setTripLocations(List<TripLocationDto> tripLocations) {
		this.tripLocations = tripLocations;
	}
	
	public BudgetDto getBudget() {
		return budget;
	}
	public void setBudget(BudgetDto budget) {
		this.budget = budget;
	}
	public List<ExpenseDto> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<ExpenseDto> expenses) {
		this.expenses = expenses;
	}
	public List<TripNotesDto> getTripNotes() {
		return tripNotes;
	}
	public void setTripNotes(List<TripNotesDto> tripNotes) {
		this.tripNotes = tripNotes;
	}
	public List<ItineraryDetails> getItineraries() {
		return itineraries;
	}
	public void setItineraries(List<ItineraryDetails> itineraries) {
		this.itineraries = itineraries;
	}
}