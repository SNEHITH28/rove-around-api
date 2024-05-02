package com.rovearound.tripplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rovearound.tripplanner.entities.TripLocation;

public interface TripLocationRepository extends JpaRepository<TripLocation, Integer> {

}
