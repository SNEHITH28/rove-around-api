package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.payloads.TripDto;

public interface TripService {
	
	TripDto createTrip(TripDto trip);
	TripDto updateTrip(TripDto trip, Integer tripId);
	TripDto getTrip(Integer tripId);
	List<TripDto> getAllTrips();
	void deleteTrip(Integer tripId);

}
