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

import com.rovearound.tripplanner.payloads.ExpensesCategoryDto;
import com.rovearound.tripplanner.services.ExpensesCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expenses-category/")
@CrossOrigin(origins = "http://localhost:4200")
public class ExpensesCategoryController {

	@Autowired
	private ExpensesCategoryService expensesCategoryService;

	@PostMapping("/add")
	public ResponseEntity<ExpensesCategoryDto> createExpensesCategory(@Valid @RequestBody ExpensesCategoryDto expensesCategoryDto) {
		ExpensesCategoryDto createdExpensesCategoryDto = this.expensesCategoryService.createExpensesCategory(expensesCategoryDto);
		return new ResponseEntity<>(createdExpensesCategoryDto, HttpStatus.CREATED);
	}

	@PutMapping("/{expensesCategoryId}")
	public ResponseEntity<ExpensesCategoryDto> updateExpensesCategory(@Valid @RequestBody ExpensesCategoryDto expensesCategoryDto, @PathVariable("expensesCategoryId") Integer id) {
		ExpensesCategoryDto updatedExpensesCategory = this.expensesCategoryService.updateExpensesCategory(expensesCategoryDto, id);
		return ResponseEntity.ok(updatedExpensesCategory);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ExpensesCategoryDto>> getAllExpensesCategorys() {
		return ResponseEntity.ok(this.expensesCategoryService.getAllExpensesCategorys());
	}

	@GetMapping("/{expensesCategoryId}")
	public ResponseEntity<ExpensesCategoryDto> getTrip(@PathVariable Integer expensesCategoryId) {
		return ResponseEntity.ok(this.expensesCategoryService.getExpensesCategory(expensesCategoryId));
	}
}
