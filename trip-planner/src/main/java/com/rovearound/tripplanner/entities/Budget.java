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
@Table(name="budget")
@NoArgsConstructor
@Getter
@Setter
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Ref: budget.trip_id > trips.id
    @ManyToOne
    @JoinColumn(name = "tripId")
    private Trip trip;

    private float amount;
    
    private boolean status;
}
