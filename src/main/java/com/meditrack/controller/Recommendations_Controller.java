package com.meditrack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.meditrack.model.Medicine;
import com.meditrack.model.Symptoms;
import com.meditrack.model.User;
import com.meditrack.service.Medicine_Service;
import com.meditrack.service.Symptoms_Service;

@Controller
@SessionAttributes({ "user", "Names", "symptoms" })
public class Recommendations_Controller {

	@Autowired
	Symptoms_Service service;

	@Autowired
	Medicine_Service medicine_Service;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/recommendation")
	public String recommendations(@ModelAttribute User user, Model model) {

		List<Symptoms> list = service.symptoms();

		model.addAttribute("user", user);
		model.addAttribute("symptoms", list);

		List<Medicine> list2 = medicine_Service.GetMedicine();

		model.addAttribute("medicines", list2);
		return "recommendation";
	}

	@GetMapping("/show_medicine")
	public String show_medicine() {

		return "show_medicine";

	}

	@PostMapping("/show_medicine")
	public String getRecommendations(@RequestParam List<String> symptoms, Model model) {
	    String pythonApiUrl = "http://localhost:5000/predict";
	    
	    // Create request body with symptoms list
	    Map<String, Object> requestBody = new HashMap<>();
	    requestBody.put("symptoms", symptoms);

	    try {
	        Map<String, String> response = restTemplate.postForObject(
	            pythonApiUrl, 
	            requestBody, 
	            Map.class
	        );
	        model.addAttribute("data", response);
	    } catch (Exception e) {
	        model.addAttribute("error", "Unable to get recommendations. Please try again later.");
	    }
	    
	    return "ShowResult";
	}

	@GetMapping("/buyMedicine")
	public String BuyMedicine() {
		return "buyMedicine";
	}

	@PostMapping("/buyMedicine")
	public String BuyMedicine(Model model) {
		return "buyMedicine";
	}

}
