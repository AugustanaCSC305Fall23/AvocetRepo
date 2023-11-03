package edu.augustana;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class CardInfo {

    private static Scene scene;
    private static Boolean popupShowing = false;

    public static void displayPopup(Card clickCard) {
        if (popupShowing){
            return;
        }
        scene = App.scene;
        Popup popup = new Popup();
        Label Title = new Label();
        Title.setText("Card Information");
        Title.setStyle("-fx-font-size:28; -fx-font-weight:bold");

        Label equipmentLabel = new Label();
        equipmentLabel.setText("Equipment: ");
        equipmentLabel.setStyle("-fx-font-size:14; -fx-font-weight:bold");

        Label levelLabel = new Label();
        levelLabel.setText("Level: ");
        levelLabel.setStyle("-fx-font-size:14; -fx-font-weight:bold");

        Label genderLabel = new Label();
        genderLabel.setText("Gender: " + clickCard.getGender());
        genderLabel.setStyle("-fx-font-size:14; -fx-font-weight:bold");

        Label keywordLabel = new Label();
        keywordLabel.setText("Keywords: ");
        keywordLabel.setStyle("-fx-font-size:14; -fx-font-weight:bold");

        HBox hBox = new HBox(60);
        hBox.getChildren().addAll(equipmentLabel, levelLabel, genderLabel, keywordLabel);
        hBox.setMinHeight(20);
        Image image = new Image("file:images/" + clickCard.getImg());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);

        Pane imagePane = new Pane();
        imagePane.setStyle("-fx-background-color: black");
        imagePane.getChildren().add(imageView);
        VBox vBox = new VBox();
        vBox.setMinWidth(10);
        VBox vBox1 = new VBox();
        vBox1.setMinWidth(10);



        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; " +
                "-fx-border-color: black; -fx-border-width: 3; " +
                "-fx-background-color: #ff6e4e");

        borderPane.setTop(Title);
        borderPane.setCenter(imagePane);
        borderPane.setBottom(hBox);
        borderPane.setRight(vBox);
        borderPane.setLeft(vBox1);

        Button closeButton = new Button("Close");
        closeButton.setPadding(new Insets(5,5,5,5));
        closeButton.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; " +
                "-fx-background-color :  #ff6e4e; -fx-text-fill:  white; " +
                "-fx-font-weight: bold; -fx-font-size:14");


        closeButton.setOnAction(event -> {
            popup.hide();
            popupShowing = false;
        });

        borderPane.setAlignment(closeButton, Pos.TOP_RIGHT);
        borderPane.setTop(closeButton);
        popup.getContent().add(borderPane);
        if (!popup.isShowing()){
            popup.show(scene.getWindow(),200,100);
            popupShowing = true;
        }

    }
}
