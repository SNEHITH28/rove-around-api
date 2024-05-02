package com.rovearound.tripplanner.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.Expense;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.payloads.ExpenseDto;
import com.rovearound.tripplanner.repositories.ExpenseRepository;
import com.rovearound.tripplanner.services.ExpenseService;

@Service
public class ExpenseServiceImplementation implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ExpenseDto createExpense(ExpenseDto expenseDto) {
		Expense expense = this.dtoToExpense(expenseDto);
		Expense savedExpense = this.expenseRepository.save(expense);
		return this.expenseToDto(savedExpense);
	}

	@Override
	public ExpenseDto updateExpense(ExpenseDto expenseDto, Integer expenseId) {
		Expense expense = this.expenseRepository.findById(expenseId)
				.orElseThrow(() -> new ResourceNotFoundException("Expense", "Id", expenseId));
		
		expense.setId(expenseDto.getExpenseId());
		expense.setAmount(expenseDto.getAmount());
		expense.setTrip(expenseDto.getTrip());
		expense.setUser(expenseDto.getUser());
		expense.setPaidOn(expenseDto.getPaidOn());
		expense.setSplitType(expense.getSplitType());
		expense.setCategory(expenseDto.getCategory());
		expense.setCategoryDescription(expenseDto.getCategoryDescription());
		expense.setStatus(true);

		Expense updatedExpense = this.expenseRepository.save(expense);
		ExpenseDto updatedExpenseDto = this.expenseToDto(updatedExpense);
				
		return updatedExpenseDto;
	}

	@Override
	public ExpenseDto getExpense(Integer expenseId) {
		Expense expense = this.expenseRepository.findById(expenseId)
				.orElseThrow(() -> new ResourceNotFoundException("Expense", "Id", expenseId));
		
		return this.expenseToDto(expense);
	}

	@Override
	public List<ExpenseDto> getAllExpenses() {
		List<Expense> expenses = this.expenseRepository.findAll();
		
		List<ExpenseDto> expenseDtos = expenses.stream().map(expense -> this.expenseToDto(expense)).collect(Collectors.toList());
		return expenseDtos;
	}

	@Override
	public void deleteExpense(Integer expenseId) {
		Expense expense = this.expenseRepository.findById(expenseId)
				.orElseThrow(() -> new ResourceNotFoundException("Expense", "Id", expenseId));
		
		expense.setStatus(false);
		this.expenseRepository.save(expense);

	}
	
	@Override
	public List<ExpenseDto> getExpenseByTripId(Integer tripId) {
		List<ExpenseDto> expenses = new ArrayList<>();
		this.getAllExpenses().forEach(el -> {
			if(el.getTrip().getId() == tripId && el.isStatus()) {
				expenses.add(el);
			}
		});
		return expenses;
	}
	
	private Expense dtoToExpense(ExpenseDto expenseDto) {
		Expense expense = this.modelMapper.map(expenseDto, Expense.class);
		return expense;
	}
	
	private ExpenseDto expenseToDto(Expense expense) {
		ExpenseDto expenseDto = this.modelMapper.map(expense, ExpenseDto.class);
		return expenseDto;
	}
}
