package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.payloads.ItineraryDto;

public interface ItineraryService {
	ItineraryDto createItinerary(ItineraryDto itinerary);
	ItineraryDto updateItinerary(ItineraryDto itinerary, Integer itineraryId);
	ItineraryDto getItinerary(Integer itineraryId);
	List<ItineraryDto> getAllItinerarys();
	void deleteItinerary(Integer itineraryId);
}
