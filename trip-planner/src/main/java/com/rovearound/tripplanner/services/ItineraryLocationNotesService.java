package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.payloads.ItineraryLocationNotesDto;

public interface ItineraryLocationNotesService {
	ItineraryLocationNotesDto createItineraryLocationNotes(ItineraryLocationNotesDto itineraryLocationNotes);
	ItineraryLocationNotesDto updateItineraryLocationNotes(ItineraryLocationNotesDto itineraryLocationNotes, Integer itineraryLocationNotesId);
	ItineraryLocationNotesDto getItineraryLocationNotes(Integer itineraryLocationNotesId);
	List<ItineraryLocationNotesDto> getItineraryLocationNotesByItineraryLocationId(Integer itineraryLocationId);
	List<ItineraryLocationNotesDto> getAllItineraryLocationNotes();
	void deleteItineraryLocationNotes(Integer itineraryLocationNotesId);

}