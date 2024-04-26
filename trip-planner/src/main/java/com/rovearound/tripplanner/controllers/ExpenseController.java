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
import com.rovearound.tripplanner.payloads.ExpenseDto;
import com.rovearound.tripplanner.services.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expense/")
@CrossOrigin(origins = "http://localhost:4200")
public class ExpenseController {
	@Autowired
	private ExpenseService expenseService;

	@PostMapping("/add")
	public ResponseEntity<ExpenseDto> createExpense(@Valid @RequestBody ExpenseDto expenseDto) {
		ExpenseDto createdExpenseDto = this.expenseService.createExpense(expenseDto);
		return new ResponseEntity<>(createdExpenseDto, HttpStatus.CREATED);
	}

	@PutMapping("/{expenseId}")
	public ResponseEntity<ExpenseDto> updateExpense(@Valid @RequestBody ExpenseDto expenseDto, @PathVariable("expenseId") Integer id) {
		ExpenseDto updatedExpense = this.expenseService.updateExpense(expenseDto, id);
		return ResponseEntity.ok(updatedExpense);
	}

	@PostMapping("/{expenseId}")
	public ResponseEntity<ApiResponse> deleteExpense(@PathVariable("expenseId") Integer id) {
		this.expenseService.deleteExpense(id);
		return new ResponseEntity<>(new ApiResponse("expense Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ExpenseDto>> getAllExpenses() {
		return ResponseEntity.ok(this.expenseService.getAllExpenses());
	}

	@GetMapping("/{expenseId}")
	public ResponseEntity<ExpenseDto> getTrip(@PathVariable Integer expenseId) {
		return ResponseEntity.ok(this.expenseService.getExpense(expenseId));
	}

}
