package com.rovearound.tripplanner.controllers;

import java.util.ArrayList;
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
import com.rovearound.tripplanner.payloads.TravelerDto;
import com.rovearound.tripplanner.services.TravelerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/traveler/")
@CrossOrigin(origins = "http://localhost:4200")
public class TravelerController {

	@Autowired
	private TravelerService travelerService;

	@PostMapping("/add")
	public ResponseEntity<TravelerDto> createTraveler(@Valid @RequestBody TravelerDto travelerDto) {
		TravelerDto createdTravelerDto = this.travelerService.createTraveler(travelerDto);
		return new ResponseEntity<>(createdTravelerDto, HttpStatus.CREATED);
	}

	@PutMapping("/{travelerId}")
	public ResponseEntity<TravelerDto> updateTraveler(@Valid @RequestBody TravelerDto travelerDto, @PathVariable("travelerId") Integer id) {
		TravelerDto updatedTraveler = this.travelerService.updateTraveler(travelerDto, id);
		return ResponseEntity.ok(updatedTraveler);
	}

	@PostMapping("/{travelerId}")
	public ResponseEntity<ApiResponse> deleteTraveler(@PathVariable("travelerId") Integer id) {
		this.travelerService.deleteTraveler(id);
		return new ResponseEntity<>(new ApiResponse("traveler Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<TravelerDto>> getAllTravelers() {
		return ResponseEntity.ok(this.travelerService.getAllTravelers());
	}

	@GetMapping("/{travelerId}")
	public ResponseEntity<TravelerDto> getTrip(@PathVariable Integer travelerId) {
		return ResponseEntity.ok(this.travelerService.getTraveler(travelerId));
	}
	
	public List<TravelerDto> getTravelerByTripId(Integer tripId) {
		ResponseEntity<List<TravelerDto>> travelersResponseEntity = this.getAllTravelers();
		List<TravelerDto> allTravelers = travelersResponseEntity.getBody();
		List<TravelerDto> travelers = new ArrayList<TravelerDto>();
		allTravelers.forEach((traveler) -> {
			if (traveler.getTrip().getId() == tripId && traveler.isStatus()) {
				travelers.add(traveler);
			}
		});
		return travelers;
	}
}
