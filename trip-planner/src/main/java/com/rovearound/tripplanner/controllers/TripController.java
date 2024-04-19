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
import com.rovearound.tripplanner.payloads.TripDto;
import com.rovearound.tripplanner.services.TripService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/trip/")
@CrossOrigin(origins = "http://localhost:4200")
public class TripController {
	@Autowired
	private TripService tripService;

	@PostMapping("/add")
	public ResponseEntity<TripDto> createTrip(@Valid @RequestBody TripDto tripDto) {
		TripDto createdTripDto = this.tripService.createTrip(tripDto);
		return new ResponseEntity<>(createdTripDto, HttpStatus.CREATED);
	}

	@PutMapping("/{tripId}")
	public ResponseEntity<TripDto> updateTrip(@Valid @RequestBody TripDto tripDto, @PathVariable("tripId") Integer id) {
		TripDto updatedTrip = this.tripService.updateTrip(tripDto, id);
		return ResponseEntity.ok(updatedTrip);
	}

	@PostMapping("/{tripId}")
	public ResponseEntity<ApiResponse> deleteTrip(@PathVariable("tripId") Integer id) {
		this.tripService.deleteTrip(id);
		return new ResponseEntity<>(new ApiResponse("trip Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<TripDto>> getAllTrips() {
		return ResponseEntity.ok(this.tripService.getAllTrips());
	}

	@GetMapping("/{tripId}")
	public ResponseEntity<TripDto> getTrip(@PathVariable Integer tripId) {
		return ResponseEntity.ok(this.tripService.getTrip(tripId));
	}
}
