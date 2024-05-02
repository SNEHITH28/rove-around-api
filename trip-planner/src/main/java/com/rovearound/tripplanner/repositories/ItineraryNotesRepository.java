package com.rovearound.tripplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rovearound.tripplanner.entities.ItineraryNotes;

public interface ItineraryNotesRepository extends JpaRepository<ItineraryNotes, Integer> {

}
