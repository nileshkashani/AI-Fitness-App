    package com.api_gateway.api.user;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;
    import org.springframework.web.reactive.function.client.WebClient;
    import org.springframework.web.reactive.function.client.WebClientException;
    import org.springframework.web.reactive.function.client.WebClientResponseException;
    import reactor.core.publisher.Mono;
    
    import java.util.Map;
    
    @Service
    public class UserValidationService {
        @Autowired
        private WebClient webClient;
        public Mono<Boolean> validateUser(String keycloakId){
            System.out.println(keycloakId);
            return webClient.get()
                    .uri("/users/isvalid")
                    .header("X-Keycloak-Id", keycloakId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .onErrorResume(WebClientResponseException.class, e -> {
                        if(e.getStatusCode() == HttpStatus.NOT_FOUND)
                            return Mono.error(new RuntimeException("user not found!"));
                        else if(e.getStatusCode() == HttpStatus.BAD_REQUEST){
                            return Mono.error(new RuntimeException("invalid user: "+keycloakId));
                        }
                        return Mono.error(new RuntimeException("something else went wrong!"));
                    });
        }
    
        public Mono<String> registerUser(Map<String, Object> request) {
            System.out.println("hello");
            return webClient.post()
                    .uri("/users/register")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(String.class)
                    .onErrorResume(WebClientResponseException.class, e -> {
                        if(e.getStatusCode() == HttpStatus.BAD_REQUEST){
                            return Mono.error(new RuntimeException("Bad request by user: "+e.getMessage()));
                        }
                        return Mono.error(new RuntimeException("something else went wrong: "+e.getMessage()));
                    });
        }
    }
