package com.rovearound.tripplanner.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
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

import com.rovearound.tripplanner.entities.User;
import com.rovearound.tripplanner.payloads.ApiResponse;
import com.rovearound.tripplanner.payloads.TravelerDto;
import com.rovearound.tripplanner.payloads.TravelerRequest;
import com.rovearound.tripplanner.payloads.TripDto;
import com.rovearound.tripplanner.payloads.UserDto;
import com.rovearound.tripplanner.services.TravelerService;
import com.rovearound.tripplanner.services.TripService;
import com.rovearound.tripplanner.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/traveler/")
@CrossOrigin(origins = "http://localhost:4200")
public class TravelerController {

	@Autowired
	private TravelerService travelerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/add")
	public ResponseEntity<?> createTraveler(@Valid @RequestBody TravelerRequest travelerRequest) {
		TripDto trip = this.tripService.getTripByTripCodeForTraveler(travelerRequest.getTripCode());
		for(UserDto el : this.travelerService.getUsersByTravelerId(trip.getId())) {
			if(el.getUserId() == travelerRequest.getUser().getUserId()) {
				return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(HttpStatus.ALREADY_REPORTED.value());
			}
		}
		if(this.userService.getUser(travelerRequest.getUser().getUserId()) != null) {
			TravelerDto travelerDto = new TravelerDto();
			travelerDto.setStatus(true);
			travelerDto.setTripId(trip.getId());
			travelerDto.setUser(this.modelMapper.map(travelerRequest.getUser(), User.class));
			TravelerDto createdTravelerDto = this.travelerService.createTraveler(travelerDto);
			return new ResponseEntity<>(createdTravelerDto, HttpStatus.CREATED);	
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND.value());
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
}
