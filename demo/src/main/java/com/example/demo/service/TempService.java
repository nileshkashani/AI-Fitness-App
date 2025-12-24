package com.example.demo.service;

import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class TempService {

        public void evaluate(){
            String str = " {\n" +
                    "  \"candidates\": [\n" +
                    "    {\n" +
                    "      \"content\": {\n" +
                    "        \"parts\": [\n" +
                    "          {\n" +
                    "            \"text\": \"```json\\n{\\n  \\\"analysis\\\": {\\n    \\\"overall\\\": \\\"This was a good, consistent run of 45 minutes, indicating solid commitment to cardiovascular exercise. The calorie burn of 350 suggests a moderate intensity, which is excellent for building an aerobic base and improving overall cardiovascular health without excessive strain. It's a productive duration for fitness.\\\",\\n    \\\"pace\\\": \\\"Given the duration (45 minutes) and calorie burn (350 calories), the pace was likely in the moderate-to-easy range. Assuming an average person, this calorie burn might correspond to approximately 3.5 miles, which would put the pace around 12:50 minutes per mile. This indicates a comfortable, conversational pace, ideal for building endurance, maintaining consistency, and facilitating recovery. Without distance data, this is an estimation, but it suggests a sustainable effort rather than a high-speed sprint.\\\",\\n    \\\"heartRate\\\": \\\"No heart rate data was provided. However, a 350-calorie burn over 45 minutes strongly suggests the heart rate was primarily in a moderate aerobic zone (e.g., 60-70% of maximum heart rate). This zone is highly effective for improving cardiovascular endurance, promoting fat metabolism, and enhancing overall stamina. Tracking heart rate in future activities would provide precise insights into your intensity and allow for more targeted training.\\\",\\n    \\\"caloriesBurned\\\": \\\"Burning 350 calories in 45 minutes is a good expenditure, contributing positively to weight management goals and overall energy balance. It demonstrates a significant output for the duration, indicating a sustained effort throughout the run. This is a respectable amount for a moderate run and signifies an effective workout.\\\"\\n  },\\n  \\\"improvements\\\": [\\n    {\\n      \\\"area\\\": \\\"Cardiovascular Fitness & Speed\\\",\\n      \\\"recommendation\\\": \\\"To further enhance your cardiovascular system and improve running speed, consider incorporating interval training or 'Fartlek' (speed play) runs once a week. This involves alternating short bursts of higher-intensity running with periods of slower, recovery jogging. This challenges your heart and lungs, making your body more efficient at using oxygen and improving your ability to sustain faster paces.\\\"\\n    },\\n    {\\n      \\\"area\\\": \\\"Endurance & Stamina\\\",\\n      \\\"recommendation\\\": \\\"To build greater endurance, gradually increase the duration of one of your weekly runs by 5-10 minutes (e.g., to 50 or 55 minutes) or aim to cover a slightly longer distance while maintaining a comfortable pace. This progressive overload will help your body adapt to longer efforts and improve your stamina over time.\\\"\\n    },\\n    {\\n      \\\"area\\\": \\\"Performance Tracking & Insights\\\",\\n      \\\"recommendation\\\": \\\"To gain a clearer understanding of your performance and progress, utilize a GPS running watch or a smartphone app that can track metrics like distance, exact pace (per mile/km), and ideally, heart rate. This data will provide invaluable insights into your training zones, allowing you to tailor future workouts more effectively and see tangible improvements.\\\"\\n    },\\n    {\\n      \\\"area\\\": \\\"Running Economy & Injury Prevention\\\",\\n      \\\"recommendation\\\": \\\"Integrate 1-2 sessions of strength training per week, focusing on key muscle groups for runners: legs, glutes, and core. Exercises such as squats, lunges, planks, glute bridges, and calf raises can improve your running form, increase power, and significantly reduce the risk of common running-related injuries.\\\"\\n    }\\n  ],\\n  \\\"suggestions\\\": [\\n    {\\n      \\\"workout\\\": \\\"Fartlek Run (Speed Play)\\\",\\n      \\\"description\\\": \\\"After a 5-10 minute easy warm-up jog, transition into 25-30 minutes of 'speed play.' During this period, pick various landmarks ahead (e.g., a tree, a lamppost, a specific house) and increase your pace to a comfortably hard effort until you reach it. Once you pass the landmark, slow down to an easy jogging or walking pace for recovery until you spot another landmark or feel ready to pick up the pace again. Vary the length of your fast and slow segments. This unstructured approach helps build speed and endurance without the strictness of traditional intervals. Finish with a 5-minute cool-down jog and dynamic stretching.\\\"\\n    },\\n    {\\n      \\\"workout\\\": \\\"Progressive Endurance Run\\\",\\n      \\\"description\\\": \\\"Aim for a slightly longer duration, such as 50-55 minutes. Start with a 10-minute easy warm-up jog. For the next 30-35 minutes, maintain a steady, conversational pace – you should be able to speak in full sentences. For the final 10 minutes of your main running segment, gradually increase your pace to a 'comfortably hard' effort (you can speak in short sentences, but it feels challenging). Focus on maintaining good form as you speed up. Conclude with a 5-minute cool-down walk/jog and static stretching.\\\"\\n    }\\n  ],\\n  \\\"safety\\\": [\\n    \\\"Always perform a proper warm-up (5-10 minutes of light cardio and dynamic stretches) before your run and a cool-down (5-10 minutes of easy walking/jogging followed by static stretches) afterward to prevent injury.\\\",\\n    \\\"Listen to your body; if you experience sharp or persistent pain, stop or reduce intensity. Pushing through significant pain can lead to more serious injuries.\\\",\\n    \\\"Ensure you wear appropriate running shoes that fit well and are not excessively worn out. Replace shoes every 300-500 miles or every 3-6 months, depending on your usage.\\\",\\n    \\\"Stay hydrated. Drink water before, during (especially if running longer than 60 minutes or in hot conditions), and after your run.\\\",\\n    \\\"Be aware of your surroundings, especially if running outdoors. Consider carrying identification, informing someone of your route, and wearing reflective gear if running in low light conditions.\\\"\\n  ]\\n}\\n```\"\n" +
                    "          }\n" +
                    "        ],\n" +
                    "        \"role\": \"model\"\n" +
                    "      },\n" +
                    "      \"finishReason\": \"STOP\",\n" +
                    "      \"index\": 0\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"usageMetadata\": {\n" +
                    "    \"promptTokenCount\": 217,\n" +
                    "    \"candidatesTokenCount\": 1268,\n" +
                    "    \"totalTokenCount\": 3611,\n" +
                    "    \"promptTokensDetails\": [\n" +
                    "      {\n" +
                    "        \"modality\": \"TEXT\",\n" +
                    "        \"tokenCount\": 217\n" +
                    "      }\n" +
                    "    ],\n" +
                    "    \"thoughtsTokenCount\": 2126\n" +
                    "  },\n" +
                    "  \"modelVersion\": \"gemini-2.5-flash\",\n" +
                    "  \"responseId\": \"qWgtafWDDcLHjuMPydbxqAs\"\n" +
                    "}\n";

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(str);
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

}
