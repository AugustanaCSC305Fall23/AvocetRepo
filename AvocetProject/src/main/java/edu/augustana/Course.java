package edu.augustana;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

public class Course {
    private static List<LessonPlan> plans;
    //private String title;

    public Course() {
        this.plans = new ArrayList<LessonPlan>();
    }

    public List<LessonPlan> getPlans() {
        return plans;
    }

    public void addLessonPlan(LessonPlan plan) {
        this.plans.add(plan);
        System.out.println(plan);
    }

    public static String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(Course.class, new CourseSerializer());
        Gson gson = gsonBuilder.create();
        return gson.toJson(plans);
    }

    private static class CourseSerializer implements JsonSerializer<Course> {
        @Override
        public JsonElement serialize(Course course, Type type, JsonSerializationContext context) {
            JsonObject jsonCourse = new JsonObject();

            JsonArray plansArray = new JsonArray();
            for (LessonPlan lessonPlan : course.getPlans()) {
                JsonObject jsonLessonPlan = new JsonObject();
                jsonLessonPlan.addProperty("event", lessonPlan.getEvent());

                JsonArray cardsArray = new JsonArray();
                for (Card card : lessonPlan.getCards()) {
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


}
