package com.rovearound.tripplanner.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rovearound.tripplanner.payloads.ApiResponse;
import com.rovearound.tripplanner.payloads.ItineraryNotesDto;
import com.rovearound.tripplanner.services.ItineraryNotesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/itinerary-notes")
@CrossOrigin(origins = "http://localhost:4200")
public class ItineraryNotesController {

	@Autowired
	private ItineraryNotesService itineraryNotesService;

	@PostMapping("/add")
	public ResponseEntity<ItineraryNotesDto> createItineraryNotes(@Valid @RequestBody ItineraryNotesDto itineraryNotesDto) {
		ItineraryNotesDto createdItineraryNotesDto = this.itineraryNotesService.createItineraryNotes(itineraryNotesDto);
		return new ResponseEntity<>(createdItineraryNotesDto, HttpStatus.CREATED);
	}

	@PutMapping("/{itineraryNotesId}")
	public ResponseEntity<ItineraryNotesDto> updateItineraryNotes(@Valid @RequestBody ItineraryNotesDto itineraryNotesDto, @PathVariable("itineraryNotesId") Integer id) {
		ItineraryNotesDto updatedItineraryNotes = this.itineraryNotesService.updateItineraryNotes(itineraryNotesDto, id);
		return ResponseEntity.ok(updatedItineraryNotes);
	}

	@PostMapping("/{itineraryNotesId}")
	public ResponseEntity<ApiResponse> deleteItineraryNotes(@PathVariable("itineraryNotesId") Integer id) {
		this.itineraryNotesService.deleteItineraryNotes(id);
		return new ResponseEntity<>(new ApiResponse("itineraryNotes Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ItineraryNotesDto>> getAllItineraryNotess() {
		return ResponseEntity.ok(this.itineraryNotesService.getAllItineraryNotes());
	}

	@GetMapping("/{itineraryNotesId}")
	public ResponseEntity<ItineraryNotesDto> getTrip(@PathVariable Integer itineraryNotesId) {
		return ResponseEntity.ok(this.itineraryNotesService.getItineraryNotes(itineraryNotesId));
	}
}
