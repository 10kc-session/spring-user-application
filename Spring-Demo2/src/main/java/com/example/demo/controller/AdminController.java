	package com.example.demo.controller;
	
	import java.util.Optional;
	
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
	
	import com.example.demo.model.Admin;
	import com.example.demo.repository.AdminRepository;
	
	@RestController
	@RequestMapping("/admin")
	public class AdminController {
	
	    private final AdminRepository adminRepository;
	
	    public AdminController(AdminRepository adminRepository) {
	        this.adminRepository = adminRepository;
	    }
	
	    @PostMapping("/login")
	    public String login(@RequestBody Admin admin) {
	        Optional<Admin> existingAdmin = adminRepository.findByUsername(admin.getUsername());
	        if (existingAdmin.isPresent() && existingAdmin.get().getPassword().equals(admin.getPassword())) {
	            return "Admin login successful";
	        }
	        return "Invalid admin credentials";
	    }
	}