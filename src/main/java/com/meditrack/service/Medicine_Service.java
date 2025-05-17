package com.meditrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meditrack.model.Medicine;
import com.meditrack.repository.Medicine_Repository;

@Service
public class Medicine_Service {

	@Autowired
	Medicine_Repository medicine_Repository;
	
	public List<Medicine> GetMedicine() {
		
		List<Medicine> Medicine = medicine_Repository.findAll();
		
		return Medicine;
	}
}
