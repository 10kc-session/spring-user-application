	package com.example.demo.controller;
	
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.User;
import com.example.demo.service.AuthService;

import jakarta.servlet.http.HttpSession;
	
	@RestController
	@RequestMapping("/api/auth")
	public class AuthController {
	    @Autowired
	    private AuthService authService;
	    
	    @PostMapping("/register")
	    public String register(@RequestBody User user) {
	        authService.register(user);
	        return "Registration successful";
	    }
	
	    @PostMapping("/login")
	    public String login(@RequestBody User user, HttpSession session) {
	        User loggedInUser = authService.login(user.getUsername(), user.getPassword());
	        session.setAttribute("user", loggedInUser);
	        return "Login successful";
	    }
	    
	    @GetMapping("/user")
	    public User getUser(HttpSession session) {
	        User user = (User) session.getAttribute("user");
	        if (user == null) {
	            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not logged in");
	        }
	        return user;
	    }
	    
	
	    @PostMapping("/logout")
	    public String logout(HttpSession session) {
	        session.invalidate();
	        return "Logged out successfully";
	    }
	}