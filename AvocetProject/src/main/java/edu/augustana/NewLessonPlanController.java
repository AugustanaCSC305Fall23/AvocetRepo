package edu.augustana;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
    void initialize() {
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
        int numCols = cardsGrid.getColumnConstraints().size();
        int col = 0;
        int row = 0;
        for (Card myCard : cardList) {
            Image image = new Image("file:images/" + myCard.getImg());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            Button cardButton = new Button();
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




