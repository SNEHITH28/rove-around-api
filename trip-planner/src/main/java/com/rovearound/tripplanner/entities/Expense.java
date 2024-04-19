package com.rovearound.tripplanner.entities;

import java.util.Date;

public class Expense {
	private int id;
    private int tripId;
    private int paidBy;
    private int categoryId;
    private String categoryDescription;
    private Date paidOn;
    private float amount;
    private String splitType;
    private boolean status;
}
