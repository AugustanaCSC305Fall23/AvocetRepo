package edu.augustana;


import java.io.IOException;
import java.lang.reflect.Type;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

/**
 * Represents a lesson plan consisting of card groups.
 */
public class LessonPlan {

    /** The list of card groups in the lesson plan. */
    private static List<CardGroup> cardGroups;

    /** The list of selected card groups. */
    private List<String> selectedCardGroups;

    /** The title of the lesson plan. */
    public static String title;

    /**
     * Creates a new lesson plan with an empty list of card groups and selected card groups.
     */
    public LessonPlan() {
        this.cardGroups = new ArrayList<CardGroup>();
        this.selectedCardGroups = new ArrayList<>();
    }

    /**
     * Gets the list of card groups in the lesson plan.
     *
     * @return The list of card groups.
     */
    public List<CardGroup> getCardGroups() {
        return cardGroups;
    }

    /**
     * Adds a card group to the lesson plan.
     *
     * @param cardGroup The card group to be added.
     */
    public void addCardGroup(CardGroup cardGroup) {
        this.cardGroups.add(cardGroup);
        System.out.println(cardGroup);
    }

    /**
     * Converts the lesson plan to a JSON-formatted string.
     *
     * @return The JSON-formatted string representing the lesson plan.
     */
    public static String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(LessonPlan.class, new CourseSerializer());
        Gson gson = gsonBuilder.create();
        return gson.toJson(cardGroups);
    }

    /**
     * Creates a LessonPlan object from a JSON file.
     *
     * @param filePath The path to the JSON file.
     * @return The LessonPlan object created from the JSON file.
     * @throws IOException If an I/O error occurs.
     */
    public static LessonPlan fromJson(String filePath) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Path.of(filePath)));
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(LessonPlan.class, new CourseSerializer());
        Gson gson = gsonBuilder.create();
        return gson.fromJson(jsonContent, LessonPlan.class);
    }

    /**
     * Serializes the LessonPlan object to JSON format.
     */
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

    /**
     * Deserializes the JSON representation of a LessonPlan object.
     */
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
            Card card = new Card(code, event, category, title, imageFileName, gender, modelSex, level, equipment, keywords, packName);
            return null;
        }
    }

    /**
     * Gets the list of selected card groups.
     *
     * @return The list of selected card groups.
     */
    public List<String> getSelectedCardGroups() {
        return selectedCardGroups;
    }

    /**
     * Gets the title of the lesson plan.
     *
     * @return The title of the lesson plan.
     */
    public static String getTitle() {
        return title;
    }

    /**
     * Sets the title of the lesson plan.
     *
     * @param newTitle The new title to set.
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }
}
