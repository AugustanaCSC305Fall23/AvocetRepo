package edu.augustana;


import java.io.*;
import java.lang.reflect.Type;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.*;
import javafx.scene.layout.GridPane;


/**
 * Represents a lesson plan consisting of card groups.
 */


public class LessonPlan  {

    /** The list of card groups in the lesson plan. */
    private ArrayList<CardGroup> cardGroups;


    /** The list of selected card groups. */
    private List<String> selectedCardGroups;

    /** The title of the lesson plan. */
    public static String title;

    private static GridPane lessonPlanGrid;


    private List<Card> favoriteCards;


    /**
     * Creates a new lesson plan with an empty list of card groups and selected card groups.
     */
    public LessonPlan() {
        this.cardGroups = new ArrayList<CardGroup>();
        this.selectedCardGroups = new ArrayList<>();
        this.favoriteCards = new ArrayList<>();
    }


    /**
     * Gets the list of card groups in the lesson plan.
     *
     * @return The list of card groups.
     */
    public ArrayList<CardGroup> getCardGroups() {
        return cardGroups;
    }

    /**
     * Adds a card group to the lesson plan.
     *
     * @param cardGroup The card group to be added.
     */
    public void addCardGroup(CardGroup cardGroup) {
        this.cardGroups.add(cardGroup);
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
    public String getTitle() {
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

    public List<Card> getFavoriteCards() {
        return favoriteCards;
    }
}
