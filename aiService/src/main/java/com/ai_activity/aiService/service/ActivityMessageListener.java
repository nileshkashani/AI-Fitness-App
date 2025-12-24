package com.ai_activity.aiService.service;

import com.ai_activity.aiService.model.ActivityModel;
import com.ai_activity.aiService.model.AiRecommendationsModel;
import com.ai_activity.aiService.repository.AiRecommendationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityMessageListener {
    private final AiRecommendationRepo aiRecommendationRepo;
    private final GetRecommendations getRecommendations;
    @KafkaListener(
            topics = "${kafka.topic.name}",
            groupId = "activity-processor-group",
            containerFactory = "activityModelKafkaListenerContainerFactory"
    )
    public void processActivity(ActivityModel message) {
        log.info("message received from user: {}", message.getUserId());
        AiRecommendationsModel aiRecommendationsModel = getRecommendations.generateRecommendation(message);
        aiRecommendationRepo.save(aiRecommendationsModel);
    }

}
