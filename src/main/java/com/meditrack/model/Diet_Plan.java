package com.meditrack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "diet_plans")

public class Diet_Plan {
	
	public Diet_Plan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Diet_Plan(Long dietPlanId, String name, String description, Integer totalCalories) {
		super();
		this.dietPlanId = dietPlanId;
		this.name = name;
		this.description = description;
		this.totalCalories = totalCalories;
	}
	public Long getDietPlanId() {
		return dietPlanId;
	}
	public void setDietPlanId(Long dietPlanId) {
		this.dietPlanId = dietPlanId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getTotalCalories() {
		return totalCalories;
	}
	public void setTotalCalories(Integer totalCalories) {
		this.totalCalories = totalCalories;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dietPlanId;

    @Column(nullable = false)
    private String name;

    private String description;
    private Integer totalCalories;

}


