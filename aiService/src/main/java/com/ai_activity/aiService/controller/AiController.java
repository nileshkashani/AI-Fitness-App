package com.ai_activity.aiService.controller;

import com.ai_activity.aiService.model.AiRecommendationsModel;
import com.ai_activity.aiService.repository.AiRecommendationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {
    private final AiRecommendationRepo aiRecommendationRepo;
    @GetMapping("/get/{userId}/{activityId}")
    public ResponseEntity<?> getAiRespByActivityId(@PathVariable String userId, @PathVariable String activityId){
        AiRecommendationsModel resp = aiRecommendationRepo.findByUserIdAndActivityId(userId, activityId);
        System.out.println(resp);
        return ResponseEntity.ok(resp);
    }
}
