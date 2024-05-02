package com.rovearound.tripplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rovearound.tripplanner.entities.ItineraryLocation;

public interface ItineraryLocationRepository extends JpaRepository<ItineraryLocation, Integer>{

}
