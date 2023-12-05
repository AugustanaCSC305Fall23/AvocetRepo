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

        Label equipmentLabel = new Label();
        equipmentLabel.setText("Equipment: \n" + clickCard.getEquipment());
        equipmentLabel.setStyle("-fx-font-size:13; -fx-font-weight:bold");
        equipmentLabel.setWrapText(true);

        Label levelLabel = new Label();
        levelLabel.setText("Level: \n"+ clickCard.getLevel());
        levelLabel.setStyle("-fx-font-size:13; -fx-font-weight:bold");
        levelLabel.setWrapText(true);

        Label genderLabel = new Label();
        genderLabel.setText("Gender: \n" + clickCard.getGender());
        genderLabel.setStyle("-fx-font-size:13; -fx-font-weight:bold");
        genderLabel.setWrapText(true);
        Label keywordLabel = new Label();
        keywordLabel.setText("Keywords: \n"+clickCard.getKeywords());
        keywordLabel.setStyle("-fx-font-size:13; -fx-font-weight:bold");
        keywordLabel.setWrapText(true);

        HBox hBox = new HBox(25);
        hBox.getChildren().addAll(equipmentLabel, levelLabel, genderLabel, keywordLabel);
        hBox.setMinHeight(20);

        Image image = new Image("file:packs/" + clickCard.getPackName() + "/" + clickCard.getImageFileName() + ".png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);

        VBox vBox = new VBox();
        vBox.setMinWidth(10);
        VBox vBox1 = new VBox();
        vBox1.setMinWidth(10);

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; " +
                "-fx-border-color: black; -fx-border-width: 3; " +
                "-fx-background-color: #89CFF0");

        borderPane.setCenter(imageView);
        borderPane.setBottom(hBox);
        borderPane.setRight(vBox);
        borderPane.setLeft(vBox1);

        Button closeButton = new Button("Close");
        closeButton.setPadding(new Insets(5,5,5,5));
        closeButton.setStyle("-fx-border-radius: 20; -fx-background-radius: 20; " +
                "-fx-background-color :  #89CFF0; -fx-text-fill:  white; " +
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
