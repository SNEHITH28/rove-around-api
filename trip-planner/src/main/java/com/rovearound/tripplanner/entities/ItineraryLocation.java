package com.rovearound.tripplanner.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="itinerary_location")
public class ItineraryLocation {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Ref: location.userId > users.id // many-to-one
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    // Ref: location.noteId > notes.id // many-to-one
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "noteId")
    private TripNotes notes;

    // Ref: location.expenseId > expenses.id // many-to-one
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "expenseId")
    private Expense expense;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itineraryId")
    private Itinerary itinerary;
    
    private String googleResponse;
    private int position;
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
	public Itinerary getItinerary() {
		return itinerary;
	}
	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
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
