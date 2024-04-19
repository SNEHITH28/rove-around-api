package com.rovearound.tripplanner.payloads;

import java.util.Date;
import com.rovearound.tripplanner.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TripDto {
	private int id;
	private User userId;
	private String tripCode;
    private String destination;
    private String googleResponse;
    private Date startDate;
    private Date endDate;
    private int createdBy;
    private Date createdOn;
    private int updatedBy;
    private Date updatedOn;
    private boolean status;

}
