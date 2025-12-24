package com.activity.activity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.activity.activity.model.ActivityModel;

@Repository
public interface ActivityRepo extends MongoRepository<ActivityModel, String>{
	
}
