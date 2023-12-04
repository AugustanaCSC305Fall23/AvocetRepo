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


public class LessonPlanManager {


    public static void addCardGroup(LessonPlan plan, CardGroup cardGroup, GridPane lessonPlanGrid, boolean revert) {
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

        eventComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (plan.getSelectedCardGroups().contains(newValue)) {
                    showAlert();
                    if (cardGroup.getCards().isEmpty()) {
                        eventComboBox.cancelEdit();
                    } else {
                        plan.getSelectedCardGroups().remove(oldValue);
                        eventComboBox.setValue(oldValue);
                        deleteCardGroup(cardGroup, plan, lessonPlanGrid); // Move the deletion logic here
                    }
                } else {
                    if (!revert) {
                        cardGroup.setEvent(newValue);
                        cardGroup.getCards().clear();
                        cardGroup.getHBox().getChildren().clear();
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

    public static void deleteCardGroup(CardGroup cardGroup, LessonPlan plan, GridPane lessonPlanGrid) {
        lessonPlanGrid.getChildren().remove(cardGroup.getVBox());
        plan.getSelectedCardGroups().remove(cardGroup.getEvent());
        plan.getCardGroups().remove(cardGroup);
        lessonPlanGrid.getChildren();
    }


    public static void displayPlanCards(CardGroup cardGroup, Button deleteButton) {

        int numCards = cardGroup.getCards().size();
        Card newCard = cardGroup.getCards().get(numCards - 1);
        ImageView imageView = new ImageView(newCard.getImageThumbnail());
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        cardGroup.getHBox().getChildren().add(imageView);
        cardGroup.getHBox().getChildren().add(deleteButton);
    }

    public static void deleteCardFromPlan(Card newCard, CardGroup cardGroup, Button deleteButton) {
                cardGroup.getCards().remove(newCard);
        for (Node node : cardGroup.getHBox().getChildren()) {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                if (imageView.getImage().equals(newCard.getImageThumbnail())) {
                    cardGroup.getHBox().getChildren().remove(imageView);
                    cardGroup.getHBox().getChildren().remove(deleteButton);
                    break;
                }
            }
        }
                cardGroup.getHBox().getChildren().re;
                displayPlanCards(cardGroup, deleteButton);

    }

    public static void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Message");
        alert.setHeaderText("This event already exists in the course");
        alert.showAndWait();
    }
}

