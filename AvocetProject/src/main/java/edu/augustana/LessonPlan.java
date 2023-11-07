package edu.augustana;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class LessonPlan {
    private List<Card> cards;
    //private String Title;
    private String event;

    private HBox hb;

    private ComboBox<String> eventComboBox;
    private int index;

    public LessonPlan(int index) {
        this.cards = new ArrayList<Card>();
        this.event = "";
        this.hb = new HBox();
        this.eventComboBox = new ComboBox<String>();
        for (Card c : App.cardCollection) {
            if (!eventComboBox.getItems().contains(c.getEvent())) {
                eventComboBox.getItems().add(c.getEvent());
            }

        }
        this.index = index;
    }
    public void addCard(Card card) {
        cards.add(card);
    }

    public void setEvent() {
        this.event = eventComboBox.getValue();
    }

    public String getEvent() {
        return eventComboBox.getValue();
    }

    public List<Card> getCards() {
        return cards;
    }
    public HBox getHBox() {
        return hb;
    }

    public ComboBox<String> getEventComboBox() {
        return eventComboBox;
    }

    public int getIndex() {
        return index;
    }

}
