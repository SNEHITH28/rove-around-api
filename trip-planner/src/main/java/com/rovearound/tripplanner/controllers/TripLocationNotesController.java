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
import com.rovearound.tripplanner.payloads.TripLocationNotesDto;
import com.rovearound.tripplanner.services.TripLocationNotesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/trip-location-notes/")
@CrossOrigin(origins = "http://localhost:4200")
public class TripLocationNotesController {

	@Autowired
	private TripLocationNotesService tripLocationNotesService;

	@PostMapping("/add")
	public ResponseEntity<TripLocationNotesDto> createTripLocationNotes(@Valid @RequestBody TripLocationNotesDto tripLocationNotesDto) {
		TripLocationNotesDto createdTripLocationNotesDto = this.tripLocationNotesService.createTripLocationNotes(tripLocationNotesDto);
		return new ResponseEntity<>(createdTripLocationNotesDto, HttpStatus.CREATED);
	}

	@PutMapping("/{tripLocationNotesId}")
	public ResponseEntity<TripLocationNotesDto> updateTripLocationNotes(@Valid @RequestBody TripLocationNotesDto tripLocationNotesDto, @PathVariable("tripLocationNotesId") Integer id) {
		TripLocationNotesDto updatedTripLocationNotes = this.tripLocationNotesService.updateTripLocationNotes(tripLocationNotesDto, id);
		return ResponseEntity.ok(updatedTripLocationNotes);
	}

	@PostMapping("/{tripLocationNotesId}")
	public ResponseEntity<ApiResponse> deleteTripLocationNotes(@PathVariable("tripLocationNotesId") Integer id) {
		this.tripLocationNotesService.deleteTripLocationNotes(id);
		return new ResponseEntity<>(new ApiResponse("tripLocationNotes Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<TripLocationNotesDto>> getAllTripLocationNotess() {
		return ResponseEntity.ok(this.tripLocationNotesService.getAllTripLocationNotes());
	}

	@GetMapping("/{tripLocationNotesId}")
	public ResponseEntity<TripLocationNotesDto> getTrip(@PathVariable Integer tripLocationNotesId) {
		return ResponseEntity.ok(this.tripLocationNotesService.getTripLocationNotes(tripLocationNotesId));
	}
}
