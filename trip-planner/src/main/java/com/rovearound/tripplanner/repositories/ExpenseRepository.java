package com.rovearound.tripplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rovearound.tripplanner.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}
