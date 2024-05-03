package com.rovearound.tripplanner.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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

import com.rovearound.tripplanner.entities.Trip;
import com.rovearound.tripplanner.entities.User;
import com.rovearound.tripplanner.models.ItineraryDetails;
import com.rovearound.tripplanner.models.TripDetails;
import com.rovearound.tripplanner.models.TripInfo;
import com.rovearound.tripplanner.payloads.ApiResponse;
import com.rovearound.tripplanner.payloads.TripDto;
import com.rovearound.tripplanner.payloads.TripLocationDto;
import com.rovearound.tripplanner.payloads.TripNotesDto;
import com.rovearound.tripplanner.payloads.UserDto;
import com.rovearound.tripplanner.payloads.BudgetDto;
import com.rovearound.tripplanner.payloads.ExpenseDto;
import com.rovearound.tripplanner.payloads.ItineraryDto;
import com.rovearound.tripplanner.payloads.ItineraryLocationDto;
import com.rovearound.tripplanner.payloads.ItineraryNotesDto;
import com.rovearound.tripplanner.payloads.TravelerDto;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/trip/")
@CrossOrigin(origins = "http://localhost:4200")
public class TripController {
	@Autowired
	private TripService tripService;
	
	@Autowired
	private BudgetService budgetService;
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private ItineraryService itineraryService;
	
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
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/add")
	public ResponseEntity<TripDto> createTrip(@Valid @RequestBody TripDto tripDto) {
		tripDto.setTripCode(this.generateTripCode());
		System.out.println("testing l" + tripDto.getUserId());
		TripDto createdTripDto = this.tripService.createTrip(tripDto);
		this.createInitialBudgetForTrip(createdTripDto);
		this.createInitialItineraryForTrip(createdTripDto);
		this.createInitialTravelerForTrip(createdTripDto);
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
	
	@GetMapping("/all/{userId}")
	public ResponseEntity<List<TripInfo>> getAllTripsByUserId(@PathVariable("userId") Integer userId) {
		List<TripInfo> tripInfos = new ArrayList<>();
		List<TripDto> trips = this.tripService.getAllTrips();
		List<TravelerDto> travelers = this.travelerService.getAllTravelers();
		trips.forEach(trip -> {
			travelers.forEach(traveler -> {
				if (trip.isStatus() && traveler.getUser().getId() == userId && traveler.isStatus()) {
					TripDetails tripDetails = tripService.getTripByTripCode(trip.getTripCode());
					if (tripDetails != null) {						
						int numberOfPlaces = tripDetails.getTripLocations().size();
						for(ItineraryDetails el: tripDetails.getItineraries()) {
							numberOfPlaces += el.getItineraryLocations().size();
						};
						TripInfo tripInfo = new TripInfo();
						tripInfo.setId(trip.getId());
						tripInfo.setTripCode(trip.getTripCode());
						tripInfo.setDestination(trip.getDestination());
						tripInfo.setGoogleResponse(trip.getGoogleResponse());
						tripInfo.setStartDate(trip.getStartDate());
						tripInfo.setEndDate(trip.getEndDate());
						tripInfo.setNumberOfUsers(tripDetails.getTravelers().size());
						tripInfo.setBudget(tripDetails.getBudget().getAmount());
						tripInfo.setNumberOfPlaces(numberOfPlaces);
						
						tripInfos.add(tripInfo);
					}
				}
			});
		});
		return ResponseEntity.ok(tripInfos);
		
	}

	@GetMapping("/{tripCode}")
	public ResponseEntity<TripDetails> getTrip(@PathVariable String tripCode) {
		return ResponseEntity.ok(tripService.getTripByTripCode(tripCode));
	}
	
	private void createInitialBudgetForTrip(TripDto createdTripDto) {
		BudgetDto budgetDtoForCreatedTrip = new BudgetDto();
		
		budgetDtoForCreatedTrip.setAmount(0);
		budgetDtoForCreatedTrip.setStatus(true);
		budgetDtoForCreatedTrip.setTripId(createdTripDto.getId());
		this.budgetService.createBudget(budgetDtoForCreatedTrip);
	}
	
	private void createInitialExpenseForTrip(TripDto createdTripDto) {
		ExpenseDto expenseDtoForCreatedTrip = new ExpenseDto();
		
		expenseDtoForCreatedTrip.setAmount(0);
		expenseDtoForCreatedTrip.setStatus(true);
		expenseDtoForCreatedTrip.setTripId(createdTripDto.getId());
		expenseDtoForCreatedTrip.setUserId(createdTripDto.getUserId());
		expenseDtoForCreatedTrip.setPaidOn(null);
		expenseDtoForCreatedTrip.setSplitType("");
		expenseDtoForCreatedTrip.setCategoryId(0);
		expenseDtoForCreatedTrip.setCategoryDescription("");
		this.expenseService.createExpense(expenseDtoForCreatedTrip);
	}
	
	private void createInitialItineraryForTrip(TripDto createdTripDto) {
		ItineraryDto itineraryDtoForCreatedTrip = new ItineraryDto();
        itineraryDtoForCreatedTrip.setTripId(createdTripDto.getId());
        itineraryDtoForCreatedTrip.setStatus(true);

        // Define the custom date-time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        // Parse start date and end date using the custom formatter
        LocalDate startDate = LocalDate.parse(createdTripDto.getStartDate().toString(), formatter);
        LocalDate endDate = LocalDate.parse(createdTripDto.getEndDate().toString(), formatter);

        if (startDate.isBefore(endDate)) {
            long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);

            for (int i = 0; i <= daysDiff; i++) {
                LocalDate currentDate = startDate.plusDays(i);
                Date date = Date.from(currentDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                itineraryDtoForCreatedTrip.setDate(date);
                this.itineraryService.createItinerary(itineraryDtoForCreatedTrip);
            }
        }

	}
	
	private void createInitialTravelerForTrip(TripDto createdTripDto) {
		TravelerDto travelerDtoForCreatedTrip = new TravelerDto();
		
		travelerDtoForCreatedTrip.setStatus(true);
		travelerDtoForCreatedTrip.setTripId(createdTripDto.getId());
		UserDto user = userService.getUser(createdTripDto.getUserId());
		travelerDtoForCreatedTrip.setUser(this.dtoToUser(user));
		this.travelerService.createTraveler(travelerDtoForCreatedTrip);
	}
	
	private Trip dtoToTrip(TripDto tripDto) {
		Trip trip = this.modelMapper.map(tripDto, Trip.class);
		return trip;
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	private String generateTripCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return sb.toString();
    }
}
