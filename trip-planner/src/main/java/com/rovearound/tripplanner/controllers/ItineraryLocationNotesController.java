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
import com.rovearound.tripplanner.payloads.ItineraryLocationNotesDto;
import com.rovearound.tripplanner.services.ItineraryLocationNotesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/itinerary-loactions-notes/")
@CrossOrigin(origins = "http://localhost:4200")
public class ItineraryLocationNotesController {
	@Autowired
	private ItineraryLocationNotesService itinerarylocationService;

	@PostMapping("/add")
	public ResponseEntity<ItineraryLocationNotesDto> createItineraryLocationNotes(@Valid @RequestBody ItineraryLocationNotesDto itinerarylocationDto) {
		ItineraryLocationNotesDto createdItineraryLocationNotesDto = this.itinerarylocationService.createItineraryLocationNotes(itinerarylocationDto);
		return new ResponseEntity<>(createdItineraryLocationNotesDto, HttpStatus.CREATED);
	}

	@PutMapping("/{itinerarylocationId}")
	public ResponseEntity<ItineraryLocationNotesDto> updateItineraryLocationNotes(@Valid @RequestBody ItineraryLocationNotesDto itinerarylocationDto, @PathVariable("itinerarylocationId") Integer id) {
		ItineraryLocationNotesDto updatedItineraryLocationNotes = this.itinerarylocationService.updateItineraryLocationNotes(itinerarylocationDto, id);
		return ResponseEntity.ok(updatedItineraryLocationNotes);
	}

	@PostMapping("/{itinerarylocationId}")
	public ResponseEntity<ApiResponse> deleteItineraryLocationNotes(@PathVariable("itinerarylocationId") Integer id) {
		this.itinerarylocationService.deleteItineraryLocationNotes(id);
		return new ResponseEntity<>(new ApiResponse("itinerarylocation Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ItineraryLocationNotesDto>> getAllItineraryLocationNotess() {
		return ResponseEntity.ok(this.itinerarylocationService.getAllItineraryLocationNotes());
	}

	@GetMapping("/{itinerarylocationId}")
	public ResponseEntity<ItineraryLocationNotesDto> getTrip(@PathVariable Integer itinerarylocationId) {
		return ResponseEntity.ok(this.itinerarylocationService.getItineraryLocationNotes(itinerarylocationId));
	}

}
