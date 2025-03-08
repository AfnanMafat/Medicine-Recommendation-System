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
@Table(name = "recommendations")

public class Recommendations {

	public Recommendations() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recommendations(Long recommendationId, User user, Medicine medicine, Symptoms symptom,
			Timestamp recommendationDate, String recommendedBy) {
		super();
		this.recommendationId = recommendationId;
		this.user = user;
		this.medicine = medicine;
		this.symptom = symptom;
		this.recommendationDate = recommendationDate;
		this.recommendedBy = recommendedBy;
	}

	public Long getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(Long recommendationId) {
		this.recommendationId = recommendationId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Symptoms getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptoms symptom) {
		this.symptom = symptom;
	}

	public Timestamp getRecommendationDate() {
		return recommendationDate;
	}

	public void setRecommendationDate(Timestamp recommendationDate) {
		this.recommendationDate = recommendationDate;
	}

	public String getRecommendedBy() {
		return recommendedBy;
	}

	public void setRecommendedBy(String recommendedBy) {
		this.recommendedBy = recommendedBy;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "symptom_id")
    private Symptoms symptom;

    @Column(name = "recommendation_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp recommendationDate;

    private String recommendedBy;
    
}

