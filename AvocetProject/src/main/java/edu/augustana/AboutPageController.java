package edu.augustana;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AboutPageController {
    @FXML
    private Button homeButton;
    /**
     * Switches to the main lesson plan screen.
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void switchToWelcomeScreen() throws IOException {
        App.setRoot("WelcomeScreenView");
    }
}
