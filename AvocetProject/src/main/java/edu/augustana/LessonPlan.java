package edu.augustana;


import java.lang.reflect.Type;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

public class LessonPlan {

    private static List<Course> plans;

    private List<String> selectedEvents;

    public LessonPlan() {
        this.plans = new ArrayList<Course>();
        this.selectedEvents = new ArrayList<>();

    }

    public List<Course> getPlans() {
        return plans;
    }

    public void addLessonPlan(Course plan) {
        this.plans.add(plan);
        System.out.println(plan);
    }

    public static String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(LessonPlan.class, new CourseSerializer());
        Gson gson = gsonBuilder.create();
        return gson.toJson(plans);
    }

    private static class CourseSerializer implements JsonSerializer<LessonPlan> {
        @Override
        public JsonElement serialize(LessonPlan course, Type type, JsonSerializationContext context) {
            JsonObject jsonCourse = new JsonObject();
            JsonArray plansArray = new JsonArray();
            for (Course course1 : course.getPlans()) {
                JsonObject jsonLessonPlan = new JsonObject();
                jsonLessonPlan.addProperty("event", course1.getEvent());
                JsonArray cardsArray = new JsonArray();
                for (Card card : course1.getCards()) {
                    JsonObject jsonCard = new JsonObject();
                    jsonCard.addProperty("code", card.getCode());
                    cardsArray.add(jsonCard);
                }
                
                jsonLessonPlan.add("cards", cardsArray);
                plansArray.add(jsonLessonPlan);
            }

            jsonCourse.add("lessonPlans", plansArray);
            return jsonCourse;
        }
    }

    public List<String> getSelectedEvents() {
        return selectedEvents;
    }
}
