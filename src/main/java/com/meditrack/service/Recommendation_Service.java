package com.meditrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meditrack.repository.Recommendation_Repository;

@Service
public class Recommendation_Service {
	
	@Autowired
	Recommendation_Repository recommendation_Repository;
	
	
}
