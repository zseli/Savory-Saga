package com.jwtauthentication.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="ADMIN-SERVICE",url="http://localhost:8086/admin")
public interface AdminProxy {
	
	 @GetMapping("/users")
	 ResponseEntity<?> getAllUsers();

	 @PutMapping("/changeRole/{username}")
	 ResponseEntity<String> changeUserRole(@PathVariable String username);

}
