package com.rovearound.tripplanner.payloads;

import com.rovearound.tripplanner.entities.Trip;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BudgetDto {
	private int budgetId;
	private Trip trip;
	private float amount;
	private boolean status;

}
