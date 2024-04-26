package com.rovearound.tripplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rovearound.tripplanner.entities.Itinerary;

public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {

}
