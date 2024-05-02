package com.rovearound.tripplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rovearound.tripplanner.entities.TripNotes;

public interface TripNotesRepository extends JpaRepository<TripNotes, Integer> {

}
