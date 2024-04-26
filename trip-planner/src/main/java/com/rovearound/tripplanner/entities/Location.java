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
@Table(name="location")
@NoArgsConstructor
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Ref: location.userId > users.id // many-to-one
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    // Ref: location.noteId > notes.id // many-to-one
    @ManyToOne
    @JoinColumn(name = "noteId")
    private Notes notes;

    // Ref: location.expenseId > expenses.id // many-to-one
    @ManyToOne
    @JoinColumn(name = "expenseId")
    private Expense expense;

    private String googleResponse;
    private int position;
    private boolean status;
}
