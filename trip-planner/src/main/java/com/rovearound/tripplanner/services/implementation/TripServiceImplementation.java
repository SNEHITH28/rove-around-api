package com.rovearound.tripplanner.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.Trip;
import com.rovearound.tripplanner.entities.User;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.models.ItineraryDetails;
import com.rovearound.tripplanner.models.TripDetails;
import com.rovearound.tripplanner.payloads.BudgetDto;
import com.rovearound.tripplanner.payloads.ExpenseDto;
import com.rovearound.tripplanner.payloads.ItineraryDto;
import com.rovearound.tripplanner.payloads.ItineraryLocationDto;
import com.rovearound.tripplanner.payloads.ItineraryNotesDto;
import com.rovearound.tripplanner.payloads.TripDto;
import com.rovearound.tripplanner.payloads.TripLocationDto;
import com.rovearound.tripplanner.payloads.TripNotesDto;
import com.rovearound.tripplanner.payloads.UserDto;
import com.rovearound.tripplanner.repositories.TripRepository;
import com.rovearound.tripplanner.services.BudgetService;
import com.rovearound.tripplanner.services.ExpenseService;
import com.rovearound.tripplanner.services.ItineraryLocationService;
import com.rovearound.tripplanner.services.ItineraryNotesService;
import com.rovearound.tripplanner.services.ItineraryService;
import com.rovearound.tripplanner.services.TravelerService;
import com.rovearound.tripplanner.services.TripLocationService;
import com.rovearound.tripplanner.services.TripNotesService;
import com.rovearound.tripplanner.services.TripService;
import com.rovearound.tripplanner.services.UserService;

@Service
public class TripServiceImplementation implements TripService{
	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItineraryService itineraryService;
	
	@Autowired
	private BudgetService budgetService;
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private TravelerService travelerService;
	
	@Autowired
	private TripLocationService tripLocationService;
	
	@Autowired
	private TripNotesService tripNotesService;
	
	@Autowired
	private ItineraryNotesService itineraryNotesService;
	
	@Autowired
	private ItineraryLocationService itineraryLocationService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TripDto createTrip(TripDto tripDto) {
		Trip trip = this.dtoToTrip(tripDto);
		trip.setStatus(true);
		User user = this.modelMapper.map(userService.getUser(tripDto.getUserId()), User.class);
		trip.setUser(user);
		Trip savedTrip = this.tripRepository.save(trip);
		return this.tripToDto(savedTrip);
	}

	@Override
	public TripDto updateTrip(TripDto tripDto, Integer tripId) {
		Trip trip = this.tripRepository.findById(tripId)
				.orElseThrow(() -> new ResourceNotFoundException("Trip", "Id", tripId));
		
		
		trip.setId(tripDto.getId());
//		trip.setUser(tripDto.getUser());
		trip.setUpdatedOn(tripDto.getUpdatedOn());
		trip.setUpdatedBy(tripDto.getUpdatedBy());
		trip.setTripCode(tripDto.getTripCode());
		trip.setStatus(true);
		trip.setStartDate(tripDto.getStartDate());
		trip.setGoogleResponse(tripDto.getGoogleResponse());
		trip.setEndDate(tripDto.getEndDate());
		trip.setDestination(tripDto.getDestination());
		trip.setCreatedOn(tripDto.getCreatedOn());
		trip.setCreatedBy(tripDto.getCreatedBy());
		
		Trip updatedTrip = this.tripRepository.save(trip);
		TripDto updatedTripDto = this.tripToDto(updatedTrip);
				
		return updatedTripDto;
	}

	@Override
	public TripDto getTrip(Integer tripId) {
		Trip trip = this.tripRepository.findById(tripId)
				.orElseThrow(() -> new ResourceNotFoundException("Trip", "Id", tripId));
		
		return this.tripToDto(trip);
	}
	
	@Override
	public TripDetails getTripByTripCode(String tripCode) {
		List<TripDto> trips = new ArrayList<TripDto>();
		this.getAllTrips().forEach(el -> {
			if(el.getTripCode() != null && el.getTripCode().equals(tripCode)) {
				trips.add(el);
			}
		});
		if (trips.size() == 1) {	
			TripDto trip = trips.get(0);
			int tripId = trip.getId();
			TripDto tripResponse = this.getTrip(tripId);
			List<UserDto> travelers = travelerService.getUsersByTravelerId(tripId);
			List<ItineraryDto> itineraries = itineraryService.getItineraryByTripId(tripId);
			List<TripLocationDto> tripLocations = tripLocationService.getTripLocationsByTripId(tripId);
			BudgetDto budget = budgetService.getBudgetByTripId(tripId);
			List<ExpenseDto> expenses = expenseService.getExpenseByTripId(tripId);
			List<TripNotesDto> tripNotes = tripNotesService.getTripNotesByTripId(tripId);
			
			TripDetails tripDetails = new TripDetails();
			List<ItineraryDetails> itineraryDetails = new ArrayList<ItineraryDetails>();
			itineraries.forEach(el -> {
				List<ItineraryNotesDto> itineraryNotes = itineraryNotesService.getItineraryNotesByItineraryId(el.getId());
				List<ItineraryLocationDto> itineraryLocations = itineraryLocationService.getItineraryLocationsByItineraryId(el.getId());
				
				ItineraryDetails itineraryDetail = new ItineraryDetails();
				itineraryDetail.setItineraryNotes(itineraryNotes);
				itineraryDetail.setItineraryLocations(itineraryLocations);
				itineraryDetail.setItineraryId(el.getId());
				itineraryDetail.setStatus(el.isStatus());
				itineraryDetail.setDate(el.getDate());
				itineraryDetail.setTripId(tripId);
				
				itineraryDetails.add(itineraryDetail);
			});
			
			tripDetails.setTrip(tripResponse);
			tripDetails.setTravelers(travelers);
			tripDetails.setItineraries(itineraryDetails);
			tripDetails.setTripLocations(tripLocations);
			tripDetails.setBudget(budget);
			tripDetails.setExpenses(expenses);
			tripDetails.setTripNotes(tripNotes);
			return tripDetails;
		}
		return null;
	}

	@Override
	public List<TripDto> getAllTrips() {
		List<Trip> trips = this.tripRepository.findAll();
		
		List<TripDto> tripDtos = trips.stream().map(trip -> this.tripToDto(trip)).collect(Collectors.toList());
		return tripDtos;
	}

	@Override
	public void deleteTrip(Integer tripId) {
		Trip trip = this.tripRepository.findById(tripId)
				.orElseThrow(() -> new ResourceNotFoundException("Trip", "Id", tripId));
		
		trip.setStatus(false);
		this.tripRepository.save(trip);

	}
	
	private Trip dtoToTrip(TripDto tripDto) {
		Trip trip = this.modelMapper.map(tripDto, Trip.class);
		return trip;
	}
	
	private TripDto tripToDto(Trip trip) {
		TripDto tripDto = this.modelMapper.map(trip, TripDto.class);
		return tripDto;
	}

}
