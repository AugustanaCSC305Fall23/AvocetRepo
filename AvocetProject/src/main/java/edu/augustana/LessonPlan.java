package edu.augustana;


import java.awt.*;
import java.lang.reflect.Type;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

public class LessonPlan {

    private static List<CardGroup> cardGroups;

    private List<String> selectedCardGroups;

    public static String title;



    public LessonPlan() {
        this.cardGroups = new ArrayList<CardGroup>();
        this.selectedCardGroups = new ArrayList<>();


    }

    public List<CardGroup> getCardGroups() {
        return cardGroups;
    }

    public void addCardGroup(CardGroup cardGroup) {
        this.cardGroups.add(cardGroup);
        System.out.println(cardGroup);
    }

    public static String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(LessonPlan.class, new CourseSerializer());
        Gson gson = gsonBuilder.create();
        return gson.toJson(cardGroups);
    }

    private static class CourseSerializer implements JsonSerializer<LessonPlan> {
        @Override
        public JsonElement serialize(LessonPlan course, Type type, JsonSerializationContext context) {
            JsonObject jsonCourse = new JsonObject();
            JsonArray plansArray = new JsonArray();
            for (CardGroup cardGroup1 : course.getCardGroups()) {
                JsonObject jsonLessonPlan = new JsonObject();
                jsonLessonPlan.addProperty("event", cardGroup1.getEvent());
                JsonArray cardsArray = new JsonArray();
                for (Card card : cardGroup1.getCards()) {
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

    public List<String> getSelectedCardGroups() {
        return selectedCardGroups;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }
}
