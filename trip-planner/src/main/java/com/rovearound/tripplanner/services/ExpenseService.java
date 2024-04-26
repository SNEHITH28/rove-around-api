package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.payloads.ExpenseDto;

public interface ExpenseService {
	ExpenseDto createExpense(ExpenseDto expense);
	ExpenseDto updateExpense(ExpenseDto expense, Integer expenseId);
	ExpenseDto getExpense(Integer expenseId);
	List<ExpenseDto> getAllExpenses();
	void deleteExpense(Integer expenseId);
}
