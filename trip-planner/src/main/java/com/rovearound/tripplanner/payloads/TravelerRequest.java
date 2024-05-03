package com.rovearound.tripplanner.payloads;


public class TravelerRequest {
    private UserDto user;
    private String tripCode;
    
	
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getTripCode() {
		return tripCode;
	}
	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}
	
    

}
