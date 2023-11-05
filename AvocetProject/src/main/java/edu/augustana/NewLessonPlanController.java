package edu.augustana;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
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
    private Button searchButton;
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
        course = new Course();

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        cardsGrid.setPrefWidth(width/2);
        lessonPlanGrid.setPrefWidth(width/2);
        ComboBox<String> eventComboBox = new ComboBox<String>();
        for (Card c : App.cardCollection) {
            if (!eventComboBox.getItems().contains(c.getEvent())) {
                eventComboBox.getItems().add(c.getEvent());
            }
        }


    // initialize the cardGrid
    displayCards(App.cardCollection);
}

    @FXML
    void searchFunction() {
        String searchText = searchBox.getText().toLowerCase();
        List<Card> outputList = new ArrayList<>();
        for (Card myCard : App.cardCollection) {
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
        plan.getHBox().getChildren().add(plan.getEventComboBox());
        course.addLessonPlan(plan);
        lessonPlanGrid.add(plan.getHBox(), 0, plan.getIndex());

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
        lessonPlanGrid.add(plan.getHBox(), 0, plan.getIndex());


//            for (Card card : plan.getCards()) {
//                Image image = new Image("file:images/" + card.getImg());
//                ImageView imageView = new ImageView(image);
//                imageView.setFitWidth(200);
//                imageView.setFitHeight(200);
//                plan.getHBox().getChildren().add(imageView);
//            }
//            //Button deleteButton = new Button("Delete");
//
//            lessonPlanGrid.getChildren().remove();
    }

}




