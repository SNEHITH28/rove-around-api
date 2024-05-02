package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.payloads.TripNotesDto;

public interface TripNotesService {
	TripNotesDto createTripNotes(TripNotesDto tripNotes);
	TripNotesDto updateTripNotes(TripNotesDto tripNotes, Integer tripNotesId);
	TripNotesDto getTripNotes(Integer tripNotesId);
	List<TripNotesDto> getTripNotesByTripId(Integer tripId);
	List<TripNotesDto> getAllTripNotes();
	void deleteTripNotes(Integer tripNotesId);

}
