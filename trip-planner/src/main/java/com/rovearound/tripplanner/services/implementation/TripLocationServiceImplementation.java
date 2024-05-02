package com.rovearound.tripplanner.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.TripLocation;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.payloads.TripLocationDto;
import com.rovearound.tripplanner.payloads.UserDto;
import com.rovearound.tripplanner.repositories.TripLocationRepository;
import com.rovearound.tripplanner.services.TripLocationService;
import com.rovearound.tripplanner.services.UserService;

@Service
public class TripLocationServiceImplementation implements TripLocationService {
	@Autowired
	private TripLocationRepository tripLocationRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TripLocationDto createTripLocation(TripLocationDto tripLocationDto) {
		TripLocation tripLocation = this.dtoToTripLocation(tripLocationDto);
		TripLocation savedTripLocation = this.tripLocationRepository.save(tripLocation);
		return this.tripLocationToDto(savedTripLocation);
	}

	@Override
	public TripLocationDto updateTripLocation(TripLocationDto tripLocationDto, Integer tripLocationId) {
		TripLocation tripLocation = this.tripLocationRepository.findById(tripLocationId)
				.orElseThrow(() -> new ResourceNotFoundException("TripLocation", "Id", tripLocationId));
		
		tripLocation.setId(tripLocationDto.getId());
		tripLocation.setTrip(tripLocationDto.getTrip());
		tripLocation.setUser(tripLocationDto.getUser());
		tripLocation.setStatus(true);

		TripLocation updatedTripLocation = this.tripLocationRepository.save(tripLocation);
		TripLocationDto updatedTripLocationDto = this.tripLocationToDto(updatedTripLocation);
				
		return updatedTripLocationDto;
	}

	@Override
	public TripLocationDto getTripLocation(Integer tripLocationId) {
		TripLocation tripLocation = this.tripLocationRepository.findById(tripLocationId)
				.orElseThrow(() -> new ResourceNotFoundException("TripLocation", "Id", tripLocationId));
		
		return this.tripLocationToDto(tripLocation);
	}

	@Override
	public List<TripLocationDto> getAllTripLocations() {
		List<TripLocation> tripLocations = this.tripLocationRepository.findAll();
		
		List<TripLocationDto> tripLocationDtos = tripLocations.stream().map(tripLocation -> this.tripLocationToDto(tripLocation)).collect(Collectors.toList());
		return tripLocationDtos;
	}

	@Override
	public void deleteTripLocation(Integer tripLocationId) {
		TripLocation tripLocation = this.tripLocationRepository.findById(tripLocationId)
				.orElseThrow(() -> new ResourceNotFoundException("TripLocation", "Id", tripLocationId));
		
		tripLocation.setStatus(false);
		this.tripLocationRepository.save(tripLocation);

	}
	
	@Override
	public List<TripLocationDto> getTripLocationsByTripId(Integer tripId) {
		List<TripLocationDto> allTripLocations = new ArrayList<>();
		this.getAllTripLocations().forEach((tripLocation) -> {
			if (tripLocation.getTrip().getId() == tripId && tripLocation.isStatus()) {
				allTripLocations.add(tripLocation);
			}
		});
		return allTripLocations;
	}
	
	private TripLocation dtoToTripLocation(TripLocationDto tripLocationDto) {
		TripLocation tripLocation = this.modelMapper.map(tripLocationDto, TripLocation.class);
		return tripLocation;
	}
	
	private TripLocationDto tripLocationToDto(TripLocation tripLocation) {
		TripLocationDto tripLocationDto = this.modelMapper.map(tripLocation, TripLocationDto.class);
		return tripLocationDto;
	}

}
