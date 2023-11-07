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
    private ComboBox<String> eventComboBox;
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
    void initialize() {
        comboBoxInitializer(eventComboBox);
        comboBoxInitializer(filteredBox);
        filteredBox.getSelectionModel().select(0);
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
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        cardsGridVbox.setPrefWidth(width/2);
        cardsGrid.setPrefWidth(width/2);
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
            cardsGrid.add(cardButton, col, row);
            col++;
            if (col >= numCols) {
                col = 0;
                row++;
            }
        }
    }
}




