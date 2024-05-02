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
import com.rovearound.tripplanner.payloads.TripLocationDto;
import com.rovearound.tripplanner.services.TripLocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/trip-locations/")
@CrossOrigin(origins = "http://localhost:4200")
public class TripLocationsController {
	@Autowired
	private TripLocationService tripLocationService;

	@PostMapping("/add")
	public ResponseEntity<TripLocationDto> createTripLocation(@Valid @RequestBody TripLocationDto tripLocationDto) {
		TripLocationDto createdTripLocationDto = this.tripLocationService.createTripLocation(tripLocationDto);
		return new ResponseEntity<>(createdTripLocationDto, HttpStatus.CREATED);
	}

	@PutMapping("/{tripLocationId}")
	public ResponseEntity<TripLocationDto> updateTripLocation(@Valid @RequestBody TripLocationDto tripLocationDto, @PathVariable("tripLocationId") Integer id) {
		TripLocationDto updatedTripLocation = this.tripLocationService.updateTripLocation(tripLocationDto, id);
		return ResponseEntity.ok(updatedTripLocation);
	}

	@PostMapping("/{tripLocationId}")
	public ResponseEntity<ApiResponse> deleteTripLocation(@PathVariable("tripLocationId") Integer id) {
		this.tripLocationService.deleteTripLocation(id);
		return new ResponseEntity<>(new ApiResponse("tripLocation Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<TripLocationDto>> getAllTripLocations() {
		return ResponseEntity.ok(this.tripLocationService.getAllTripLocations());
	}

	@GetMapping("/{tripLocationId}")
	public ResponseEntity<TripLocationDto> getTrip(@PathVariable Integer tripLocationId) {
		return ResponseEntity.ok(this.tripLocationService.getTripLocation(tripLocationId));
	}

}
