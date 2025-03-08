package com.meditrack.model;

import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medical_history")

public class Medical_History {
	
	public Medical_History() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medical_History(Long historyId, User user, Symptoms symptom, Medicine medicine, Timestamp dateTaken) {
		super();
		this.historyId = historyId;
		this.user = user;
		this.symptom = symptom;
		this.medicine = medicine;
		this.dateTaken = dateTaken;
	}

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Symptoms getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptoms symptom) {
		this.symptom = symptom;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Timestamp getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(Timestamp dateTaken) {
		this.dateTaken = dateTaken;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "symptom_id")
    private Symptoms symptom;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    private Timestamp dateTaken;

}


