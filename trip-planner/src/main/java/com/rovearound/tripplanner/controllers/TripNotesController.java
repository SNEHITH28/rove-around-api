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
import com.rovearound.tripplanner.payloads.TripNotesDto;
import com.rovearound.tripplanner.services.TripNotesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/trip-notes")
@CrossOrigin(origins = "http://localhost:4200")
public class TripNotesController {

	@Autowired
	private TripNotesService tripNotesService;

	@PostMapping("/add")
	public ResponseEntity<TripNotesDto> createTripNotes(@Valid @RequestBody TripNotesDto tripNotesDto) {
		TripNotesDto createdTripNotesDto = this.tripNotesService.createTripNotes(tripNotesDto);
		return new ResponseEntity<>(createdTripNotesDto, HttpStatus.CREATED);
	}

	@PutMapping("/{tripNotesId}")
	public ResponseEntity<TripNotesDto> updateTripNotes(@Valid @RequestBody TripNotesDto tripNotesDto, @PathVariable("tripNotesId") Integer id) {
		TripNotesDto updatedTripNotes = this.tripNotesService.updateTripNotes(tripNotesDto, id);
		return ResponseEntity.ok(updatedTripNotes);
	}

	@PostMapping("/{tripNotesId}")
	public ResponseEntity<ApiResponse> deleteTripNotes(@PathVariable("tripNotesId") Integer id) {
		this.tripNotesService.deleteTripNotes(id);
		return new ResponseEntity<>(new ApiResponse("tripNotes Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<TripNotesDto>> getAllTripNotess() {
		return ResponseEntity.ok(this.tripNotesService.getAllTripNotes());
	}

	@GetMapping("/{tripNotesId}")
	public ResponseEntity<TripNotesDto> getTrip(@PathVariable Integer tripNotesId) {
		return ResponseEntity.ok(this.tripNotesService.getTripNotes(tripNotesId));
	}
}
