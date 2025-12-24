package com.api_gateway.api.user;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class KeycloakUserFilter implements GlobalFilter, Ordered {

    private final UserValidationService userValidationService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return chain.filter(exchange);
        }

        Map<String, Object> userDetails = getUserDetails(token);
        if (userDetails == null) {
            return chain.filter(exchange);
        }

        String keycloakId = (String) userDetails.get("keycloakId");

        return userValidationService.validateUser(keycloakId)
                .flatMap(exists -> {
                    if (!exists) {
                        return userValidationService.registerUser(userDetails);
                    }
                    return Mono.empty();
                })
                .then(Mono.defer(() -> {
                    ServerHttpRequest mutatedRequest = exchange.getRequest()
                            .mutate()
                            .header("X-Keycloak-Id", keycloakId)
                            .build();

                    return chain.filter(
                            exchange.mutate().request(mutatedRequest).build()
                    );
                }));
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private Map<String, Object> getUserDetails(String token) {
        try {
            String tokenWithoutBearer = token.replace("Bearer", "").trim();
            SignedJWT signedJWT = SignedJWT.parse(tokenWithoutBearer);
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

            return Map.of(
                    "keycloakId", claims.getStringClaim("sub"),
                    "firstName", claims.getStringClaim("given_name"),
                    "lastName", claims.getStringClaim("family_name"),
                    "email", claims.getStringClaim("email")
            );
        } catch (Exception e) {
            return null;
        }
    }
}
