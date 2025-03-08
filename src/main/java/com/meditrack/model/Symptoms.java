package com.meditrack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "symptoms")

public class Symptoms {

	public Symptoms() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Symptoms(Long symptomId, String name, String description) {
		super();
		this.symptomId = symptomId;
		this.name = name;
		this.description = description;
	}

	public Long getSymptomId() {
		return symptomId;
	}

	public void setSymptomId(Long symptomId) {
		this.symptomId = symptomId;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long symptomId;

	@Column(nullable = false)
	private String name;

	private String description;

}
