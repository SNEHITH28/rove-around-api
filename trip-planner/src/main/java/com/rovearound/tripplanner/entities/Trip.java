package com.rovearound.tripplanner.entities;

import java.util.Date;
import java.util.Random;

import com.rovearound.tripplanner.payloads.TripDto;

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
@Table(name = "trip")
@NoArgsConstructor
@Getter
@Setter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Ref: trip.userId > users.id // many-to-one
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String tripCode;
    private String destination;
    private String googleResponse;
    private Date startDate;
    private Date endDate;
    private int createdBy;
    private Date createdOn;
    private int updatedBy;
    private Date updatedOn;
    private boolean status;
}
