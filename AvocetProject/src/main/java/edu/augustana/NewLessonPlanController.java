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
    private ComboBox<String> filteredBox;
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
    private CheckBox neutralBox;
    @FXML
    private CheckBox femaleBox;
    @FXML
    private GridPane lessonPlanGrid;
    @FXML
    private Button addEventButton;
    private double width;
    private Course course;
    @FXML
    void initialize() {
        comboBoxInitializer(filteredBox);
        filteredBox.getSelectionModel().select(0);
        course = new Course();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        cardsGrid.setPrefWidth(width/2);
        lessonPlanGrid.setPrefWidth(width/2);
        lessonPlanGrid.setVgap(10);
        ComboBox<String> eventComboBox = new ComboBox<String>();
        for (Card c : App.cardCollection) {
            if (!eventComboBox.getItems().contains(c.getEvent())) {
                eventComboBox.getItems().add(c.getEvent());
            }
        }


        // initialize the cardGrid
        //displayCards(App.cardCollection);
        displayCards(eventCardList());



//    CheckBox box1 = new CheckBox("Neutral");
//    CheckBox box2 = new CheckBox("Female");
//    Button genderFilteringButton = new Button("Filter");
//    genderFilteringButton.setOnAction(e -> filteringBaseOnGender(box1, box2));

    }

//    @FXML
//    void femaleGenderFilter(ActionEvent event) {
//
//    }
//
//    @FXML
//    void neutralGenderFilter(ActionEvent event) {
//
//    }

    @FXML
    void eventFiltering(ActionEvent event) {
        cardsGrid.getChildren().clear();
        displayCards(eventCardList());
    }

    private List<Card> eventCardList() {
        List<Card> outputList = new ArrayList<>();
        String keywords = filteredBox.getValue();
        if (keywords.equals("ALL")) {
            outputList = App.cardCollection;
        } else {
            for (Card myCard: App.cardCollection) {
                if (myCard.getEvent().contains(keywords)) {
                    outputList.add(myCard);
                }
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
//=======
//    private GridPane lessonPlanGrid;
//    @FXML
//    private Button addEventButton;
//    private double width;
//    private Course course;
//    @FXML
//    void initialize() {
//        course = new Course();
//
//        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
//        width = screenSize.getWidth();
//        cardsGrid.setPrefWidth(width/2);
//        lessonPlanGrid.setPrefWidth(width/2);
//        ComboBox<String> eventComboBox = new ComboBox<String>();
//        for (Card c : App.cardCollection) {
//            if (!eventComboBox.getItems().contains(c.getEvent())) {
//                eventComboBox.getItems().add(c.getEvent());
//            }
//        }
//
//
//        // initialize the cardGrid
//        displayCards(App.cardCollection);
//    }
//
//>>>>>>> 824ec6a0ef8cfeb011ea47590362d1f0dc0648f3
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
//    @FXML
//    public void filteringBaseOnGender(CheckBox box1, CheckBox box2){
//        if (box1.isSelected()){
//            List<Card> outputList = new ArrayList<>();
//            for (Card myCard: App.cardCollection) {
//                if (myCard.getGender().equals("N")) {
//                    outputList.add(myCard);
//                }
//            }
//            cardsGrid.getChildren().clear();
//            displayCards(outputList);
//        }
//        if (box2.isSelected()){
//            List<Card> outputList = new ArrayList<>();
//            for (Card myCard: App.cardCollection){
//                if (myCard.getGender().equals("F")) {
//                    outputList.add(myCard);
//                }
//            }
//            cardsGrid.getChildren().clear();
//            displayCards(outputList);
//        }
//    }

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

    private void deletePlan(LessonPlan plan) {

        lessonPlanGrid.getChildren().remove(plan.getVBox());
        course.getPlans().remove(plan);
    }

}




