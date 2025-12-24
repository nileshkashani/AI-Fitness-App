package com.ai_activity.aiService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityModel {
	private String id;
	private String userId;
	private String activityType;
	private Integer caloriesBurned;
	private Integer duration;
	private LocalDateTime startTime;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
