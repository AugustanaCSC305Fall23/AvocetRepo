package edu.augustana;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

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
    void initialize() {
        eventComboBox.getItems().addAll("Floor", "Bar", "Mushroom", "Rings");
        keyWordsComboBox.getItems().addAll("Floor", "Bar", "Mushroom", "Rings", "KeyWord1", "KeyWord2");

    }



}
