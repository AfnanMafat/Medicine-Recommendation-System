package com.meditrack.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;

//@RestController
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class PredictionController {

    private final String PYTHON_API_URL = "http://localhost:5000/predict";
    Map<String, String> pythonResponse;
   

    @PostMapping("/predict")
    public ResponseEntity<Map<String, String>> predict(@RequestBody Map<String, String> request) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> pythonRequest = new HashMap<>();
        pythonRequest.put("symptoms", request.get("symptoms")); 

        pythonResponse = restTemplate.postForObject(
            PYTHON_API_URL, 
            pythonRequest, 
            Map.class
        );
        
        System.out.println(request.get("symptoms"));
        System.out.println(pythonResponse);

        return ResponseEntity.ok(pythonResponse);
    }
    

    
    @GetMapping("/ShowResult")
    public String ShowResult(Model model,HttpSession session) {
    	
    	model.addAttribute("data",pythonResponse);
    	
    	return "ShowResult";
    	
    }
}
