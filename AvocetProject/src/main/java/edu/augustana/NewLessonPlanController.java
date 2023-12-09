package edu.augustana;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import javafx.stage.WindowEvent;


import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
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

    @FXML
    private Button openButton;
    private Boolean revert;

    public Stage stage;

    @FXML
    private TextField titleField;
    private static boolean changesMade = false;

    @FXML
    private MenuItem textOnlyMenu;

    @FXML
    private MenuItem withImagesMenu;
    @FXML
    private TextField lessonPlanTitleTF;



    @FXML
    void initialize() {

        job = PrinterJob.createPrinterJob();
        FilterController.comboBoxInitializer(eventFilterComboBox, "event");
        FilterController.comboBoxInitializer(genderFilterComboBox, "gender");
        FilterController.comboBoxInitializer(levelFilterComboBox, "level");
        FilterController.comboBoxInitializer(modelFilterComboBox, "model");
        revert = false;
        plan = new LessonPlan();
        lessonPlanTitleTF.textProperty().addListener((observable, oldValue, newValue) -> {
            plan.setTitle(lessonPlanTitleTF.getText());
        });
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
        String filePath = "src/favoriteCards.txt";
        Path path = Paths.get(filePath);

        int numRows = cardsGrid.getRowConstraints().size();
        int numCols = 3;
        cardsGrid.setVgap(10);
        int col = 0;
        int row = 0;
        for (Card myCard : cardList) {
            ImageView imageView = new ImageView(myCard.getImageThumbnail());
            imageView.setFitWidth(180);
            imageView.setFitHeight(180);
            Button cardButton = new Button();
            cardButton.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            cardButton.getStyleClass().add("cardPopup");
            Card clickCard = myCard;
            cardButton.setOnAction(event -> addCardToCardGroup(myCard));
            cardButton.setGraphic(imageView);
            ImageView maximizeIcon = new ImageView("file:src/maximizeicon.png");
            maximizeIcon.setFitHeight(10);
            maximizeIcon.setFitWidth(10);
            Button maximizeButton = new Button();
            maximizeButton.setGraphic(maximizeIcon);
            maximizeButton.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            maximizeButton.getStyleClass().add("buttonOrange");
            maximizeButton.setOnAction(event -> CardInfo.displayPopup(clickCard));
            CheckBox isFavoriteCB = new CheckBox();
            isFavoriteCB.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            isFavoriteCB.getStyleClass().add("heart-check-box");
            try {
                List<String> lines = Files.readAllLines(path);
                if (lines.contains(myCard.getCode())) {
                    myCard.setFavoriteStatus(true);
                    isFavoriteCB.setSelected(true);
                }
            } catch (IOException e) {
                System.err.println("Error reading lines from the file: " + e.getMessage());
            }
            isFavoriteCB.selectedProperty().addListener((obs, oldValue, newValue) -> {
                try {
                    if (newValue) {
                        // Checkbox is checked, add the card code to the file
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/favoriteCards.txt", true))) {
                            myCard.setFavoriteStatus(true);
                            writer.write(myCard.getCode());
                            writer.newLine();
                        }
                    } else {
                        List<String> lines = Files.readAllLines(Paths.get("src/favoriteCards.txt"));
                        lines.remove(myCard.getCode());
                        myCard.setFavoriteStatus(false);

                        // Write the updated content back to the file
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/favoriteCards.txt"))) {
                            for (String line : lines) {
                                writer.write(line);
                                writer.newLine(); // Add a newline for better formatting or separation
                            }
                        }
                    }
                } catch (IOException e) {
                    // Handle the IOException appropriately
                    e.printStackTrace();
                }
            });

            VBox cardVbox = new VBox(maximizeButton, cardButton);
            cardsGrid.add(cardButton, col, row);
            cardsGrid.add(maximizeButton, col, row);
            cardsGrid.add(isFavoriteCB, col, row);
            cardsGrid.setValignment(isFavoriteCB, javafx.geometry.VPos.TOP);
            cardsGrid.setHalignment(isFavoriteCB, javafx.geometry.HPos.LEFT);
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
        ChangesMadeManager.setChangesMade(true);
    }


    private void displayPlanCards(CardGroup cardGroup) {
        LessonPlanManager.displayPlanCards(cardGroup);

    }

    @FXML
    private void addCardToCardGroup(Card card) {
        for (CardGroup cardGroup : plan.getCardGroups()) {
            if (cardGroup.getEvent().equals(card.getEvent()) && (!cardGroup.getCards().contains(card)) ){
                cardGroup.addCard(card);
                displayPlanCards(cardGroup);
                ChangesMadeManager.setChangesMade(true);
            }
        }
    }

    @FXML
    private void printLessonPlanTextOnly() {
        Node centerNode = newLessonPlanBorderPane.getCenter();
        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (printerJob != null && printerJob.showPrintDialog(null)) {
            VBox listContainer = new VBox();
            Text title = new Text("Lesson Plan Title: " +plan.getTitle());
            listContainer.getChildren().add(title);

            // Replace the following with your actual list data
            List<CardGroup> cardGroups = plan.getCardGroups();
            for (CardGroup cg : cardGroups) {
                Text event = new Text(cg.getEvent());
                listContainer.getChildren().add(event);
                for (Card card : cg.getCards()) {
                    Text listItemText = new Text("  - Code: "+card.getCode()+", Title: " +card.getTitle());
                    listContainer.getChildren().add(listItemText);
                }
            }
            boolean success = printerJob.printPage(listContainer);
            if (success) {
                printerJob.endJob();
            }
        }
    }


    @FXML
    private void printLessonPlanWithImages() {
        Node centerNode = newLessonPlanBorderPane.getCenter();
        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (printerJob != null && printerJob.showPrintDialog(null)) {
            VBox listContainer = new VBox();
            Text title = new Text("Lesson Plan Title: " +plan.getTitle());
            listContainer.getChildren().add(title);
            int numHbox = 0;
            List<CardGroup> cardGroups = plan.getCardGroups();
            for (CardGroup cg : cardGroups) {
                Text event = new Text(cg.getEvent());
                listContainer.getChildren().add(event);
                HBox hBox = new HBox();
                int count = 0;

                for (Card card : cg.getCards()) {
                    ImageView imageView = new ImageView(card.getImageThumbnail());
                    hBox.getChildren().add(imageView);
                    count++;
                    if (count % 3 == 0) {
                        listContainer.getChildren().add(hBox);
                        hBox = new HBox();
                    }

                }
                if (!hBox.getChildren().isEmpty()) {
                    listContainer.getChildren().add(hBox);
                    numHbox++;
                }
                if (numHbox >= 5) {
                    boolean success = printerJob.printPage(listContainer);

                    if (success) {
                        listContainer.getChildren().clear(); // Clear the content of the current page
                        numHbox = 0;
                    } else {
                        break;
                    }
                }
            }


            boolean success = printerJob.printPage(listContainer);

            if (success) {
                printerJob.endJob();
            }
        }
    }
    public static void handleCloseRequest(WindowEvent event) {
        if(ChangesMadeManager.isThereChanges()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Unsaved Changes");
            alert.setHeaderText("There are some unsaved changes");

            ButtonType buttonTypeSave = new ButtonType("Save");
            ButtonType buttonTypeDoNotSave = new ButtonType("Don't Save");
            ButtonType buttonTypeCancel = new ButtonType("Cancel");

            alert.getButtonTypes().setAll(buttonTypeSave, buttonTypeDoNotSave, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == buttonTypeSave){
                SaveCourse.saveFile();
            }else if(result.get() == buttonTypeDoNotSave){
                Platform.exit();
            }else{
                event.consume();
            }
        }else{
            Platform.exit();
        }
    }
    @FXML
    void SaveButton(ActionEvent event) {
        SaveCourse.saveFile();
        ChangesMadeManager.setChangesMade(false);
    }

    @FXML
    void OpenButton(ActionEvent event) throws IOException { OpenLessonPlan.openFile(); }
}




