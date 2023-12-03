package edu.augustana;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        deleteButton.setOnAction(event -> deletePlan(cardGroup, plan, lessonPlanGrid));
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
                        deletePlan(cardGroup, plan, lessonPlanGrid); // Move the deletion logic here
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

    public static void deletePlan(CardGroup plan, LessonPlan course, GridPane lessonPlanGrid) {
        lessonPlanGrid.getChildren().remove(plan.getVBox());
        course.getSelectedCardGroups().remove(plan.getEvent());
        course.getCardGroups().remove(plan);
    }

    public static void displayPlanCards(CardGroup plan, GridPane lessonPlanGrid) {
        int numCards = plan.getCards().size();
        Card newCard = plan.getCards().get(numCards - 1);
        ImageView imageView = new ImageView(newCard.getImageThumbnail());
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        plan.getHBox().getChildren().add(imageView);
    }

    public static void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Message");
        alert.setHeaderText("This event already exists in the course");
        alert.showAndWait();
    }
}

