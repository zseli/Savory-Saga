package com.jwtauthentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jwtauthentication.entity.AuthenticationResponse;
import com.jwtauthentication.entity.User;
import com.jwtauthentication.service.AuthenticationService;

@RestController
public class AuthenticationController {
	private final AuthenticationService authService;

	public AuthenticationController(AuthenticationService authService) {
		this.authService = authService;
	}
	
	@GetMapping("/home")
	public ResponseEntity<String> home(){
		return ResponseEntity.ok("Hello welcome to authentication service! Please login to continue!");
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User request) {
	    try {
	        AuthenticationResponse response = authService.register(request);
	        return ResponseEntity.ok(response);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
	    }
	}
	
	@PostMapping("/login")
		public ResponseEntity<AuthenticationResponse> login(@RequestBody User request){
			return ResponseEntity.ok(authService.authenticate(request));
		}
	
	@GetMapping("/getUser")
	public ResponseEntity<?> getUser(){	
		AuthenticationResponse user=authService.getCurrentUser();
		return ResponseEntity.ok(user);
	}
}
