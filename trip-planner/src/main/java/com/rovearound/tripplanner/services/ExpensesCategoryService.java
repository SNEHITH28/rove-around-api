package com.rovearound.tripplanner.services;

import java.util.List;

import com.rovearound.tripplanner.payloads.ExpensesCategoryDto;

public interface ExpensesCategoryService {
	ExpensesCategoryDto createExpensesCategory(ExpensesCategoryDto expensesCategory);
	ExpensesCategoryDto updateExpensesCategory(ExpensesCategoryDto expensesCategory, Integer expensesCategoryId);
	ExpensesCategoryDto getExpensesCategory(Integer expensesCategoryId);
	List<ExpensesCategoryDto> getAllExpensesCategorys();

}
