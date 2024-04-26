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
import com.rovearound.tripplanner.payloads.ItineraryDto;
import com.rovearound.tripplanner.services.ItineraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/itinerary/")
@CrossOrigin(origins = "http://localhost:4200")
public class ItineraryController {
	@Autowired
	private ItineraryService itineraryService;

	@PostMapping("/add")
	public ResponseEntity<ItineraryDto> createItinerary(@Valid @RequestBody ItineraryDto itineraryDto) {
		ItineraryDto createdItineraryDto = this.itineraryService.createItinerary(itineraryDto);
		return new ResponseEntity<>(createdItineraryDto, HttpStatus.CREATED);
	}

	@PutMapping("/{itineraryId}")
	public ResponseEntity<ItineraryDto> updateItinerary(@Valid @RequestBody ItineraryDto itineraryDto, @PathVariable("itineraryId") Integer id) {
		ItineraryDto updatedItinerary = this.itineraryService.updateItinerary(itineraryDto, id);
		return ResponseEntity.ok(updatedItinerary);
	}

	@PostMapping("/{itineraryId}")
	public ResponseEntity<ApiResponse> deleteItinerary(@PathVariable("itineraryId") Integer id) {
		this.itineraryService.deleteItinerary(id);
		return new ResponseEntity<>(new ApiResponse("itinerary Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ItineraryDto>> getAllItinerarys() {
		return ResponseEntity.ok(this.itineraryService.getAllItinerarys());
	}

	@GetMapping("/{itineraryId}")
	public ResponseEntity<ItineraryDto> getTrip(@PathVariable Integer itineraryId) {
		return ResponseEntity.ok(this.itineraryService.getItinerary(itineraryId));
	}

}
