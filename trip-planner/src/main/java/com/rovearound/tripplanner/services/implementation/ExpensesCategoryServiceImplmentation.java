package com.rovearound.tripplanner.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rovearound.tripplanner.entities.ExpensesCategory;
import com.rovearound.tripplanner.exceptions.ResourceNotFoundException;
import com.rovearound.tripplanner.payloads.ExpensesCategoryDto;
import com.rovearound.tripplanner.repositories.ExpensesCategoryRepository;
import com.rovearound.tripplanner.services.ExpensesCategoryService;

@Service
public class ExpensesCategoryServiceImplmentation  implements ExpensesCategoryService {

	@Autowired
	private ExpensesCategoryRepository expensesCategoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ExpensesCategoryDto createExpensesCategory(ExpensesCategoryDto expensesCategoryDto) {
		ExpensesCategory expensesCategory = this.dtoToExpensesCategory(expensesCategoryDto);
		ExpensesCategory savedExpensesCategory = this.expensesCategoryRepository.save(expensesCategory);
		return this.expensesCategoryToDto(savedExpensesCategory);
	}

	@Override
	public ExpensesCategoryDto updateExpensesCategory(ExpensesCategoryDto expensesCategoryDto, Integer expensesCategoryId) {
		ExpensesCategory expensesCategory = this.expensesCategoryRepository.findById(expensesCategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("ExpensesCategory", "Id", expensesCategoryId));
		
		expensesCategory.setId(expensesCategoryDto.getId());
//		expensesCategory.setAmount(expensesCategoryDto.getAmount());
//		expensesCategory.setTrip(expensesCategoryDto.getTrip());
//		expensesCategory.setUser(expensesCategoryDto.getUser());
//		expensesCategory.setPaidOn(expensesCategoryDto.getPaidOn());
//		expensesCategory.setSplitType(expensesCategory.getSplitType());
//		expensesCategory.setCategory(expensesCategoryDto.getCategory());

		ExpensesCategory updatedExpensesCategory = this.expensesCategoryRepository.save(expensesCategory);
		ExpensesCategoryDto updatedExpensesCategoryDto = this.expensesCategoryToDto(updatedExpensesCategory);
				
		return updatedExpensesCategoryDto;
	}

	@Override
	public ExpensesCategoryDto getExpensesCategory(Integer expensesCategoryId) {
		ExpensesCategory expensesCategory = this.expensesCategoryRepository.findById(expensesCategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("ExpensesCategory", "Id", expensesCategoryId));
		
		return this.expensesCategoryToDto(expensesCategory);
	}

	@Override
	public List<ExpensesCategoryDto> getAllExpensesCategorys() {
		List<ExpensesCategory> expensesCategorys = this.expensesCategoryRepository.findAll();
		
		List<ExpensesCategoryDto> expensesCategoryDtos = expensesCategorys.stream().map(expensesCategory -> this.expensesCategoryToDto(expensesCategory)).collect(Collectors.toList());
		return expensesCategoryDtos;
	}
	
	private ExpensesCategory dtoToExpensesCategory(ExpensesCategoryDto expensesCategoryDto) {
		ExpensesCategory expensesCategory = this.modelMapper.map(expensesCategoryDto, ExpensesCategory.class);
		return expensesCategory;
	}
	
	private ExpensesCategoryDto expensesCategoryToDto(ExpensesCategory expensesCategory) {
		ExpensesCategoryDto expensesCategoryDto = this.modelMapper.map(expensesCategory, ExpensesCategoryDto.class);
		return expensesCategoryDto;
	}
}
