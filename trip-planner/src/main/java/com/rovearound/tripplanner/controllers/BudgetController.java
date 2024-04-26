package com.rovearound.tripplanner.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rovearound.tripplanner.payloads.ApiResponse;
import com.rovearound.tripplanner.payloads.BudgetDto;
import com.rovearound.tripplanner.services.BudgetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/budget/")
@CrossOrigin(origins = "http://localhost:4200")
public class BudgetController {
	@Autowired
	private BudgetService budgetService;

	@PostMapping("/add")
	public ResponseEntity<BudgetDto> createBudget(@Valid @RequestBody BudgetDto budgetDto) {
		BudgetDto createdBudgetDto = this.budgetService.createBudget(budgetDto);
		return new ResponseEntity<>(createdBudgetDto, HttpStatus.CREATED);
	}

	@PutMapping("/{budgetId}")
	public ResponseEntity<BudgetDto> updateBudget(@Valid @RequestBody BudgetDto budgetDto, @PathVariable("budgetId") Integer id) {
		BudgetDto updatedBudget = this.budgetService.updateBudget(budgetDto, id);
		return ResponseEntity.ok(updatedBudget);
	}

	@PostMapping("/{budgetId}")
	public ResponseEntity<ApiResponse> deleteBudget(@PathVariable("budgetId") Integer id) {
		this.budgetService.deleteBudget(id);
		return new ResponseEntity<>(new ApiResponse("budget Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<BudgetDto>> getAllBudgets() {
		return ResponseEntity.ok(this.budgetService.getAllBudgets());
	}

	@GetMapping("/{budgetId}")
	public ResponseEntity<BudgetDto> getTrip(@PathVariable Integer budgetId) {
		return ResponseEntity.ok(this.budgetService.getBudget(budgetId));
	}

}
