package edu.augustana;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;


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
    private GridPane cardsGrid;
    @FXML
    private ScrollPane cardsGridScrollPane;
    @FXML
    void initialize() {
        for (Card c : App.cardCollection) {
            if (!eventComboBox.getItems().contains(c.getEvent())) {
                eventComboBox.getItems().add(c.getEvent());
            }

        }
        //String imagePath =  App.cardCollection.get(0).getImg();
        keyWordsComboBox.getItems().addAll("Floor", "Bar", "Mushroom", "Rings", "KeyWord1", "KeyWord2");

        int numCols = 2;

        int col = 0;
        int row = 0;
        for (int i = 0; i < App.cardCollection.size(); i++) {

                Image image = new Image("file:images/" + App.cardCollection.get(i).getImg());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100); // Adjust as needed
                imageView.setFitHeight(100);
                Button cardButton = new Button();
                Card clickCard = App.cardCollection.get(i);
                cardButton.setOnAction(event -> cardInfo.displayPopup(clickCard));
                cardButton.setGraphic(imageView);
                cardsGrid.add(cardButton, col,row, 1, 1);

                col++;
                if (col >= numCols) {
                    col = 0;
                    row++;
                }

        }


    }




}
