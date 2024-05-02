package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.payloads.BudgetDto;

public interface BudgetService {
	BudgetDto createBudget(BudgetDto budget);
	BudgetDto updateBudget(BudgetDto budget, Integer budgetId);
	BudgetDto getBudget(Integer budgetId);
	List<BudgetDto> getAllBudgets();
	BudgetDto getBudgetByTripId(Integer tripId);
	void deleteBudget(Integer budgetId);

}
