package com.rovearound.tripplanner.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.TripNotes;
import com.rovearound.tripplanner.entities.User;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.payloads.TripNotesDto;
import com.rovearound.tripplanner.repositories.TripNotesRepository;
import com.rovearound.tripplanner.services.TripNotesService;
import com.rovearound.tripplanner.services.TripService;
import com.rovearound.tripplanner.services.UserService;

@Service
public class TripNotesServiceImplementation implements TripNotesService {
	@Autowired
	private TripNotesRepository tripNotesRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TripNotesDto createTripNotes(TripNotesDto tripNotesDto) {
		TripNotes tripNotes = this.dtoToTripNotes(tripNotesDto);
		TripNotes savedTripNotes = this.tripNotesRepository.save(tripNotes);
		return this.tripNotesToDto(savedTripNotes);
	}

	@Override
	public TripNotesDto updateTripNotes(TripNotesDto tripNotesDto, Integer tripNotesId) {
		TripNotes tripNotes = this.tripNotesRepository.findById(tripNotesId)
				.orElseThrow(() -> new ResourceNotFoundException("TripNotes", "Id", tripNotesId));
		
		tripNotes.setId(tripNotesDto.getId());
		tripNotes.setUpdatedBy(tripNotesDto.getUpdatedBy());
		tripNotes.setUpdatedOn(tripNotesDto.getUpdatedOn());
		tripNotes.setNote(tripNotesDto.getNote());
		tripNotes.setStatus(true);

		TripNotes updatedTripNotes = this.tripNotesRepository.save(tripNotes);
		TripNotesDto updatedTripNotesDto = this.tripNotesToDto(updatedTripNotes);
				
		return updatedTripNotesDto;
	}

	@Override
	public TripNotesDto getTripNotes(Integer tripNotesId) {
		TripNotes tripNotes = this.tripNotesRepository.findById(tripNotesId)
				.orElseThrow(() -> new ResourceNotFoundException("TripNotes", "Id", tripNotesId));
		
		return this.tripNotesToDto(tripNotes);
	}

	@Override
	public List<TripNotesDto> getAllTripNotes() {
		List<TripNotes> tripNotess = this.tripNotesRepository.findAll();
		
		List<TripNotesDto> tripNotesDtos = tripNotess.stream().map(tripNotes -> this.tripNotesToDto(tripNotes)).collect(Collectors.toList());
		return tripNotesDtos;
	}

	@Override
	public void deleteTripNotes(Integer tripNotesId) {
		TripNotes tripNotes = this.tripNotesRepository.findById(tripNotesId)
				.orElseThrow(() -> new ResourceNotFoundException("TripNotes", "Id", tripNotesId));
		
		tripNotes.setStatus(false);
		this.tripNotesRepository.save(tripNotes);

	}
	
	@Override
	public List<TripNotesDto> getTripNotesByTripId(Integer tripId) {
		List<TripNotesDto> allTripNotes = new ArrayList<>();
		this.getAllTripNotes().forEach((tripNotes) -> {
			if (tripNotes.getTripId() == tripId && tripNotes.isStatus()) {
				allTripNotes.add(tripNotes);
			}
		});
		return allTripNotes;
	}
	
	private TripNotes dtoToTripNotes(TripNotesDto tripNotesDto) {
		TripNotes tripNotes = this.modelMapper.map(tripNotesDto, TripNotes.class);
		return tripNotes;
	}
	
	private TripNotesDto tripNotesToDto(TripNotes tripNotes) {
		TripNotesDto tripNotesDto = this.modelMapper.map(tripNotes, TripNotesDto.class);
		return tripNotesDto;
	}

}
