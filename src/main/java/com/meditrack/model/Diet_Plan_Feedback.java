package com.meditrack.model;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "diet_plan_feedback")

public class Diet_Plan_Feedback {

	public Diet_Plan_Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Diet_Plan_Feedback(Long feedbackId, User user, Diet_Plan dietPlan, String feedbackText, Integer rating,
			Timestamp feedbackDate) {
		super();
		this.feedbackId = feedbackId;
		this.user = user;
		this.dietPlan = dietPlan;
		this.feedbackText = feedbackText;
		this.rating = rating;
		this.feedbackDate = feedbackDate;
	}

	public Long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
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

	public String getFeedbackText() {
		return feedbackText;
	}

	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Timestamp getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Timestamp feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "diet_plan_id")
    private Diet_Plan dietPlan;

    private String feedbackText;

    @Column(nullable = false)
    private Integer rating;

    @Column(name = "feedback_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp feedbackDate;
    
}
