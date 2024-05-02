package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.payloads.ItineraryNotesDto;

public interface ItineraryNotesService {
	ItineraryNotesDto createItineraryNotes(ItineraryNotesDto itineraryNotes);
	ItineraryNotesDto updateItineraryNotes(ItineraryNotesDto itineraryNotes, Integer itineraryNotesId);
	ItineraryNotesDto getItineraryNotes(Integer itineraryNotesId);
	List<ItineraryNotesDto> getItineraryNotesByItineraryId(Integer itineraryId);
	List<ItineraryNotesDto> getAllItineraryNotes();
	void deleteItineraryNotes(Integer itineraryNotesId);

}
