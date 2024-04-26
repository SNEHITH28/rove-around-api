package com.rovearound.tripplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rovearound.tripplanner.entities.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Integer>{

}
