package com.rovearound.tripplanner.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.Itinerary;
import com.rovearound.tripplanner.entities.ItineraryLocation;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.payloads.ItineraryDto;
import com.rovearound.tripplanner.payloads.ItineraryLocationDto;
import com.rovearound.tripplanner.repositories.ItineraryLocationRepository;
import com.rovearound.tripplanner.services.ItineraryLocationService;
import com.rovearound.tripplanner.services.ItineraryService;

@Service
public class ItineraryLocationServiceImplementation implements ItineraryLocationService {

	@Autowired
	private ItineraryLocationRepository itineraryLocationRepository;
	
	@Autowired
	private ItineraryService itineraryService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ItineraryLocationDto createItineraryLocation(ItineraryLocationDto itineraryLocationDto) {
		ItineraryLocation itineraryLocation = this.dtoToItineraryLocation(itineraryLocationDto);
		ItineraryDto i = itineraryService.getItinerary(itineraryLocationDto.getItineraryId());
		itineraryLocation.setItinerary(this.modelMapper.map(i, Itinerary.class));
		ItineraryLocation savedItineraryLocation = this.itineraryLocationRepository.save(itineraryLocation);
		return this.itineraryLocationToDto(savedItineraryLocation);
	}

	@Override
	public ItineraryLocationDto updateItineraryLocation(ItineraryLocationDto itineraryLocationDto, Integer itineraryLocationId) {
		ItineraryLocation itineraryLocation = this.itineraryLocationRepository.findById(itineraryLocationId)
				.orElseThrow(() -> new ResourceNotFoundException("ItineraryLocation", "Id", itineraryLocationId));
		
		itineraryLocation.setId(itineraryLocationDto.getId());
//		itineraryLocation.setItinerary(itineraryLocationDto.getItinerary());
//		itineraryLocation.setUser(itineraryLocationDto.getUser());
		itineraryLocation.setStatus(true);

		ItineraryLocation updatedItineraryLocation = this.itineraryLocationRepository.save(itineraryLocation);
		ItineraryLocationDto updatedItineraryLocationDto = this.itineraryLocationToDto(updatedItineraryLocation);
				
		return updatedItineraryLocationDto;
	}

	@Override
	public ItineraryLocationDto getItineraryLocation(Integer itineraryLocationId) {
		ItineraryLocation itineraryLocation = this.itineraryLocationRepository.findById(itineraryLocationId)
				.orElseThrow(() -> new ResourceNotFoundException("ItineraryLocation", "Id", itineraryLocationId));
		
		return this.itineraryLocationToDto(itineraryLocation);
	}

	@Override
	public List<ItineraryLocationDto> getAllItineraryLocations() {
		List<ItineraryLocation> itineraryLocations = this.itineraryLocationRepository.findAll();
		
		List<ItineraryLocationDto> itineraryLocationDtos = itineraryLocations.stream().map(itineraryLocation -> this.itineraryLocationToDto(itineraryLocation)).collect(Collectors.toList());
		return itineraryLocationDtos;
	}

	@Override
	public void deleteItineraryLocation(Integer itineraryLocationId) {
		ItineraryLocation itineraryLocation = this.itineraryLocationRepository.findById(itineraryLocationId)
				.orElseThrow(() -> new ResourceNotFoundException("ItineraryLocation", "Id", itineraryLocationId));
		
		itineraryLocation.setStatus(false);
		this.itineraryLocationRepository.save(itineraryLocation);

	}
	
	@Override
	public List<ItineraryLocationDto> getItineraryLocationsByItineraryId(Integer itineraryId) {
		List<ItineraryLocationDto> allItineraryLocations = new ArrayList<>();
		this.getAllItineraryLocations().forEach((itineraryLocation) -> {
			if (itineraryLocation.getItineraryId() == itineraryId && itineraryLocation.isStatus()) {
				allItineraryLocations.add(itineraryLocation);
			}
		});
		return allItineraryLocations;
	}
	
	private ItineraryLocation dtoToItineraryLocation(ItineraryLocationDto itineraryLocationDto) {
		ItineraryLocation itineraryLocation = this.modelMapper.map(itineraryLocationDto, ItineraryLocation.class);
		return itineraryLocation;
	}
	
	private ItineraryLocationDto itineraryLocationToDto(ItineraryLocation itineraryLocation) {
		ItineraryLocationDto itineraryLocationDto = this.modelMapper.map(itineraryLocation, ItineraryLocationDto.class);
		return itineraryLocationDto;
	}
}

