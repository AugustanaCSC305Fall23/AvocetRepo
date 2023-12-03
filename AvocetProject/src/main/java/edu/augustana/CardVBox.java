package edu.augustana;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CardVBox extends VBox {

    private Card myCard;
    public CardVBox(Card myCard, Button actionButton) {
        super();
        this.myCard = myCard;
        ImageView imageView = new ImageView(myCard.getImageThumbnail());
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        Button cardButton = new Button();
        cardButton.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        cardButton.getStyleClass().add("cardPopup");
        cardButton.setOnAction(event -> CardInfo.displayPopup(myCard));
        cardButton.setGraphic(imageView);

        actionButton.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        actionButton.getStyleClass().add("buttonWhite");
        actionButton.setPrefWidth(220);
        //actionButton.setStyle("-fx-background-color: #ff6e4e");

        getChildren().addAll(cardButton, actionButton);
    }

    public Card getMyCard() {
        return myCard;
    }
}
