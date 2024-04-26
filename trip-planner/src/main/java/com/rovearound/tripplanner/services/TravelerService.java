package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.payloads.TravelerDto;

public interface TravelerService {
	TravelerDto createTraveler(TravelerDto traveler);
	TravelerDto updateTraveler(TravelerDto traveler, Integer travelerId);
	TravelerDto getTraveler(Integer travelerId);
	List<TravelerDto> getAllTravelers();
	void deleteTraveler(Integer travelerId);
}
