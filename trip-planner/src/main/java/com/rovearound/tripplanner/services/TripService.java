package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.models.TripDetails;
import com.rovearound.tripplanner.payloads.TripDto;

public interface TripService {
	
	TripDto createTrip(TripDto trip);
	TripDto updateTrip(TripDto trip, Integer tripId);
	TripDto getTrip(Integer tripId);
	TripDetails getTripByTripCode(String tripCode);
	TripDto getTripByTripCodeForTraveler(String tripCode);
	List<TripDto> getAllTrips();
	void deleteTrip(Integer tripId);

}
