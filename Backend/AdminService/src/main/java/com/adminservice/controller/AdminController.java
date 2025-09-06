package com.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adminservice.proxy.UserProxy;
import com.adminservice.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
    private AdminService adminService;

	@Autowired
	private UserProxy userProxy;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userProxy.getAllUsers());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/changeRole/{username}")
    public ResponseEntity<String> changeUserRole(@PathVariable String username) {
        adminService.changeUserRole(username);
        return ResponseEntity.status(HttpStatus.OK).body("User role updated successfully");
    }

}

