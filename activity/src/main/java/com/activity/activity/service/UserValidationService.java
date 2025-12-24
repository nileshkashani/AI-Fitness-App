package com.activity.activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserValidationService {
    @Autowired
    private WebClient webClient;
    public boolean validateUser(String userId){

        try {
            boolean x =  webClient.get()
                    .uri("/users/isvalid")
                    .header("X-Keycloak-Id", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            return x;
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return false;
    }
}
