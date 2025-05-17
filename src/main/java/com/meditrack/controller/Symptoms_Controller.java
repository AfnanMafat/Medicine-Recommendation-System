package com.meditrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.meditrack.model.User;
import com.meditrack.service.Symptoms_Service;

@Controller
@SessionAttributes("user")
public class Symptoms_Controller {

	@Autowired
	Symptoms_Service service;
	
	@GetMapping("/xyz")
	public String Fired(@ModelAttribute User user, Model model) {
		
		model.addAttribute("user", user);
		
		return "xyz";
	}
	
}
