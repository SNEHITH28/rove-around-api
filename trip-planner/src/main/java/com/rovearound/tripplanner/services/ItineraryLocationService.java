package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.payloads.ItineraryLocationDto;

public interface ItineraryLocationService {
	ItineraryLocationDto createItineraryLocation(ItineraryLocationDto itineraryLocation);
	ItineraryLocationDto updateItineraryLocation(ItineraryLocationDto itineraryLocation, Integer itineraryLocationId);
	ItineraryLocationDto getItineraryLocation(Integer itineraryLocationId);
	List<ItineraryLocationDto> getItineraryLocationsByItineraryId(Integer itineraryId);
	List<ItineraryLocationDto> getAllItineraryLocations();
	void deleteItineraryLocation(Integer itineraryLocationId);

}
