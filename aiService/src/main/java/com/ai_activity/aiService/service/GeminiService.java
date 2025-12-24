package com.ai_activity.aiService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiService {
    private final WebClient webClient;

    @Value("${gemini.API_KEY}")
    private String geminiApiKey;

    @Value("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent")
    private String geminiUrl;

    public String getRecommendations(String details){
        Map<String, Object> request = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[]  {
                                Map.of("text", details)
                        })
                }
        );
        return webClient.post()
                .uri(geminiUrl)
                .header("Content-Type", "application/json")
                .header("X-goog-api-key", geminiApiKey)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
