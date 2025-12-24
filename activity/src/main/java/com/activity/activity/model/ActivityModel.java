package com.activity.activity.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityModel {
	@Id
	private String id; //mongo id should be string unlikely of mysql
	private String userId; //keycloak id not userId
	private String activityType;
	private Integer caloriesBurned;
	private Integer duration;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
