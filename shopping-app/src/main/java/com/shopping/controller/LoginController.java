package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.service.AccountService;

@Controller
public class LoginController {
	
	@Autowired
	private AccountService userService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String LoggedIn() {
		return "redirect:/";
	}
	
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registrationForm(ModelMap model, @RequestParam String name, @RequestParam("username") String username, @RequestParam("password1") String password1, @RequestParam("password2") String password2) {
		System.out.println(username + "," + password1 + "," + password2);
		if (! password1.equals(password2)) {
			model.put("errorMessage", "Password does not match");
			return "registration";
		}
		String fb = userService.saveUser(name, username, password1);
		model.put("errorMessage", fb);
		return "registration";
	}

}
