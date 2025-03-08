package com.meditrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meditrack.model.Symptoms;
import com.meditrack.repository.Symptoms_Repository;

@Service
public class Symptoms_Service {

	@Autowired
	Symptoms_Repository symptoms_Repository;
	
	public List<Symptoms> symptoms(){
		return symptoms_Repository.findAll();
	}
	
}
