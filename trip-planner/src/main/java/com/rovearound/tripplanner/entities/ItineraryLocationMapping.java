package com.rovearound.tripplanner.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="itinerary_location_mapping")
@NoArgsConstructor
@Getter
@Setter
public class ItineraryLocationMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Ref: itinerary_location_mapping.location_id > location.id // many-to-one
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    // Ref: itinerary_location_mapping.itinerary_id > itinerary.id // many-to-one
    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;
}
