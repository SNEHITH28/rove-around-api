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

}
