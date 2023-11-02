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
                cardButton.setOnAction(event -> displayPopup(clickCard));
                cardButton.setGraphic(imageView);
                cardsGrid.add(cardButton, col,row, 1, 1);

                col++;
                if (col >= numCols) {
                    col = 0;
                    row++;
                }

        }


    }

    private void displayPopup(Card clickCard) {
        Popup popup = new Popup();
        popup.setWidth(300);
        popup.setHeight(300);
        Label Title = new Label();
        Title.setText("Card Information");
        Title.setStyle("-fx-font-size:28; -fx-font-weight:bold");

        Label equipmentLabel = new Label();
        equipmentLabel.setText("Equipment: ");
        equipmentLabel.setStyle("-fx-font-size:11; -fx-font-weight:bold");

        Label levelLabel = new Label();
        levelLabel.setText("Level: " );
        levelLabel.setStyle("-fx-font-size:11; -fx-font-weight:bold");

        Label genderLabel = new Label();
        genderLabel.setText("Gender: " + clickCard.getGender());
        genderLabel.setStyle("-fx-font-size:11; -fx-font-weight:bold");

        Label keywordLabel = new Label();
        keywordLabel.setText("Keywords: ");
        keywordLabel.setStyle("-fx-font-size:11; -fx-font-weight:bold");

        HBox hBox = new HBox(equipmentLabel,levelLabel,genderLabel,keywordLabel);
        Image image = new Image("file:images/"+clickCard.getImg());
        ImageView imageView = new ImageView(image);





        BorderPane borderPane = new BorderPane();
        borderPane.setTop(Title);
        borderPane.setCenter(imageView);
        popup.getContent().add(borderPane);
        
    }


}
