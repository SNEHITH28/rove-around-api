package com.rovearound.tripplanner.entities;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="item")
@NoArgsConstructor
@Getter
@Setter
public class Trip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private int userId;
    private String tripCode;
    private String destination;
    private Date startDate;
    private Date endDate;
    private int createdBy;
    private Date createdOn;
    private int updatedBy;
    private Date updatedOn;
    private boolean status;
}
