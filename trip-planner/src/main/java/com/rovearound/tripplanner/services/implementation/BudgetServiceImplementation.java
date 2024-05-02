package com.rovearound.tripplanner.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.Budget;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.payloads.BudgetDto;
import com.rovearound.tripplanner.repositories.BudgetRepository;
import com.rovearound.tripplanner.services.BudgetService;

@Service
public class BudgetServiceImplementation implements BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BudgetDto createBudget(BudgetDto budgetDto) {
		Budget budget = this.dtoToBudget(budgetDto);
		budget.setStatus(true);
		Budget savedBudget = this.budgetRepository.save(budget);
		return this.budgetToDto(savedBudget);
	}

	@Override
	public BudgetDto updateBudget(BudgetDto budgetDto, Integer budgetId) {
		Budget budget = this.budgetRepository.findById(budgetId)
				.orElseThrow(() -> new ResourceNotFoundException("Budget", "Id", budgetId));
		
		budget.setId(budgetDto.getId());
		budget.setAmount(budgetDto.getAmount());
//		budget.setTrip(budgetDto.getTrip());
		budget.setStatus(true);

		Budget updatedBudget = this.budgetRepository.save(budget);
		BudgetDto updatedBudgetDto = this.budgetToDto(updatedBudget);
				
		return updatedBudgetDto;
	}

	@Override
	public BudgetDto getBudget(Integer budgetId) {
		Budget budget = this.budgetRepository.findById(budgetId)
				.orElseThrow(() -> new ResourceNotFoundException("Budget", "Id", budgetId));
		
		return this.budgetToDto(budget);
	}

	@Override
	public List<BudgetDto> getAllBudgets() {
		List<Budget> budgets = this.budgetRepository.findAll();
		
		List<BudgetDto> budgetDtos = budgets.stream().map(budget -> this.budgetToDto(budget)).collect(Collectors.toList());
		return budgetDtos;
	}

	@Override
	public void deleteBudget(Integer budgetId) {
		Budget budget = this.budgetRepository.findById(budgetId)
				.orElseThrow(() -> new ResourceNotFoundException("Budget", "Id", budgetId));
		
		budget.setStatus(false);
		this.budgetRepository.save(budget);

	}
	
	@Override
	public BudgetDto getBudgetByTripId(Integer tripId) {
		BudgetDto budget = new BudgetDto();
		this.getAllBudgets().forEach((el)-> {
			if(el.getTripId() == tripId && el.isStatus()) {
				budget.setAmount(el.getAmount());
				budget.setId(el.getId());
				budget.setStatus(budget.isStatus());
				budget.setTripId(tripId);
			}
		});
		return budget;
	}
	
	private Budget dtoToBudget(BudgetDto budgetDto) {
		Budget budget = this.modelMapper.map(budgetDto, Budget.class);
		return budget;
	}
	
	private BudgetDto budgetToDto(Budget budget) {
		BudgetDto budgetDto = this.modelMapper.map(budget, BudgetDto.class);
		return budgetDto;
	}
}
