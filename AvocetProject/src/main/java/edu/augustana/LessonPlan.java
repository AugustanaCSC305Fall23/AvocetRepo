package edu.augustana;


import java.io.*;
import java.lang.reflect.Type;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.*;
import javafx.scene.layout.GridPane;

public class LessonPlan  {

    private static ArrayList<CardGroup> cardGroups;

    private List<String> selectedCardGroups;

    public static String title;

    private static GridPane lessonPlanGrid;



    public LessonPlan() {
        this.cardGroups = new ArrayList<CardGroup>();
        this.selectedCardGroups = new ArrayList<>();

    }

    public static ArrayList<CardGroup> getCardGroups() {
        return cardGroups;
    }

    public void addCardGroup(CardGroup cardGroup) {
        this.cardGroups.add(cardGroup);
        System.out.println(cardGroup);
    }

    public void saveToFile(File logFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Lesson Plan", gson.toJsonTree(cardGroups));
        String serializedLessonPlan = gson.toJson(jsonObject);
        PrintWriter writer = new PrintWriter(new FileWriter(logFile));
        writer.println(serializedLessonPlan);
        writer.close();

    }

    public static LessonPlan loadFromFile(File openedFile) throws IOException {
        FileReader jsonContent = new FileReader(openedFile);
        Gson gson = new Gson();
        return gson.fromJson(jsonContent, LessonPlan.class);
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
}
