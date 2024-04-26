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
@Table(name="trip_notes_mapping")
@NoArgsConstructor
@Getter
@Setter
public class TripNotesMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    // Ref: trip_notes_mapping.note_id > notes.id
    @ManyToOne
    @JoinColumn(name = "noteId")
    private Notes note;

    // Ref: trip_notes_mapping.trip_id > trips.id
    @ManyToOne
    @JoinColumn(name = "tripId")
    private Trip trip;
}
