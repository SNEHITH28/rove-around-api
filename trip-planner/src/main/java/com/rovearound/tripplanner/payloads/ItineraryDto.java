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

	private int itineraryId;
    private Trip trip;
    private Date date;
    private boolean status;
}
