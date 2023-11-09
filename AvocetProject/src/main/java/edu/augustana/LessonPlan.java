package edu.augustana;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class LessonPlan {
    public List<Card> cards;
    //private String Title;
    private String event;

    private transient HBox hb;

    private transient static ComboBox<String> eventComboBox;
    private transient int index;
    private transient VBox vb;

    public LessonPlan(int index) {
        this.cards = new ArrayList<Card>();
        this.event = "";
        this.vb = new VBox();
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

    public static String getEvent() {
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

    public VBox getVBox() {
        return vb;
    }

}
