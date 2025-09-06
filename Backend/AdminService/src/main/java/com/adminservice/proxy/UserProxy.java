package com.adminservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="USER-SERVICE",url="http://localhost:8081/user")
public interface UserProxy {
	@GetMapping("/allUsers")
	ResponseEntity<?> getAllUsers();
	
}
