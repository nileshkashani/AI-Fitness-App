package com.activity.activity.service;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.Map;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.activity.activity.model.ActivityModel;
import com.activity.activity.repository.ActivityRepo;

@Service
@RequiredArgsConstructor
public class ActivityService {
	private final ActivityRepo activityRepo;

    private final UserValidationService userValidationService;

    private final KafkaTemplate<String, ActivityModel> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

	public ResponseEntity<?> addActivity(Map<String, String> request, String userId) {
		if(request == null) {
            return ResponseEntity.status(400).body(Map.of("success", "false",
                    "message", "request is empty"));
        }
        System.out.println(request);
        if(!userValidationService.validateUser(userId)){
            return ResponseEntity.status(400).body(Map.of("success", false,
                    "message", "not a valid user is saving activity"));
        }
		ActivityModel activityModel = ActivityModel.builder()
				.userId(userId)
				.activityType(request.get("activityType"))
				.duration(Integer.valueOf(request.get("duration")))
				.caloriesBurned(Integer.valueOf(request.get("caloriesBurned")))
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.build();
		ActivityModel activityModel1 = activityRepo.save(activityModel);
        try {
            kafkaTemplate.send(topicName, String.valueOf(activityModel1.getUserId()), activityModel1);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(Map.of("success", true, "message", "activity saved successfully", "data", activityModel1));
	}
	
}
