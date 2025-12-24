package com.fitness.userservice.service;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.userservice.model.UserModel;
import com.fitness.userservice.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserModel register(Map<String, Object> request) {
        if (request == null) {
            return null;
        }
        UserModel user = new UserModel();
        user.setFirstName((String) request.get("firstName"));
        user.setLastName((String) request.get("lastName"));
        user.setEmail((String) request.get("email"));
        user.setPhoneNo((String) request.get("phoneNo"));
        user.setKeycloakId((String) request.get("keycloakId"));

        if (user.getFirstName() == null || user.getEmail() == null) {
            throw new IllegalArgumentException("firstName and email are required");
        }

        UserModel isExistUser = userRepo.findByEmail(user.getEmail());
        if(isExistUser != null && isExistUser.getEmail().equals(user.getEmail())){
            System.out.println("user already exists");
            return isExistUser;
        }

        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        return userRepo.save(user);
    }
}
