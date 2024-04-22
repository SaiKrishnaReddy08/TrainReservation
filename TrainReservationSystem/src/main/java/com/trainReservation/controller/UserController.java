package com.trainReservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.trainReservation.entity.User;
import com.trainReservation.exceptions.AuthenticationDetailException;
import com.trainReservation.service.UserService;

import jakarta.validation.Valid;


@SessionAttributes({"userId", "bookingDetail", "isLoggedIn"})
@Controller
public class UserController {
	@Autowired
	public UserService userService;
	
	@GetMapping("register")
	public String showRegisterPage(@ModelAttribute("user") User user) {
		return "register";
	}
	
	@PostMapping("register")
	public String registerUser(@Valid User user, BindingResult result, ModelMap model) {
		String view = "";
		boolean validDetails = true;
		if(result.hasErrors()) {
			view =  "register";
			validDetails = false;
		}
		if(userService.isPhoneRegistered(user.getPhoneNumber())) {
			model.put("registeredPhone","Phone Number already Registered.");
			view = "register";
			validDetails = false;
		}
		if(userService.isUserIdTaken(user.getUserId())) {
			model.put("idTaken","User ID is not available.");
			view = "register";
			validDetails = false;
		}
		if(validDetails) {
			userService.registerNewUser(user);
			view= "redirect:login";
		}
		
		return view;
	}
	
	@GetMapping("login")
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping("login")
	public String login(@RequestParam String userId, @RequestParam String password, ModelMap model) {
		String view = "";
		try {
			
			if(userId.isBlank()) {
				throw new AuthenticationDetailException("User ID is EMPTY");
			}
			if(password.isBlank()) {
				throw new AuthenticationDetailException("Password is EMPTY");
			}
			
			if(userService.authenticate(userId,password)) {
				model.put("userId", userId);
				model.put("isLoggedIn", true);
				view = "redirect:home";
			}
			else {
				throw new AuthenticationDetailException("Wrong User ID or Password.");
			}
		}catch(AuthenticationDetailException e) {
			model.put("invalid", e.getMessage());
			view = "login";
		}
		return view;
		
	}
	
	@GetMapping("/logout")
	public String logout(ModelMap model) {
		model.put("userId", "-");
		model.put("isLoggedIn", false);
		return "redirect:home";
	}
}