package com.rovearound.tripplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rovearound.tripplanner.entities.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer>{

}
