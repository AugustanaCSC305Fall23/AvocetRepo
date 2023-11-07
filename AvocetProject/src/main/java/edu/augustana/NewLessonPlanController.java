package edu.augustana;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class NewLessonPlanController {


    @FXML
    private ComboBox<String> eventFilterComboBox;
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
    @FXML
    void initialize() {
        comboBoxInitializer(eventFilterComboBox);
        course = new Course();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        cardsGrid.setPrefWidth(width/2);
        lessonPlanGrid.setPrefWidth(width/2);
        lessonPlanGrid.setVgap(10);
        displayCards(App.cardCollection);
    }

    @FXML
    void eventFiltering(ActionEvent event) {
        cardsGrid.getChildren().clear();
        displayCards(eventCardList());
    }

    private List<Card> eventCardList() {
        List<Card> outputList = new ArrayList<>();
        String keywords = eventFilterComboBox.getValue();
        for (Card myCard : App.cardCollection) {
            if (myCard.getEvent().contains(keywords)) {
                outputList.add(myCard);
            }
        }
        return outputList;
    }

    private void comboBoxInitializer(ComboBox<String> comboBox) {
        for (Card c : App.cardCollection) {
            if (!comboBox.getItems().contains(c.getEvent())) {
                comboBox.getItems().add(c.getEvent());
            }
        }
    }

    @FXML
    void searchFunction() {
        String searchText = searchBox.getText().toLowerCase();
        List<Card> searchList = eventCardList();
        List<Card> outputList = new ArrayList<>();
        for (Card myCard : searchList) {
            if (myCard.matchesSearchText(searchText)) {
                outputList.add(myCard);
            }
        }
        cardsGrid.getChildren().clear();
        displayCards(outputList);
    }

    private void displayCards(List<Card> cardList) {
        int numRows = cardsGrid.getRowConstraints().size();
        int numCols = 3;

        cardsGridVbox.setPrefWidth(width/2);

        cardsGrid.setVgap(10);
        int col = 0;
        int row = 0;
        for (Card myCard : cardList) {
            Image image = new Image("file:images/" + myCard.getImg());
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
            if (plan.getEvent().equals(card.getEvent())){
                plan.addCard(card);
                displayPlanCards(plan);

            }
        }
    }

    private void displayPlanCards(LessonPlan plan) {
        int numCards = (plan.getCards().size());
        Card newCard = plan.getCards().get(numCards-1);
        Image image = new Image("file:images/" + newCard.getImg());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        plan.getHBox().getChildren().add(imageView);

        lessonPlanGrid.getChildren().remove(plan.getHBox());
        lessonPlanGrid.add(plan.getVBox(), 0, plan.getIndex());
    }

    private void deletePlan(LessonPlan plan) {

        lessonPlanGrid.getChildren().remove(plan.getVBox());
        course.getPlans().remove(plan);
    }

}




