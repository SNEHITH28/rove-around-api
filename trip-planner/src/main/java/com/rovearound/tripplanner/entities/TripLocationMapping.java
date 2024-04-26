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
@Table(name="trip_location_mapping")
@NoArgsConstructor
@Getter
@Setter
public class TripLocationMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Ref: trip_location_mapping.location_id > location.id // many-to-one
    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;

    // Ref: trip_location_mapping.trip_id > trips.id // many-to-one
    @ManyToOne
    @JoinColumn(name = "tripId")
    private Trip trip;
}
