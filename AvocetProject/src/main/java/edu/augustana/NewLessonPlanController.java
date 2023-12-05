package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URL;
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
    private ComboBox<String> modelFilterComboBox;
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
    @FXML
    private Button saveButton;
    private double width;
    private LessonPlan plan;
    private PrinterJob job;
    @FXML
    private Button printButton;
    @FXML
    private BorderPane newLessonPlanBorderPane;
    private Boolean revert;

    @FXML
    private MenuItem textOnlyMenu;

    @FXML
    private MenuItem withImagesMenu;

    @FXML
    void initialize() {
        job = PrinterJob.createPrinterJob();
        FilterController.comboBoxInitializer(eventFilterComboBox, "event");
        FilterController.comboBoxInitializer(genderFilterComboBox, "gender");
        FilterController.comboBoxInitializer(levelFilterComboBox, "level");
        FilterController.comboBoxInitializer(modelFilterComboBox, "model");
        revert = false;
        plan = new LessonPlan();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        cardsGridVbox.setPrefWidth(width/2);
        cardsGrid.setPrefWidth((width/2) - 60);
        cardsGrid.setHgap(20);
        lessonPlanGrid.setPrefHeight(screenSize.getHeight());
        lessonPlanGrid.setMinWidth(width/2);
        lessonPlanGrid.setVgap(10);
        displayCards(App.cardCollection);
    }

    private void searchInitiator() {
        cardsGrid.getChildren().clear();
        displayCards(FilterController.cardGridHandler(eventFilterComboBox, genderFilterComboBox, levelFilterComboBox, modelFilterComboBox, searchBox));
    }

    @FXML
    void searchFiltering() {searchInitiator();}

    @FXML
    void eventFiltering(ActionEvent event) {searchInitiator();}

    @FXML
    void genderFiltering(ActionEvent event) {searchInitiator();}

    @FXML
    void levelFiltering(ActionEvent event) {searchInitiator();}

    @FXML
    void modelFiltering(ActionEvent event) {searchInitiator();}

    private void displayCards(List<Card> cardList) {
        int numRows = cardsGrid.getRowConstraints().size();
        int numCols = 3;
        cardsGrid.setVgap(10);
        int col = 0;
        int row = 0;
        for (Card myCard : cardList) {

            ImageView imageView = new ImageView(myCard.getImageThumbnail());
            imageView.setFitWidth(180);
            imageView.setFitHeight(180);

            ImageView maximizeIcon = new ImageView("file:src/maximizeicon.png");
            maximizeIcon.setFitHeight(10);
            maximizeIcon.setFitWidth(10);
            Button maximizeButton = new Button();
            maximizeButton.setGraphic(maximizeIcon);
            Button cardButton = new Button();
            cardButton.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            cardButton.getStyleClass().add("cardPopup");
            Card clickCard = myCard;
            cardButton.setOnAction(event -> addCardToPlan(myCard));
            cardButton.setGraphic(imageView);

            maximizeButton.setOnAction(event -> CardInfo.displayPopup(clickCard));

            //maximizeButton.setStyle("-fx-background-color: #ff6e4e");
            VBox cardVbox = new VBox(maximizeButton, cardButton);
            cardsGrid.add(cardButton, col, row);
            cardsGrid.add(maximizeButton, col, row);
            cardsGrid.setValignment(maximizeButton, javafx.geometry.VPos.TOP);
            cardsGrid.setHalignment(maximizeButton, javafx.geometry.HPos.RIGHT);


            col++;
            if (col >= numCols) {
                col = 0;
                row++;
            }
        }
    }

    @FXML
    private void addCardGroup() {
        CardGroup cardGroup = new CardGroup(lessonPlanGrid.getRowCount());
        LessonPlanManager.addCardGroup(plan, cardGroup, lessonPlanGrid, revert);
    }


    private void displayPlanCards(CardGroup cardGroup) {
        LessonPlanManager.displayPlanCards(cardGroup);

    }


    @FXML
    private void addCardToPlan(Card card) {
        for (CardGroup cardGroup : plan.getCardGroups()) {
            if (cardGroup.getEvent().equals(card.getEvent()) && (!cardGroup.getCards().contains(card)) ){
                cardGroup.addCard(card);
                displayPlanCards(cardGroup);
            }
        }
    }

    @FXML
    private void printLessonPlanWithImages() {

        Node centerNode = newLessonPlanBorderPane.getCenter();
        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (printerJob != null && printerJob.showPrintDialog(null)) {
            WritableImage snapshot = centerNode.snapshot(new SnapshotParameters(), null);
            ImageView center = new ImageView(snapshot);


            boolean success = printerJob.printPage(center);

            if (success) {
                printerJob.endJob();
            }
        }
    }
    @FXML
    void SaveButton(ActionEvent event) {
        SaveCourse.saveFile();
    }
}




