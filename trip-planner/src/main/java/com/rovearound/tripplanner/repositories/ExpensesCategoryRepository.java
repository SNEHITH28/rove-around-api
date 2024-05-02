package com.rovearound.tripplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rovearound.tripplanner.entities.ExpensesCategory;

public interface ExpensesCategoryRepository  extends JpaRepository<ExpensesCategory, Integer> {

}
