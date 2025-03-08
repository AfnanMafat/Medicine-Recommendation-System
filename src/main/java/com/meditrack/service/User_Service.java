package com.meditrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meditrack.model.User;
import com.meditrack.repository.User_Repository;

@Service
public class User_Service {
	
	@Autowired
	User_Repository repository;
	
	public void addUser(User user) {
		repository.save(user);
	}
	
	public User FindUserByUserName(String Name) {
		return repository.findByUsername(Name);
	}
	
	public void UpdateUser(User user) {
		repository.save(user);
	}
}
