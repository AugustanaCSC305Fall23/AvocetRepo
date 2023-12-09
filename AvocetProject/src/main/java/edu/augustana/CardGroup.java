package edu.augustana;



import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a group of cards with a common event.
 */
public class CardGroup {

    /** The list of cards in the group. */
    private List<Card> cards;

    /** The event associated with the group of cards. */
    private String event;

    /** The horizontal box (HBox) containing the cards. */
    private transient HBox hb;

    /** The static combo box for selecting events. */
    private transient static ComboBox<String> eventComboBox;

    /** The index of the card group. */
    private transient int index;

    /** The vertical box (VBox) containing the cards. */
    private transient VBox vb;
    private List<HBox> cgHBoxes;

    /**
     * Constructs a CardGroup with the specified index.
     *
     * @param index The index of the card group.
     */
    public CardGroup(int index) {
        this.cgHBoxes = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.event = "";
        this.vb = new VBox();
        this.hb = new HBox();
        cgHBoxes.add(hb);
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

    /**
     * Adds a card to the group.
     *
     * @param card The card to be added.
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Removes a card from the group.
     *
     * @param card The card to be removed.
     */
    public void removeCard(Card card) { cards.remove(card);}

    /**
     * Sets the event associated with the group.
     *
     * @param evt The event to be set.
     */
    public void setEvent(String evt) {
        this.event = evt;
    }

    /**
     * Deletes a card from the group.
     *
     * @param card The card to be deleted.
     */
    public void deleteCard(Card card) { cards.remove(card);}

    /**
     * Gets the event associated with the group.
     *
     * @return The event associated with the group.
     */
    public String getEvent() {
        return event;
    }

    /**
     * Gets the list of cards in the group.
     *
     * @return The list of cards in the group.
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Gets the horizontal box (HBox) containing the cards.
     *
     * @return The HBox containing the cards.
     */
    public HBox getHBox() {
        return hb;
    }

    /**
     * Gets the combo box for selecting events.
     *
     * @return The combo box for selecting events.
     */
    public ComboBox<String> getEventComboBox() {
        return eventComboBox;
    }

    /**
     * Gets the index of the card group.
     *
     * @return The index of the card group.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Gets the vertical box (VBox) containing the cards.
     *
     * @return The VBox containing the cards.
     */
    public VBox getVBox() {
        return vb;
    }

    /**
     * Sets the horizontal box (HBox) containing the cards.
     *
     * @param hb The HBox to be set.
     */
    public void setHb(HBox hb) {
        this.hb = hb;
        cgHBoxes.add(hb);
    }

    public List<HBox> getCgHBoxes() {
        return cgHBoxes;
    }
}
