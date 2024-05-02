package com.rovearound.tripplanner.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.ItineraryNotes;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.payloads.ItineraryNotesDto;
import com.rovearound.tripplanner.repositories.ItineraryNotesRepository;
import com.rovearound.tripplanner.services.ItineraryNotesService;

@Service
public class ItineraryNotesServiceImplementation implements ItineraryNotesService {
	@Autowired
	private ItineraryNotesRepository itineraryNotesRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ItineraryNotesDto createItineraryNotes(ItineraryNotesDto itineraryNotesDto) {
		ItineraryNotes itineraryNotes = this.dtoToItineraryNotes(itineraryNotesDto);
		ItineraryNotes savedItineraryNotes = this.itineraryNotesRepository.save(itineraryNotes);
		return this.itineraryNotesToDto(savedItineraryNotes);
	}

	@Override
	public ItineraryNotesDto updateItineraryNotes(ItineraryNotesDto itineraryNotesDto, Integer itineraryNotesId) {
		ItineraryNotes itineraryNotes = this.itineraryNotesRepository.findById(itineraryNotesId)
				.orElseThrow(() -> new ResourceNotFoundException("ItineraryNotes", "Id", itineraryNotesId));
		
		itineraryNotes.setId(itineraryNotesDto.getId());
		itineraryNotes.setItinerary(itineraryNotesDto.getItinerary());
		itineraryNotes.setUser(itineraryNotesDto.getUser());
		itineraryNotes.setStatus(true);

		ItineraryNotes updatedItineraryNotes = this.itineraryNotesRepository.save(itineraryNotes);
		ItineraryNotesDto updatedItineraryNotesDto = this.itineraryNotesToDto(updatedItineraryNotes);
				
		return updatedItineraryNotesDto;
	}

	@Override
	public ItineraryNotesDto getItineraryNotes(Integer itineraryNotesId) {
		ItineraryNotes itineraryNotes = this.itineraryNotesRepository.findById(itineraryNotesId)
				.orElseThrow(() -> new ResourceNotFoundException("ItineraryNotes", "Id", itineraryNotesId));
		
		return this.itineraryNotesToDto(itineraryNotes);
	}

	@Override
	public List<ItineraryNotesDto> getAllItineraryNotes() {
		List<ItineraryNotes> itineraryNotess = this.itineraryNotesRepository.findAll();
		
		List<ItineraryNotesDto> itineraryNotesDtos = itineraryNotess.stream().map(itineraryNotes -> this.itineraryNotesToDto(itineraryNotes)).collect(Collectors.toList());
		return itineraryNotesDtos;
	}

	@Override
	public void deleteItineraryNotes(Integer itineraryNotesId) {
		ItineraryNotes itineraryNotes = this.itineraryNotesRepository.findById(itineraryNotesId)
				.orElseThrow(() -> new ResourceNotFoundException("ItineraryNotes", "Id", itineraryNotesId));
		
		itineraryNotes.setStatus(false);
		this.itineraryNotesRepository.save(itineraryNotes);

	}
	
	@Override
	public List<ItineraryNotesDto> getItineraryNotesByItineraryId(Integer itineraryId) {
		List<ItineraryNotesDto> allItineraryNotes = new ArrayList<>();
		this.getAllItineraryNotes().forEach((itineraryNotes) -> {
			if (itineraryNotes.getItinerary().getId() == itineraryId && itineraryNotes.isStatus()) {
				allItineraryNotes.add(itineraryNotes);
			}
		});
		return allItineraryNotes;
	}
	
	private ItineraryNotes dtoToItineraryNotes(ItineraryNotesDto itineraryNotesDto) {
		ItineraryNotes itineraryNotes = this.modelMapper.map(itineraryNotesDto, ItineraryNotes.class);
		return itineraryNotes;
	}
	
	private ItineraryNotesDto itineraryNotesToDto(ItineraryNotes itineraryNotes) {
		ItineraryNotesDto itineraryNotesDto = this.modelMapper.map(itineraryNotes, ItineraryNotesDto.class);
		return itineraryNotesDto;
	}

}

