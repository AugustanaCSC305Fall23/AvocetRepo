package edu.augustana;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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

        comboBoxInitializer(eventFilterComboBox, "event");
        comboBoxInitializer(genderFilterComboBox, "gender");
        comboBoxInitializer(levelFilterComboBox, "level");

        course = new Course();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        cardsGrid.setPrefWidth(width/2);
        lessonPlanGrid.setMinWidth(width/2);
        lessonPlanGrid.setVgap(10);
        displayCards(App.cardCollection);
    }

    private void cardGridHandler(){
        cardsGrid.getChildren().clear();
        searchFunction(levelFunction(genderFunction(eventFunction())));
//        List<Card> tempList1 = eventFunction();
//        List<Card> tempList2 = genderFunction(tempList1);
//        List<Card> tempList3 = levelFunction(tempList2);
//        searchFunction(tempList3);
    }

    @FXML
    void searchFiltering() {
        cardGridHandler();
    }

    @FXML
    void eventFiltering(ActionEvent event) {
        cardGridHandler();
    }

    @FXML
    void genderFiltering(ActionEvent event) {
        cardGridHandler();
    }

    @FXML
    void levelFiltering(ActionEvent event) {
        cardGridHandler();
    }

    private List<Card> eventFunction() {
        List<Card> eventOutputList = new ArrayList<>();
        String keywords = eventFilterComboBox.getValue();
        if (keywords == null || keywords.equals("All Events")) {
            eventOutputList = App.getCardCollection();
        } else {
            for (Card myCard : App.cardCollection) {
                if (myCard.getEvent().contains(keywords)) {
                    eventOutputList.add(myCard);
                }
            }
        }
        return eventOutputList;
    }

    private List<Card> genderFunction(List<Card> tempList) {
        List<Card> genderOutputList = new ArrayList<>();
        String keywords = genderFilterComboBox.getValue();
        if (keywords == null || keywords.equals("All Genders")) {
            genderOutputList = tempList;
        } else {
            for (Card myCard : tempList) {
                if (myCard.getGender().contains(keywords)) {
                    genderOutputList.add(myCard);
                }
            }
        }
        return genderOutputList;
    }

    private List<Card> levelFunction(List<Card> tempList) {
        List<Card> levelOutputList = new ArrayList<>();
        String keywords = levelFilterComboBox.getValue();
        if (keywords == null || keywords.equals("All Levels") || keywords.equals("ALL")) {
            levelOutputList = tempList;
        } else {
            for (Card myCard : tempList) {
                String[] cardLevels = myCard.getLevelList();
                for (String level : cardLevels) {
                    if (keywords.equals(level)) {
                        levelOutputList.add(myCard);
                    }
                }
            }
        }
        return levelOutputList;
    }

    private void searchFunction(List<Card> tempList) {
        String searchText = searchBox.getText().toLowerCase();
        List<Card> searchOutputList = new ArrayList<>();
        for (Card myCard : tempList) {
            if (myCard.matchesSearchText(searchText)) {
                searchOutputList.add(myCard);
            }
        }
        cardsGrid.getChildren().clear();
        displayCards(searchOutputList);
    }

    private void comboBoxInitializer(ComboBox<String> comboBox, String category) {
        if (category.equals("event")) {
            comboBox.getItems().add("All Events");
            for (Card c : App.cardCollection) {
                if (!comboBox.getItems().contains(c.getEvent())) {
                    comboBox.getItems().add(c.getEvent());
                }
            }
        } else if (category.equals("gender")) {
            comboBox.getItems().add("All Genders");
            for (Card c : App.cardCollection) {
                if (!comboBox.getItems().contains(c.getGender())) {
                    comboBox.getItems().add(c.getGender());
                }
            }
        } else if (category.equals("level")) {
            comboBox.getItems().add("All Levels");
            for (Card c : App.cardCollection) {
                String[] lvl = c.getLevelList();
                for (String tempLevel : lvl) {
                    if (!comboBox.getItems().contains(tempLevel)) {
                        comboBox.getItems().add(tempLevel);
                    }
                }
            }
        }
    }

    private void displayCards(List<Card> cardList) {
        int numRows = cardsGrid.getRowConstraints().size();
        int numCols = 3;
        cardsGridVbox.setPrefWidth(width/2);
        cardsGrid.setVgap(10);
        int col = 0;
        int row = 0;
        for (Card myCard : cardList) {
            Image image = new Image("file:packs/"+myCard.getPackName()+"/" + myCard.getImg());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            Button cardButton = new Button();
            Card clickCard = myCard;
            cardButton.setOnAction(event -> CardInfo.displayPopup(clickCard));
            cardButton.setGraphic(imageView);
            Button addButton = new Button("Add");
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
            Image image = new Image("file:packs/" + newCard.getPackName() + "/" + newCard.getImg());

            ImageView imageView = new ImageView(image);
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
        //
        Node node = App.scene.getRoot();
//        node.getWid

    }

}




