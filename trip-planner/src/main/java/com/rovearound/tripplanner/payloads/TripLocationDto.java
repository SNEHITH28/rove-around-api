package com.rovearound.tripplanner.payloads;

import com.rovearound.tripplanner.entities.Expense;
import com.rovearound.tripplanner.entities.TripNotes;
import com.rovearound.tripplanner.entities.Trip;
import com.rovearound.tripplanner.entities.User;


public class TripLocationDto {
	private int id;
    private User user;
    private TripNotes notes;
    private Expense expense;
    private String googleResponse;
    private int position;
    private Trip trip;
    public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip tripId) {
		this.trip = tripId;
	}
	private boolean status;
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
	public TripNotes getNotes() {
		return notes;
	}
	public void setNotes(TripNotes notes) {
		this.notes = notes;
	}
	public Expense getExpense() {
		return expense;
	}
	public void setExpense(Expense expense) {
		this.expense = expense;
	}
	public String getGoogleResponse() {
		return googleResponse;
	}
	public void setGoogleResponse(String googleResponse) {
		this.googleResponse = googleResponse;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
