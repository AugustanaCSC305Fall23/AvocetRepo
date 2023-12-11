package edu.augustana;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Manages lesson plans, card groups, and card interactions.
 */
public class LessonPlanManager {

    /**
     * Adds a card group to the lesson plan and updates the UI.
     *
     * @param plan           The lesson plan.
     * @param cardGroup      The card group to be added.
     * @param lessonPlanGrid The GridPane representing the lesson plan UI.
     * @param revert         A flag indicating whether to revert changes.
     */
    public static void addCardGroup(LessonPlan plan, CardGroup cardGroup, GridPane lessonPlanGrid, boolean revert, boolean fromOpenedFile) {
        cardGroup.getHBox().setSpacing(10);
        Button deleteButton = new Button("Delete");
        deleteButton.getStylesheets().add(LessonPlanManager.class.getResource("style.css").toExternalForm());
        deleteButton.getStyleClass().add("buttonOrange");
        deleteButton.setOnAction(event -> deleteCardGroup(cardGroup, plan, lessonPlanGrid));
        ComboBox<String> eventComboBox = new ComboBox<>();
        eventComboBox.promptTextProperty().set("Select Event");
        eventComboBox.getStylesheets().add(LessonPlanManager.class.getResource("style.css").toExternalForm());
        eventComboBox.getStyleClass().add("combo-boxWhite");
        FilterController.comboBoxInitializer(eventComboBox, "event");
        if (fromOpenedFile) {
            eventComboBox.setValue(cardGroup.getEvent());
            plan.getSelectedCardGroups().add(cardGroup.getEvent());
        }
            eventComboBox.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (plan.getSelectedCardGroups().contains(newValue)) {
                        showAlert();
                        if (cardGroup.getCards().isEmpty()) {
                            eventComboBox.cancelEdit();
                            deleteCardGroup(cardGroup, plan, lessonPlanGrid);
                        } else {
                            plan.getSelectedCardGroups().remove(oldValue);
                            eventComboBox.setValue(oldValue);
                            deleteCardGroup(cardGroup, plan, lessonPlanGrid);
                        }
                    } else {
                        if (!revert) {
                            plan.getSelectedCardGroups().remove(oldValue);
                            cardGroup.setEvent(newValue);
                            cardGroup.getCards().clear();
                            for (HBox hb : cardGroup.getCgHBoxes()) {
                                hb.getChildren().clear();

                            }

                        }
                        plan.getSelectedCardGroups().add(newValue);
                    }
                }
            });


        HBox topHB = new HBox(eventComboBox, deleteButton);
        cardGroup.getVBox().getChildren().add(topHB);
        cardGroup.getVBox().getChildren().add(cardGroup.getHBox());
        plan.addCardGroup(cardGroup);
        lessonPlanGrid.add(cardGroup.getVBox(), 0, cardGroup.getIndex());

    }

    /**
     * Deletes a card group from the lesson plan and updates the UI.
     *
     * @param cardGroup      The card group to be deleted.
     * @param plan           The lesson plan.
     * @param lessonPlanGrid The GridPane representing the lesson plan UI.
     */
    public static void deleteCardGroup(CardGroup cardGroup, LessonPlan plan, GridPane lessonPlanGrid) {
        lessonPlanGrid.getChildren().remove(cardGroup.getVBox());
        plan.getSelectedCardGroups().remove(cardGroup.getEvent());
        plan.getCardGroups().remove(cardGroup);
        lessonPlanGrid.getChildren();
    }


    /**
     * Displays cards in the lesson plan UI for a specific card group.
     *
     * @param cardGroup The card group for which to display cards.
     */
    public static void displayPlanCards(CardGroup cardGroup) {

        int numCards = cardGroup.getCards().size();
        Card newCard = cardGroup.getCards().get(numCards - 1);
        ImageView imageView = new ImageView(newCard.getImageThumbnail());
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        Button deleteButton = new Button("Remove");
        VBox cardVBox = new VBox(imageView, deleteButton);
        System.out.println(cardGroup.getCards().size());
        if ((cardGroup.getCards().size()-1) % 3 == 0) {
            HBox hbox = new HBox();
            cardGroup.getVBox().getChildren().add(hbox);
            cardGroup.setHb(hbox);

        }
        cardGroup.getHBox().getChildren().add(cardVBox);
        deleteButton.setOnAction(e -> LessonPlanManager.deleteCardFromCardGroup(newCard, cardGroup, cardVBox));

    }

    /**
     * Deletes a card from a card group and updates the UI.
     *
     * @param newCard   The card to be deleted.
     * @param cardGroup The card group from which to delete the card.
     * @param cardVBox  The VBox representing the card in the UI.
     */
    public static void deleteCardFromCardGroup(Card newCard, CardGroup cardGroup, VBox cardVBox) {
                cardGroup.getCards().remove(newCard);
                for (HBox hb : cardGroup.getCgHBoxes()) {
                    if (hb.getChildren().contains(cardVBox)) {
                        hb.getChildren().remove(cardVBox);
                    }
                }

                ChangesMadeManager.setChangesMade(true);
    }

    /**
     * Shows an alert with a specific message.
     */
    public static void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Message");
        alert.setHeaderText("This event already exists in the course");
        alert.showAndWait();
    }
}

