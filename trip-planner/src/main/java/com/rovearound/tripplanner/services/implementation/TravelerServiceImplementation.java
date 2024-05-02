package com.rovearound.tripplanner.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.Traveler;
import com.rovearound.tripplanner.entities.User;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.payloads.TravelerDto;
import com.rovearound.tripplanner.payloads.UserDto;
import com.rovearound.tripplanner.repositories.TravelerRepository;
import com.rovearound.tripplanner.services.ItineraryService;
import com.rovearound.tripplanner.services.TravelerService;
import com.rovearound.tripplanner.services.UserService;

@Service
public class TravelerServiceImplementation implements TravelerService {

	@Autowired
	private TravelerRepository travelerRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TravelerDto createTraveler(TravelerDto travelerDto) {
		Traveler traveler = this.dtoToTraveler(travelerDto);
		Traveler savedTraveler = this.travelerRepository.save(traveler);
		return this.travelerToDto(savedTraveler);
	}

	@Override
	public TravelerDto updateTraveler(TravelerDto travelerDto, Integer travelerId) {
		Traveler traveler = this.travelerRepository.findById(travelerId)
				.orElseThrow(() -> new ResourceNotFoundException("Traveler", "Id", travelerId));
		
		traveler.setId(travelerDto.getTravelerId());
		traveler.setTrip(travelerDto.getTrip());
		traveler.setUser(travelerDto.getUser());
		traveler.setStatus(true);

		Traveler updatedTraveler = this.travelerRepository.save(traveler);
		TravelerDto updatedTravelerDto = this.travelerToDto(updatedTraveler);
				
		return updatedTravelerDto;
	}

	@Override
	public TravelerDto getTraveler(Integer travelerId) {
		Traveler traveler = this.travelerRepository.findById(travelerId)
				.orElseThrow(() -> new ResourceNotFoundException("Traveler", "Id", travelerId));
		
		return this.travelerToDto(traveler);
	}

	@Override
	public List<TravelerDto> getAllTravelers() {
		List<Traveler> travelers = this.travelerRepository.findAll();
		
		List<TravelerDto> travelerDtos = travelers.stream().map(traveler -> this.travelerToDto(traveler)).collect(Collectors.toList());
		return travelerDtos;
	}

	@Override
	public void deleteTraveler(Integer travelerId) {
		Traveler traveler = this.travelerRepository.findById(travelerId)
				.orElseThrow(() -> new ResourceNotFoundException("Traveler", "Id", travelerId));
		
		traveler.setStatus(false);
		this.travelerRepository.save(traveler);

	}
	
	@Override
	public List<UserDto> getUsersByTravelerId(Integer tripId) {
		List<TravelerDto> allTravelers = this.getAllTravelers();
		List<UserDto> users = new ArrayList<UserDto>();
		allTravelers.forEach((traveler) -> {
			if (traveler.getTrip().getId() == tripId && traveler.isStatus()) {
				int userId = traveler.getUser().getId();
				users.add(userService.getUser(userId));
			}
		});
		return users;
	}
	
	private Traveler dtoToTraveler(TravelerDto travelerDto) {
		Traveler traveler = this.modelMapper.map(travelerDto, Traveler.class);
		return traveler;
	}
	
	private TravelerDto travelerToDto(Traveler traveler) {
		TravelerDto travelerDto = this.modelMapper.map(traveler, TravelerDto.class);
		return travelerDto;
	}
}

