package com.rovearound.tripplanner.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tripId")
    private Trip trip;

    private Date date;
    private boolean status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
    
}
