package com.rovearound.tripplanner.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.Itinerary;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.payloads.ItineraryDto;
import com.rovearound.tripplanner.repositories.ItineraryRepository;
import com.rovearound.tripplanner.services.ItineraryService;

@Service
public class ItineraryServiceImplementation implements ItineraryService {

	@Autowired
	private ItineraryRepository itineraryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ItineraryDto createItinerary(ItineraryDto itineraryDto) {
		Itinerary itinerary = this.dtoToItinerary(itineraryDto);
		Itinerary savedItinerary = this.itineraryRepository.save(itinerary);
		return this.itineraryToDto(savedItinerary);
	}

	@Override
	public ItineraryDto updateItinerary(ItineraryDto itineraryDto, Integer itineraryId) {
		Itinerary itinerary = this.itineraryRepository.findById(itineraryId)
				.orElseThrow(() -> new ResourceNotFoundException("Itinerary", "Id", itineraryId));
		
		itinerary.setId(itineraryDto.getItineraryId());
		itinerary.setTrip(itineraryDto.getTrip());
		itinerary.setDate(itineraryDto.getDate());
		itinerary.setStatus(true);

		Itinerary updatedItinerary = this.itineraryRepository.save(itinerary);
		ItineraryDto updatedItineraryDto = this.itineraryToDto(updatedItinerary);
				
		return updatedItineraryDto;
	}

	@Override
	public ItineraryDto getItinerary(Integer itineraryId) {
		Itinerary itinerary = this.itineraryRepository.findById(itineraryId)
				.orElseThrow(() -> new ResourceNotFoundException("Itinerary", "Id", itineraryId));
		
		return this.itineraryToDto(itinerary);
	}

	@Override
	public List<ItineraryDto> getAllItinerarys() {
		List<Itinerary> itinerarys = this.itineraryRepository.findAll();
		
		List<ItineraryDto> itineraryDtos = itinerarys.stream().map(itinerary -> this.itineraryToDto(itinerary)).collect(Collectors.toList());
		return itineraryDtos;
	}

	@Override
	public void deleteItinerary(Integer itineraryId) {
		Itinerary itinerary = this.itineraryRepository.findById(itineraryId)
				.orElseThrow(() -> new ResourceNotFoundException("Itinerary", "Id", itineraryId));
		
		itinerary.setStatus(false);
		this.itineraryRepository.save(itinerary);

	}
	
	private Itinerary dtoToItinerary(ItineraryDto itineraryDto) {
		Itinerary itinerary = this.modelMapper.map(itineraryDto, Itinerary.class);
		return itinerary;
	}
	
	private ItineraryDto itineraryToDto(Itinerary itinerary) {
		ItineraryDto itineraryDto = this.modelMapper.map(itinerary, ItineraryDto.class);
		return itineraryDto;
	}
}
