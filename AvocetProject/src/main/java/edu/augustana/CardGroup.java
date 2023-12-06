package edu.augustana;



import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CardGroup {


    private List<Card> cards;

    private String event;


    private transient HBox hb;

    private transient static ComboBox<String> eventComboBox;
    private transient int index;
    private transient VBox vb;


    public CardGroup(int index) {
        this.cards = new ArrayList<>();
        this.event = "";
        this.vb = new VBox();
        this.hb = new HBox();
        this.eventComboBox = new ComboBox<>();
        for (Card c : App.cardCollection) {
            if (!eventComboBox.getItems().contains(c.getEvent())) {
                eventComboBox.getItems().add(c.getEvent());
            }

        }
        this.eventComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                cards.clear();
                hb.getChildren().clear();
            }
        });
        this.index = index;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) { cards.remove(card);}
    public void setEvent(String evt) {
        this.event = evt;
    }

    public void deleteCard(Card card) { cards.remove(card);}

    public String getEvent() {
        return event;

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

    public void setHb(HBox hb) {
        this.hb = hb;
    }
}
