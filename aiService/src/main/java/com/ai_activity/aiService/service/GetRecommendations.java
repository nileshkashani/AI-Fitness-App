package com.ai_activity.aiService.service;

import com.ai_activity.aiService.model.ActivityModel;
import com.ai_activity.aiService.model.AiRecommendationsModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetRecommendations {
    private final GeminiService geminiService;

    public AiRecommendationsModel generateRecommendation(ActivityModel activityModel){
        String prompt = getPrompt(activityModel);
        String output = geminiService.getRecommendations(prompt);
        log.info("recommendation from AI {}", output);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(output);
        System.out.println(root);
        JsonNode second = root.get("candidates")
                .get(0)
                .get("content")
                .get("parts")
                .get(0)
                .get("text");
        String text = second
                .asString()
                .replace("```json\\n", "")
                .replace("\\n```", "")
                .replace("```json", "")
                .replace("```", "")
                .trim();
        System.out.println(text);

        JsonNode suggestionsJson = mapper.readTree(text);
        JsonNode analysisNode = suggestionsJson.path("analysis");
        StringBuilder analysis = new StringBuilder();
        evaluateAnalysis(analysisNode, analysis, "overall", "overall: ");
        evaluateAnalysis(analysisNode, analysis, "pace", "pace: ");
        evaluateAnalysis(analysisNode, analysis, "heartRate", "heartRate: ");
        evaluateAnalysis(analysisNode, analysis, "caloriesBurned", "caloriesBurned: ");
        System.out.println(analysis);


        JsonNode improvementsNode = suggestionsJson.path("improvements");
        List<String> improvements = evaluateImprovements(improvementsNode);
        System.out.println(improvements);

        JsonNode suggestionsNode = suggestionsJson.path("suggestions");
        List<String> suggestions = evaluateSuggestions(suggestionsNode);
        System.out.println(suggestions);


        JsonNode safetyNode = suggestionsJson.path("safety");
        List<String> safety = evaluateSafety(safetyNode);
        System.out.println(safety);

        return AiRecommendationsModel.builder()
                .activityId(activityModel.getId())
                .userId(activityModel.getUserId())
                .activityType(activityModel.getActivityType())
                .improvements(improvements)
                .recommendation(analysis.toString())
                .suggestions(suggestions)
                .safety(safety)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private List<String> evaluateSafety(JsonNode safetyNode) {
        List<String> safetys = new ArrayList<>();
        if(safetyNode.isArray()){
            safetyNode.forEach(safety -> {
                safetys.add(safety.asString());
            });
        }
        return safetys.isEmpty() ? List.of("no safeties provided by AI"): safetys;
    }

    private List<String> evaluateSuggestions(JsonNode suggestionsNode) {
        List<String> suggestions = new ArrayList<>();
        if(suggestionsNode.isArray()){
            suggestionsNode.forEach(suggestion -> {
                String workout = suggestion.get("workout").asString();
                String desc = suggestion.get("description").asString();
                suggestions.add(String.format("%s: %s", workout, desc));
            });
        }
        return suggestions.isEmpty() ? List.of("no suggestions provided by AI"): suggestions;
    }

    private List<String> evaluateImprovements(JsonNode improvementsNode) {
        List<String> improvements = new ArrayList<>();
        if(improvementsNode.isArray()){
            improvementsNode.forEach(improvement -> {
                String area = improvement.get("area").asString();
                String recommendation = improvement.get("recommendation").asString();
                improvements.add(String.format("%s: %s", area, recommendation));
            });
        }
        return improvementsNode.isEmpty() ? List.of("no improvements provided by AI"): improvements;

    }

    private void evaluateAnalysis(JsonNode analysisNode, StringBuilder analysis, String key, String prefix) {
        if(!analysisNode.path(key).isMissingNode()){
            analysis.append(prefix)
                    .append(analysisNode.path(key).asString())
                    .append("\n");
        }
    }


    private String getPrompt(ActivityModel activityModel) {
        String prompt =  """
            Analyze this fitness activity and provide detailed recommendations:
            
            {
              "analysis": {
                "overall": "Overall analysis here",
                "pace": "Pace analysis here",
                "caloriesBurned": "Calories analysis here"
              },
              "improvements": [
                {
                  "area": "Area name",
                  "recommendation": "Detailed recommendation"
                }
              ],
              "suggestions": [
                {
                  "workout": "Workout name",
                  "description": "Detailed workout description"
                }
              ],
              "safety": [
                "Safety point 1",
                "Safety point 2"
              ]
            }
            
            Analyze this activity:
            Activity Type: %s
            Duration: %d minutes
            Calories Burned: %d
            
            Provide detailed analysis focusing on performance, improvements, next workout suggestions, and safety advice.
            Ensure the response follows the EXACT JSON format shown above.
            """;

        return String.format(
                prompt,
                activityModel.getActivityType(),
                activityModel.getDuration(),
                activityModel.getCaloriesBurned()
        );
    }
}

