package edu.augustana;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


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
    void initialize() {
        for (Card c : App.cardCollection) {
            if (!eventComboBox.getItems().contains(c.getEvent())) {
                eventComboBox.getItems().add(c.getEvent());
            }

        }
        //String imagePath =  App.cardCollection.get(0).getImg();
        keyWordsComboBox.getItems().addAll("Floor", "Bar", "Mushroom", "Rings", "KeyWord1", "KeyWord2");
        int numRows = cardsGrid.getRowConstraints().size();
        int numCols = cardsGrid.getColumnConstraints().size();

        int col = 0;
        int row = 0;
        for (int i = 0; i < 6; i++) {

                Image image = new Image("file:images/" + App.cardCollection.get(i).getImg());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100); // Adjust as needed
                imageView.setFitHeight(100);
                Button cardButton = new Button();
                cardButton.setGraphic(imageView);
                cardsGrid.add(cardButton, col,row);

                col++;
                if (col >= numCols) {
                    col = 0;
                    row++;
                }

        }

    }



}
