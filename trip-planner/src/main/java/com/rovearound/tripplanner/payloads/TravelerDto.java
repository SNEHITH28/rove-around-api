package com.rovearound.tripplanner.payloads;


import com.rovearound.tripplanner.entities.Trip;
import com.rovearound.tripplanner.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TravelerDto {
	private int travelerId;
    private User user;
    private Trip trip;
    private boolean status;
	public int getTravelerId() {
		return travelerId;
	}
	public void setTravelerId(int travelerId) {
		this.travelerId = travelerId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
    

}
