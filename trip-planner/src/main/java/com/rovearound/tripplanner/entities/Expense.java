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
@Table(name="expense")
@NoArgsConstructor
@Getter
@Setter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    // Ref: expenses.tripId > trips.id // many-to-one
    @ManyToOne
    @JoinColumn(name = "tripId")
    private Trip trip;

    // Ref: expenses.paidBy > users.id // many-to-one
    @ManyToOne
    @JoinColumn(name = "paidBy")
    private User paidBy;

    // Ref: expenses.categoryId > expenses_category.id // many-to-one
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private ExpensesCategory category;

    private String categoryDescription;
    private Date paidOn;
    private float amount;
    private String splitType;
    private boolean status;
}
