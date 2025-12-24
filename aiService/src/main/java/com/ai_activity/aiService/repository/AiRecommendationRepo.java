package com.ai_activity.aiService.repository;

import com.ai_activity.aiService.model.AiRecommendationsModel;
import org.jspecify.annotations.Nullable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AiRecommendationRepo extends MongoRepository<AiRecommendationsModel, String> {
    AiRecommendationsModel findByUserIdAndActivityId(String userId, String activityId);
}
