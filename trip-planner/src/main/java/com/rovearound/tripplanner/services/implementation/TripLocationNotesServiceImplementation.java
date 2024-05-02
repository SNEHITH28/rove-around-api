package com.rovearound.tripplanner.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.TripLocationNotes;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.payloads.TripLocationNotesDto;
import com.rovearound.tripplanner.repositories.TripLocationNotesRepository;
import com.rovearound.tripplanner.services.TripLocationNotesService;

@Service
public class TripLocationNotesServiceImplementation implements TripLocationNotesService {
	@Autowired
	private TripLocationNotesRepository tripLocationNotesRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TripLocationNotesDto createTripLocationNotes(TripLocationNotesDto tripLocationNotesDto) {
		TripLocationNotes tripLocationNotes = this.dtoToTripLocationNotes(tripLocationNotesDto);
		TripLocationNotes savedTripLocationNotes = this.tripLocationNotesRepository.save(tripLocationNotes);
		return this.tripLocationNotesToDto(savedTripLocationNotes);
	}

	@Override
	public TripLocationNotesDto updateTripLocationNotes(TripLocationNotesDto tripLocationNotesDto, Integer tripLocationNotesId) {
		TripLocationNotes tripLocationNotes = this.tripLocationNotesRepository.findById(tripLocationNotesId)
				.orElseThrow(() -> new ResourceNotFoundException("TripLocationNotes", "Id", tripLocationNotesId));
		
		tripLocationNotes.setId(tripLocationNotesDto.getId());
		tripLocationNotes.setTripLocation(tripLocationNotesDto.getTripLocation());
		tripLocationNotes.setUser(tripLocationNotesDto.getUser());
		tripLocationNotes.setStatus(true);

		TripLocationNotes updatedTripLocationNotes = this.tripLocationNotesRepository.save(tripLocationNotes);
		TripLocationNotesDto updatedTripLocationNotesDto = this.tripLocationNotesToDto(updatedTripLocationNotes);
				
		return updatedTripLocationNotesDto;
	}

	@Override
	public TripLocationNotesDto getTripLocationNotes(Integer tripLocationNotesId) {
		TripLocationNotes tripLocationNotes = this.tripLocationNotesRepository.findById(tripLocationNotesId)
				.orElseThrow(() -> new ResourceNotFoundException("TripLocationNotes", "Id", tripLocationNotesId));
		
		return this.tripLocationNotesToDto(tripLocationNotes);
	}

	@Override
	public List<TripLocationNotesDto> getAllTripLocationNotes() {
		List<TripLocationNotes> tripLocationNotess = this.tripLocationNotesRepository.findAll();
		
		List<TripLocationNotesDto> tripLocationNotesDtos = tripLocationNotess.stream().map(tripLocationNotes -> this.tripLocationNotesToDto(tripLocationNotes)).collect(Collectors.toList());
		return tripLocationNotesDtos;
	}

	@Override
	public void deleteTripLocationNotes(Integer tripLocationNotesId) {
		TripLocationNotes tripLocationNotes = this.tripLocationNotesRepository.findById(tripLocationNotesId)
				.orElseThrow(() -> new ResourceNotFoundException("TripLocationNotes", "Id", tripLocationNotesId));
		
		tripLocationNotes.setStatus(false);
		this.tripLocationNotesRepository.save(tripLocationNotes);

	}
	
	@Override
	public List<TripLocationNotesDto> getTripLocationNotesByTripLocationId(Integer tripLocationId) {
		List<TripLocationNotesDto> allTripLocationNotes = new ArrayList<>();
		this.getAllTripLocationNotes().forEach((tripLocationNotes) -> {
			if (tripLocationNotes.getTripLocation().getId() == tripLocationId && tripLocationNotes.isStatus()) {
				allTripLocationNotes.add(tripLocationNotes);
			}
		});
		return allTripLocationNotes;
	}
	
	private TripLocationNotes dtoToTripLocationNotes(TripLocationNotesDto tripLocationNotesDto) {
		TripLocationNotes tripLocationNotes = this.modelMapper.map(tripLocationNotesDto, TripLocationNotes.class);
		return tripLocationNotes;
	}
	
	private TripLocationNotesDto tripLocationNotesToDto(TripLocationNotes tripLocationNotes) {
		TripLocationNotesDto tripLocationNotesDto = this.modelMapper.map(tripLocationNotes, TripLocationNotesDto.class);
		return tripLocationNotesDto;
	}

}

