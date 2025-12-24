package com.fitness.userservice.controller;
import java.util.Map;

import com.fitness.userservice.repository.UserRepo;
import com.fitness.userservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
    
    @Autowired
    private UserRepo userRepo;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody Map<String, Object> request){
		return ResponseEntity.ok(userService.register(request));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable Long userId){
        System.out.println("hello from user-service");
        return ResponseEntity.ok(userRepo.findById(userId));
	}

    @GetMapping("/isvalid")
    public ResponseEntity<?> isValidUser(HttpServletRequest httpServletRequest) {
        String keycloakId = httpServletRequest.getHeader("X-Keycloak-Id");
        if (keycloakId == null || keycloakId.isBlank()) {
            return ResponseEntity.badRequest().body("Missing X-Keycloak-Id header");
        }
        boolean exists = userRepo.existsByKeycloakId(keycloakId);
        return ResponseEntity.ok(exists);
    }
}
