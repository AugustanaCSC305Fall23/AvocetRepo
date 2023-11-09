package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class NewLessonPlanController {
    @FXML
    private ComboBox<String> eventFilterComboBox;
    @FXML
    private ComboBox<String> levelFilterComboBox;
    @FXML
    private ComboBox<String> genderFilterComboBox;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ComboBox<String> keyWordsComboBox;
    @FXML
    private TextField searchBox;
    @FXML
    private GridPane cardsGrid;
    @FXML
    private ScrollPane cardsGridScrollPane;
    @FXML
    private VBox cardsGridVbox;
    @FXML
    private GridPane lessonPlanGrid;
    @FXML
    private Button addEventButton;
    private double width;
    private Course course;
    private PrinterJob job;
    @FXML
    private Button printButton;

    @FXML
    void initialize() {

        job = PrinterJob.createPrinterJob();
        FilterController.comboBoxInitializer(eventFilterComboBox, "event");
        FilterController.comboBoxInitializer(genderFilterComboBox, "gender");
        FilterController.comboBoxInitializer(levelFilterComboBox, "level");

        course = new Course();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        cardsGrid.setPrefWidth(width/2);
        lessonPlanGrid.setMinWidth(width/2);
        lessonPlanGrid.setVgap(10);
        displayCards(App.cardCollection);
    }

    private void searchInitiator() {
        cardsGrid.getChildren().clear();
        displayCards(FilterController.cardGridHandler(eventFilterComboBox, genderFilterComboBox, levelFilterComboBox, searchBox));
    }

    @FXML
    void searchFiltering() {searchInitiator();}

    @FXML
    void eventFiltering(ActionEvent event) {searchInitiator();}

    @FXML
    void genderFiltering(ActionEvent event) {searchInitiator();}

    @FXML
    void levelFiltering(ActionEvent event) {searchInitiator();}

    private void displayCards(List<Card> cardList) {
        int numRows = cardsGrid.getRowConstraints().size();
        int numCols = 3;
        cardsGridVbox.setPrefWidth(width/2);
        cardsGrid.setVgap(10);
        int col = 0;
        int row = 0;
        for (Card myCard : cardList) {
            ImageView imageView = new ImageView(myCard.getImageThumbnail());
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            Button cardButton = new Button();
            cardButton.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            cardButton.getStyleClass().add("cardPopup");
            Card clickCard = myCard;
            cardButton.setOnAction(event -> CardInfo.displayPopup(clickCard));
            cardButton.setGraphic(imageView);
            Button addButton = new Button("Add");
            addButton.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            addButton.getStyleClass().add("buttonWhite");
            addButton.setPrefWidth(220);
            addButton.setOnAction(event -> addCardToPlan(myCard));

            //addButton.setStyle("-fx-background-color: #ff6e4e");
            VBox cardVbox = new VBox(cardButton, addButton);
            cardsGrid.add(cardVbox, col, row);

            col++;
            if (col >= numCols) {
                col = 0;
                row++;
            }
        }
    }

    @FXML
    private void addLessonPlan() {

        LessonPlan plan = new LessonPlan(lessonPlanGrid.getRowCount());

        //cardsHBox.getChildren().add(eventComboBox);
        plan.getHBox().setSpacing(10);
        Button deleteButton = new Button("Delete");
        deleteButton.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        deleteButton.getStyleClass().add("buttonOrange");
        deleteButton.setOnAction(event -> deletePlan(plan));
        HBox topHB = new HBox(plan.getEventComboBox(), deleteButton);
        plan.getVBox().getChildren().add(topHB);
        plan.getVBox().getChildren().add(plan.getHBox());
        course.addLessonPlan(plan);
        lessonPlanGrid.add(plan.getVBox(), 0, plan.getIndex());

    }
    @FXML
    private void addCardToPlan(Card card) {
        for (LessonPlan plan : course.getPlans()) {
            if (plan.getEvent().equals(card.getEvent()) && (!plan.getCards().contains(card)) ){
                plan.addCard(card);
                displayPlanCards(plan);
            }
        }
    }

    private void displayPlanCards(LessonPlan plan) {
        int numCards = plan.getCards().size();
        Card newCard = plan.getCards().get(numCards - 1);
        ImageView imageView = new ImageView(newCard.getImageThumbnail());
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        plan.getHBox().getChildren().add(imageView);
    }

    private void deletePlan(LessonPlan plan) {
        lessonPlanGrid.getChildren().remove(plan.getVBox());
        course.getPlans().remove(plan);
    }
    @FXML
    private void printLessonPlan() {
        Node node = App.scene.getRoot();
    }

}




