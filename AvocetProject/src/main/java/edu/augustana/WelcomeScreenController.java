package edu.augustana;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * Controller class for the WelcomeScreen.fxml file.
 */
public class WelcomeScreenController {

    /**
     * Switches to the main lesson plan screen.
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void switchToMainScreen() throws IOException {
        App.setRoot("NewLessonPlanView");
    }
}
