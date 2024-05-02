package com.rovearound.tripplanner.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.Trip;
import com.rovearound.tripplanner.entities.User;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.payloads.TripDto;
import com.rovearound.tripplanner.repositories.TripRepository;
import com.rovearound.tripplanner.services.TripService;
import com.rovearound.tripplanner.services.UserService;

@Service
public class TripServiceImplementation implements TripService{
	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TripDto createTrip(TripDto tripDto) {
		Trip trip = this.dtoToTrip(tripDto);
		trip.setStatus(true);
		User user = this.modelMapper.map(userService.getUser(tripDto.getUserId()), User.class);
		trip.setUser(user);
		Trip savedTrip = this.tripRepository.save(trip);
		return this.tripToDto(savedTrip);
	}

	@Override
	public TripDto updateTrip(TripDto tripDto, Integer tripId) {
		Trip trip = this.tripRepository.findById(tripId)
				.orElseThrow(() -> new ResourceNotFoundException("Trip", "Id", tripId));
		
		
		trip.setId(tripDto.getId());
//		trip.setUser(tripDto.getUser());
		trip.setUpdatedOn(tripDto.getUpdatedOn());
		trip.setUpdatedBy(tripDto.getUpdatedBy());
		trip.setTripCode(tripDto.getTripCode());
		trip.setStatus(true);
		trip.setStartDate(tripDto.getStartDate());
		trip.setGoogleResponse(tripDto.getGoogleResponse());
		trip.setEndDate(tripDto.getEndDate());
		trip.setDestination(tripDto.getDestination());
		trip.setCreatedOn(tripDto.getCreatedOn());
		trip.setCreatedBy(tripDto.getCreatedBy());
		
		Trip updatedTrip = this.tripRepository.save(trip);
		TripDto updatedTripDto = this.tripToDto(updatedTrip);
				
		return updatedTripDto;
	}

	@Override
	public TripDto getTrip(Integer tripId) {
		Trip trip = this.tripRepository.findById(tripId)
				.orElseThrow(() -> new ResourceNotFoundException("Trip", "Id", tripId));
		
		return this.tripToDto(trip);
	}

	@Override
	public List<TripDto> getAllTrips() {
		List<Trip> trips = this.tripRepository.findAll();
		
		List<TripDto> tripDtos = trips.stream().map(trip -> this.tripToDto(trip)).collect(Collectors.toList());
		return tripDtos;
	}

	@Override
	public void deleteTrip(Integer tripId) {
		Trip trip = this.tripRepository.findById(tripId)
				.orElseThrow(() -> new ResourceNotFoundException("Trip", "Id", tripId));
		
		trip.setStatus(false);
		this.tripRepository.save(trip);

	}
	
	private Trip dtoToTrip(TripDto tripDto) {
		Trip trip = this.modelMapper.map(tripDto, Trip.class);
		return trip;
	}
	
	private TripDto tripToDto(Trip trip) {
		TripDto tripDto = this.modelMapper.map(trip, TripDto.class);
		return tripDto;
	}

}
