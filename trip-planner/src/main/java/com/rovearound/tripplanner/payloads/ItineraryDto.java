package com.rovearound.tripplanner.payloads;

import java.util.Date;

import com.rovearound.tripplanner.entities.Trip;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ItineraryDto {

	private int id;
    private int tripId;
    private Date date;
    private boolean status;
	public int getId() {
		return id;
	}
	public void setId(int itineraryId) {
		this.id = itineraryId;
	}
	
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
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
