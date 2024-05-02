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
import com.rovearound.tripplanner.payloads.ItineraryLocationDto;
import com.rovearound.tripplanner.services.ItineraryLocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/itinerary-locations/")
@CrossOrigin(origins = "http://localhost:4200")
public class ItineraryLocationsController {
	@Autowired
	private ItineraryLocationService itinerarylocationService;

	@PostMapping("/add")
	public ResponseEntity<ItineraryLocationDto> createItineraryLocation(@Valid @RequestBody ItineraryLocationDto itinerarylocationDto) {
		ItineraryLocationDto createdItineraryLocationDto = this.itinerarylocationService.createItineraryLocation(itinerarylocationDto);
		return new ResponseEntity<>(createdItineraryLocationDto, HttpStatus.CREATED);
	}

	@PutMapping("/{itinerarylocationId}")
	public ResponseEntity<ItineraryLocationDto> updateItineraryLocation(@Valid @RequestBody ItineraryLocationDto itinerarylocationDto, @PathVariable("itinerarylocationId") Integer id) {
		ItineraryLocationDto updatedItineraryLocation = this.itinerarylocationService.updateItineraryLocation(itinerarylocationDto, id);
		return ResponseEntity.ok(updatedItineraryLocation);
	}

	@PostMapping("/{itinerarylocationId}")
	public ResponseEntity<ApiResponse> deleteItineraryLocation(@PathVariable("itinerarylocationId") Integer id) {
		this.itinerarylocationService.deleteItineraryLocation(id);
		return new ResponseEntity<>(new ApiResponse("itinerarylocation Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ItineraryLocationDto>> getAllItineraryLocations() {
		return ResponseEntity.ok(this.itinerarylocationService.getAllItineraryLocations());
	}

	@GetMapping("/{itinerarylocationId}")
	public ResponseEntity<ItineraryLocationDto> getTrip(@PathVariable Integer itinerarylocationId) {
		return ResponseEntity.ok(this.itinerarylocationService.getItineraryLocation(itinerarylocationId));
	}

}
