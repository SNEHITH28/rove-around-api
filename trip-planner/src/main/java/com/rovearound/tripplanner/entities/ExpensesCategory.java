package com.rovearound.tripplanner.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="expenses_category")
@NoArgsConstructor
@Getter
@Setter
public class ExpensesCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String categoryType;
}
