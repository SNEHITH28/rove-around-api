package com.rovearound.tripplanner.payloads;


public class ItineraryLocationDto {
	private int id;
    private int userId;
    private int itineraryId;
    private String googleResponse;
    private int position;
	private boolean status;

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getItineraryId() {
		return itineraryId;
	}
	public void setItineraryId(int itineraryId) {
		this.itineraryId = itineraryId;
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
