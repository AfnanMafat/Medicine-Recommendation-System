package com.meditrack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "meals")
public class Meal {

	public Meal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Meal(Long mealId, Diet_Plan dietPlan, MealType mealType, String foodItems) {
		super();
		this.mealId = mealId;
		this.dietPlan = dietPlan;
		this.mealType = mealType;
		this.foodItems = foodItems;
	}

	public Long getMealId() {
		return mealId;
	}

	public void setMealId(Long mealId) {
		this.mealId = mealId;
	}

	public Diet_Plan getDietPlan() {
		return dietPlan;
	}

	public void setDietPlan(Diet_Plan dietPlan) {
		this.dietPlan = dietPlan;
	}

	public MealType getMealType() {
		return mealType;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}

	public String getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(String foodItems) {
		this.foodItems = foodItems;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;

    @ManyToOne
    @JoinColumn(name = "diet_plan_id")
    private Diet_Plan dietPlan;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MealType mealType;

    private String foodItems;

    public enum MealType {
        Breakfast, Lunch, Dinner, Snack
    }
    
}

