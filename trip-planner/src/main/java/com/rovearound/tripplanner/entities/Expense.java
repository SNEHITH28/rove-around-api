package com.rovearound.tripplanner.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="expense")
@NoArgsConstructor
@Getter
@Setter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    // Ref: expenses.tripId > trips.id // many-to-one
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tripId")
    private Trip trip;

    // Ref: expenses.paidBy > users.id // many-to-one
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "paidBy")
    private User user;

    // Ref: expenses.categoryId > expenses_category.id // many-to-one
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "categoryId")
    private ExpensesCategory category;

    private String categoryDescription;
    private Date paidOn;
    private float amount;
    private String splitType;
    private boolean status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ExpensesCategory getCategory() {
		return category;
	}
	public void setCategory(ExpensesCategory category) {
		this.category = category;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public Date getPaidOn() {
		return paidOn;
	}
	public void setPaidOn(Date paidOn) {
		this.paidOn = paidOn;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getSplitType() {
		return splitType;
	}
	public void setSplitType(String splitType) {
		this.splitType = splitType;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
    
}
