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
    public static void addLessonPlan(LessonPlan course, Course plan, GridPane lessonPlanGrid, boolean revert) {
        plan.getHBox().setSpacing(10);
        Button deleteButton = new Button("Delete");
        deleteButton.getStylesheets().add(LessonPlanManager.class.getResource("style.css").toExternalForm());
        deleteButton.getStyleClass().add("buttonOrange");
        deleteButton.setOnAction(event -> deletePlan(plan, course, lessonPlanGrid));
        ComboBox<String> eventComboBox = new ComboBox<>();
        eventComboBox.promptTextProperty().set("Select Event");
        eventComboBox.getStylesheets().add(LessonPlanManager.class.getResource("style.css").toExternalForm());
        eventComboBox.getStyleClass().add("combo-boxWhite");
        FilterController.comboBoxInitializer(eventComboBox, "event");

        eventComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (course.getSelectedEvents().contains(newValue)) {
                    showAlert();
                    if (plan.getCards().isEmpty()) {
                        eventComboBox.cancelEdit();
                    } else {
                        course.getSelectedEvents().remove(oldValue);
                        eventComboBox.setValue(oldValue);
                        deletePlan(plan, course, lessonPlanGrid); // Move the deletion logic here
                    }
                } else {
                    if (!revert) {
                        plan.setEvent(newValue);
                        plan.getCards().clear();
                        plan.getHBox().getChildren().clear();
                    }
                    course.getSelectedEvents().add(newValue);
                }
            }
        });
        HBox topHB = new HBox(eventComboBox, deleteButton);

        plan.getVBox().getChildren().add(topHB);
        plan.getVBox().getChildren().add(plan.getHBox());
        course.addLessonPlan(plan);
        lessonPlanGrid.add(plan.getVBox(), 0, plan.getIndex());
    }

    public static void deletePlan(Course plan, LessonPlan course, GridPane lessonPlanGrid) {
        lessonPlanGrid.getChildren().remove(plan.getVBox());
        course.getSelectedEvents().remove(plan.getEvent());
        course.getPlans().remove(plan);
    }

    public static void displayPlanCards(Course plan, GridPane lessonPlanGrid) {
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

