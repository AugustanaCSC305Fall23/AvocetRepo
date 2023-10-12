package edu.augustana;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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
        //Image image = new Image("/1.png");
        //ImageView imageView = new ImageView(image);
       // cardsGrid.add(imageView, 0,0);
       // keyWordsComboBox.getItems().addAll("Floor", "Bar", "Mushroom", "Rings", "KeyWord1", "KeyWord2");
//        for (int i = 0; i < App.cardCollection.size(); i++) {
//
//                String imagePath =  App.cardCollection.get(i).getImg();
//                Image image = new Image(imagePath);
//                ImageView imageView = new ImageView(image);
//                cardsGrid.add(imageView, j, i);
//
//        }

    }



}
