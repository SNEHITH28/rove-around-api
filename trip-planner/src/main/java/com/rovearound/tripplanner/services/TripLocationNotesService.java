package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.payloads.TripLocationNotesDto;

public interface TripLocationNotesService {
	TripLocationNotesDto createTripLocationNotes(TripLocationNotesDto tripLocationNotes);
	TripLocationNotesDto updateTripLocationNotes(TripLocationNotesDto tripLocationNotes, Integer tripLocationNotesId);
	TripLocationNotesDto getTripLocationNotes(Integer tripLocationNotesId);
	List<TripLocationNotesDto> getTripLocationNotesByTripLocationId(Integer tripLocationId);
	List<TripLocationNotesDto> getAllTripLocationNotes();
	void deleteTripLocationNotes(Integer tripLocationNotesId);

}
