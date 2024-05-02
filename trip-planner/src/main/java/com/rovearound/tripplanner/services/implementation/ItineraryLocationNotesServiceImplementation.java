package com.rovearound.tripplanner.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.ItineraryLocationNotes;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.payloads.ItineraryLocationNotesDto;
import com.rovearound.tripplanner.repositories.ItineraryLocationNotesRepository;
import com.rovearound.tripplanner.services.ItineraryLocationNotesService;

@Service
public class ItineraryLocationNotesServiceImplementation implements ItineraryLocationNotesService {
	@Autowired
	private ItineraryLocationNotesRepository itineraryLocationNotesRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ItineraryLocationNotesDto createItineraryLocationNotes(ItineraryLocationNotesDto itineraryLocationNotesDto) {
		ItineraryLocationNotes itineraryLocationNotes = this.dtoToItineraryLocationNotes(itineraryLocationNotesDto);
		ItineraryLocationNotes savedItineraryLocationNotes = this.itineraryLocationNotesRepository.save(itineraryLocationNotes);
		return this.itineraryLocationNotesToDto(savedItineraryLocationNotes);
	}

	@Override
	public ItineraryLocationNotesDto updateItineraryLocationNotes(ItineraryLocationNotesDto itineraryLocationNotesDto, Integer itineraryLocationNotesId) {
		ItineraryLocationNotes itineraryLocationNotes = this.itineraryLocationNotesRepository.findById(itineraryLocationNotesId)
				.orElseThrow(() -> new ResourceNotFoundException("ItineraryLocationNotes", "Id", itineraryLocationNotesId));
		
		itineraryLocationNotes.setId(itineraryLocationNotesDto.getId());
//		itineraryLocationNotes.setItineraryLocation(itineraryLocationNotesDto.getItineraryLocation());
//		itineraryLocationNotes.setUser(itineraryLocationNotesDto.getUser());
		itineraryLocationNotes.setStatus(true);

		ItineraryLocationNotes updatedItineraryLocationNotes = this.itineraryLocationNotesRepository.save(itineraryLocationNotes);
		ItineraryLocationNotesDto updatedItineraryLocationNotesDto = this.itineraryLocationNotesToDto(updatedItineraryLocationNotes);
				
		return updatedItineraryLocationNotesDto;
	}

	@Override
	public ItineraryLocationNotesDto getItineraryLocationNotes(Integer itineraryLocationNotesId) {
		ItineraryLocationNotes itineraryLocationNotes = this.itineraryLocationNotesRepository.findById(itineraryLocationNotesId)
				.orElseThrow(() -> new ResourceNotFoundException("ItineraryLocationNotes", "Id", itineraryLocationNotesId));
		
		return this.itineraryLocationNotesToDto(itineraryLocationNotes);
	}

	@Override
	public List<ItineraryLocationNotesDto> getAllItineraryLocationNotes() {
		List<ItineraryLocationNotes> itineraryLocationNotess = this.itineraryLocationNotesRepository.findAll();
		
		List<ItineraryLocationNotesDto> itineraryLocationNotesDtos = itineraryLocationNotess.stream().map(itineraryLocationNotes -> this.itineraryLocationNotesToDto(itineraryLocationNotes)).collect(Collectors.toList());
		return itineraryLocationNotesDtos;
	}

	@Override
	public void deleteItineraryLocationNotes(Integer itineraryLocationNotesId) {
		ItineraryLocationNotes itineraryLocationNotes = this.itineraryLocationNotesRepository.findById(itineraryLocationNotesId)
				.orElseThrow(() -> new ResourceNotFoundException("ItineraryLocationNotes", "Id", itineraryLocationNotesId));
		
		itineraryLocationNotes.setStatus(false);
		this.itineraryLocationNotesRepository.save(itineraryLocationNotes);

	}
	
	@Override
	public List<ItineraryLocationNotesDto> getItineraryLocationNotesByItineraryLocationId(Integer itineraryLocationId) {
		List<ItineraryLocationNotesDto> allItineraryLocationNotes = new ArrayList<>();
		this.getAllItineraryLocationNotes().forEach((itineraryLocationNotes) -> {
			if (itineraryLocationNotes.getItineraryLocationId() == itineraryLocationId && itineraryLocationNotes.isStatus()) {
				allItineraryLocationNotes.add(itineraryLocationNotes);
			}
		});
		return allItineraryLocationNotes;
	}
	
	private ItineraryLocationNotes dtoToItineraryLocationNotes(ItineraryLocationNotesDto itineraryLocationNotesDto) {
		ItineraryLocationNotes itineraryLocationNotes = this.modelMapper.map(itineraryLocationNotesDto, ItineraryLocationNotes.class);
		return itineraryLocationNotes;
	}
	
	private ItineraryLocationNotesDto itineraryLocationNotesToDto(ItineraryLocationNotes itineraryLocationNotes) {
		ItineraryLocationNotesDto itineraryLocationNotesDto = this.modelMapper.map(itineraryLocationNotes, ItineraryLocationNotesDto.class);
		return itineraryLocationNotesDto;
	}

}
