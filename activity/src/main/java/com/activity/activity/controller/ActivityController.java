package com.activity.activity.controller;

import java.util.Map;

import com.activity.activity.repository.ActivityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.activity.activity.service.ActivityService;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {
	private final ActivityService activityService;
    private final ActivityRepo activityRepo;

	@PostMapping("/add")
	public ResponseEntity<?> addActivity(@RequestBody Map<String, String> request, @RequestHeader("X-User-ID") String userId){
		return ResponseEntity.ok(activityService.addActivity(request, userId));
	}
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllActivities(){
        System.out.println("hello");
        return ResponseEntity.ok(activityRepo.findAll());
    }

}
