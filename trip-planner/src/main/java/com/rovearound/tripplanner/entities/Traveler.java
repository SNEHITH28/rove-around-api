package com.rovearound.tripplanner.entities;


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
@Table(name="travelers")
@NoArgsConstructor
@Getter
@Setter
public class Traveler {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private int userId;
    private int tripId;
    private boolean status;
}
