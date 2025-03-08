package com.meditrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.meditrack.model.User;
import com.meditrack.service.User_Service;

@Controller
@SessionAttributes("user")
public class User_Controller {

	@Autowired
	User_Service service;

	@GetMapping("/signup")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "SignUp";
	}

	@PostMapping("/signup")
	public String signUp(User user) {
		service.addUser(user);
		return "loginPage";
	}

	@GetMapping("/")
	public String loginPage() {
		return "loginPage";
	}

	@GetMapping("/login")
	public String home() {
		return "homePage";
	}

	@PostMapping("/login")
	public String home(@RequestParam String username, @RequestParam String password, Model model) {

		User FindUser = service.FindUserByUserName(username);

		if ((FindUser == null) == true) {
			return "redirect:/signup";
		}

		if (FindUser.getPassword().equals(password) == false) {
			return "redirect:/loginPage";
		}

		model.addAttribute("user", FindUser);

		return "homePage";
	}

	@GetMapping("/forgot_password")
	public String forgot_password() {

		return "forgot_password";
	}

	@PostMapping("/forgot_password")
	public String forgot_password(@RequestParam String username, @RequestParam String email) {

		User FindUser = service.FindUserByUserName(username);

		if (FindUser.getUsername().equals(username) && FindUser.getEmail().equals(email)) {
			return "change_password";
		}

		return "redirect:/signup";
	}

	@GetMapping("/change_password")
	public String change_password() {
		return "change_password";
	}

	@PostMapping("/change_password")
	public String change_password(@RequestParam String username, @RequestParam String confirmPassword) {

		User FindUser = service.FindUserByUserName(username);

		FindUser.setPassword(confirmPassword);

		service.UpdateUser(FindUser);
		return "loginPage";
	}

	@GetMapping("/homePage")
	public String homePage() {

		return "homePage";
	}

	@GetMapping("/profile")
	public String profile(@ModelAttribute User user, Model model) {

		model.addAttribute("user", user);
		return "profile";
	}

	@GetMapping("/medical-history")
	public String medical_history(@ModelAttribute User user, Model model) {

		model.addAttribute("user", user);
		return "medical-history";
	}

//	@GetMapping("/recommendation")
//	public String recommendations(@ModelAttribute User user, Model model) {
//
//		model.addAttribute("user", user);
//		return "recommendation";
//	}
	
	@GetMapping("/diet-recommendation")
	public String diet_recommendations(@ModelAttribute User user, Model model) {

		model.addAttribute("user", user);
		return "diet-recommendation";
	}

	@GetMapping("/logout")
	public String logout() {

		return "logout";
	}
	
	@GetMapping("/update")
	public String update_profile() {
		return "update";
	}
	
	@PostMapping("/update")
	public String update_profile(@ModelAttribute User user,Model model) {
		
		User fiUser = service.FindUserByUserName(user.getUsername());
		
		if(fiUser != null) {
			
			fiUser.setFirstName(user.getFirstName());
			fiUser.setLastName(user.getLastName());
			fiUser.setEmail(user.getEmail());
			fiUser.setPassword(user.getPassword());
			
			service.UpdateUser(fiUser);
			
		}
		
		return "/profile_details";
	}
	
}
