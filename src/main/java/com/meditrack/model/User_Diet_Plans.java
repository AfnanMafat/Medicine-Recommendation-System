package com.meditrack.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_diet_plans")

public class User_Diet_Plans {

	public User_Diet_Plans() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User_Diet_Plans(Long userDietPlanId, User user, Diet_Plan dietPlan, Date startDate, Date endDate) {
		super();
		this.userDietPlanId = userDietPlanId;
		this.user = user;
		this.dietPlan = dietPlan;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Long getUserDietPlanId() {
		return userDietPlanId;
	}
	public void setUserDietPlanId(Long userDietPlanId) {
		this.userDietPlanId = userDietPlanId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Diet_Plan getDietPlan() {
		return dietPlan;
	}
	public void setDietPlan(Diet_Plan dietPlan) {
		this.dietPlan = dietPlan;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDietPlanId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "diet_plan_id")
    private Diet_Plan dietPlan;

    private Date startDate;
    private Date endDate;
	
}



