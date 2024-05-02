package com.rovearound.tripplanner.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BudgetDto {
	private int id;
	private int tripId;
	private float amount;
	private boolean status;
	public int getId() {
		return id;
	}
	public void setId(int budgetId) {
		this.id = budgetId;
	}

	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

}
