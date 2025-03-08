package com.meditrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.meditrack.model.Symptoms;
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
	
	@GetMapping("/recommendation")
	public String recommendations(@ModelAttribute User user, Model model) {
		
		List<Symptoms> list = service.symptoms();

		model.addAttribute("user", user);
		model.addAttribute("symptoms",list);
		return "recommendation";
	}
}
