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
@Table(name="itinerary_notes_mapping")
@NoArgsConstructor
@Getter
@Setter
public class ItineraryNotesMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Ref: itinerary_notes_mapping.note_id > notes.id
    @ManyToOne
    @JoinColumn(name = "noteId")
    private Notes note;

    // Ref: itinerary_notes_mapping.itinerary_id > itinerary.id
    @ManyToOne
    @JoinColumn(name = "itineraryId")
    private Itinerary itinerary;
}
