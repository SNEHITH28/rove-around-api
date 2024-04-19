package com.rovearound.tripplanner.entities;

import java.util.Date;

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
@Table(name="itinerary")
@NoArgsConstructor
@Getter
@Setter
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    // Ref: itinerary.tripId > trips.id // many-to-one
    @ManyToOne
    @JoinColumn(name = "tripId")
    private Trip trip;

    private Date date;
    private boolean status;
}
