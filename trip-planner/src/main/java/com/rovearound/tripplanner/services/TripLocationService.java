package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.payloads.TripLocationDto;

public interface TripLocationService {
	TripLocationDto createTripLocation(TripLocationDto tripLocation);
	TripLocationDto updateTripLocation(TripLocationDto tripLocation, Integer tripLocationId);
	TripLocationDto getTripLocation(Integer tripLocationId);
	List<TripLocationDto> getTripLocationsByTripId(Integer tripId);
	List<TripLocationDto> getAllTripLocations();
	void deleteTripLocation(Integer tripLocationId);
}
