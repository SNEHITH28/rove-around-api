package com.rovearound.tripplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rovearound.tripplanner.entities.Traveler;

public interface TravelerRepository extends JpaRepository<Traveler, Integer> {

}
