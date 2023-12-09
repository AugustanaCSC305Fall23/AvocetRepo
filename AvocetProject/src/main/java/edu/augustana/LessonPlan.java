package edu.augustana;


import java.io.IOException;
import java.lang.reflect.Type;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

public class LessonPlan {

    private static List<CardGroup> cardGroups;

    private List<String> selectedCardGroups;

    public static String title;

    private List<Card> favoriteCards;



    public LessonPlan() {
        this.cardGroups = new ArrayList<CardGroup>();
        this.selectedCardGroups = new ArrayList<>();
        this.favoriteCards = new ArrayList<>();


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

    public static LessonPlan fromJson(String filePath) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Path.of(filePath)));
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(LessonPlan.class, new CourseSerializer());
        Gson gson = gsonBuilder.create();
        return gson.fromJson(jsonContent, LessonPlan.class);
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

    private static class CourseDeserializer implements JsonDeserializer<LessonPlan> {
        @Override
        public LessonPlan deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String code = jsonObject.get("code").getAsString();
            String event = jsonObject.get("event").getAsString();
            String category = jsonObject.get("category").getAsString();
            String title = jsonObject.get("title").getAsString();
            String imageFileName = jsonObject.get("imageFileName").getAsString();
            String gender = jsonObject.get("gender").getAsString();
            String modelSex = jsonObject.get("modelSex").getAsString();
            String level = jsonObject.get("level").getAsString();
            String equipment = jsonObject.get("equipment").getAsString();
            String keywords = jsonObject.get("keywords").getAsString();
            String packName = jsonObject.get("packName").getAsString();
    //        Card card = new Card(code, event, category, title, imageFileName, gender, modelSex, level, equipment, keywords, packName);
            return null;
        }
    }

    public List<String> getSelectedCardGroups() {
        return selectedCardGroups;
    }

    public static String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public List<Card> getFavoriteCards() {
        return favoriteCards;
    }
}
