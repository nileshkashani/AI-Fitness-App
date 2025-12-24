package com.fitness.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitness.userservice.model.UserModel;

public interface UserRepo extends JpaRepository<UserModel, Long>{

    Boolean existsByKeycloakId(String keycloakId);

    UserModel findByEmail(String email);
}
