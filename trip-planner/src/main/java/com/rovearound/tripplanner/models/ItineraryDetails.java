package com.rovearound.tripplanner.models;

import java.util.Date;
import java.util.List;

import com.rovearound.tripplanner.payloads.ItineraryLocationDto;
import com.rovearound.tripplanner.payloads.ItineraryNotesDto;

public class ItineraryDetails {
	private int tripId;
	private int itineraryId;
	private Date date;
    private boolean status;
    private List<ItineraryLocationDto> itineraryLocations;
    private List<ItineraryNotesDto> itineraryNotes;
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public int getItineraryId() {
		return itineraryId;
	}
	public void setItineraryId(int itineraryId) {
		this.itineraryId = itineraryId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<ItineraryLocationDto> getItineraryLocations() {
		return itineraryLocations;
	}
	public void setItineraryLocations(List<ItineraryLocationDto> itineraryLocations) {
		this.itineraryLocations = itineraryLocations;
	}
	public List<ItineraryNotesDto> getItineraryNotes() {
		return itineraryNotes;
	}
	public void setItineraryNotes(List<ItineraryNotesDto> itineraryNotes) {
		this.itineraryNotes = itineraryNotes;
	}
}

