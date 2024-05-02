package com.rovearound.tripplanner.payloads;

import java.util.Date;

import com.rovearound.tripplanner.entities.ItineraryLocation;
import com.rovearound.tripplanner.entities.User;

public class ItineraryLocationNotesDto {
	private int id;
    private User user;
    private ItineraryLocation itineraryLocation;
	private String note;
    private boolean status;
    private int createdBy;
    private Date createdOn;
    private int updatedBy;
    private Date updatedOn;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ItineraryLocation getItineraryLocation() {
		return itineraryLocation;
	}
	public void setItineraryLocation(ItineraryLocation itineraryLocation) {
		this.itineraryLocation = itineraryLocation;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
    
    

}
