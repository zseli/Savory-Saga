package com.jwtauthentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jwtauthentication.proxy.AdminProxy;

@RestController
@RequestMapping("/admin")
public class AdminController {	
	
	@Autowired
	private AdminProxy adminProxy;
	
	@GetMapping("/adminHome")
	public ResponseEntity<String> adminOnly(){
		return ResponseEntity.ok("Hello from the admin only url");
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers(){
		return adminProxy.getAllUsers();
	}
	    
	@PutMapping("/changeRole/{username}")
	 public ResponseEntity<?> changeUserRole(@PathVariable String username) {
	     return adminProxy.changeUserRole(username);
	}
}
