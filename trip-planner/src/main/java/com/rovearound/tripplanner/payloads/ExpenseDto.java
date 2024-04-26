package com.rovearound.tripplanner.payloads;

import java.util.Date;

import com.rovearound.tripplanner.entities.ExpensesCategory;
import com.rovearound.tripplanner.entities.Trip;
import com.rovearound.tripplanner.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExpenseDto {
	private int expenseId;
    private Trip trip;
    private User user;
    private ExpensesCategory category;
    private String categoryDescription;
    private Date paidOn;
    private float amount;
    private String splitType;
    private boolean status;
}
