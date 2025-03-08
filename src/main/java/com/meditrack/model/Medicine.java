package com.meditrack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicines")

public class Medicine {
	
	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Medicine(Long medicineId, String name, String description, String dosageInstructions, String sideEffects) {
		super();
		this.medicineId = medicineId;
		this.name = name;
		this.description = description;
		this.dosageInstructions = dosageInstructions;
		this.sideEffects = sideEffects;
	}
	public Long getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
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
	public String getDosageInstructions() {
		return dosageInstructions;
	}
	public void setDosageInstructions(String dosageInstructions) {
		this.dosageInstructions = dosageInstructions;
	}
	public String getSideEffects() {
		return sideEffects;
	}
	public void setSideEffects(String sideEffects) {
		this.sideEffects = sideEffects;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineId;

    @Column(nullable = false)
    private String name;

    private String description;
    private String dosageInstructions;
    private String sideEffects;
	
}


