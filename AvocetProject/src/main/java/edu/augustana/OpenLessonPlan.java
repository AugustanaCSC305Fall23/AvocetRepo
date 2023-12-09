package edu.augustana;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Utility class for opening lesson plans from file.
 */
public class OpenLessonPlan {
    public static Object openFile;
    public static Stage stage;

    /**
     * Opens a lesson plan file and reads its content.
     *
     * @throws IOException If an I/O error occurs during file reading.
     */
    static void openFile() throws IOException {
        FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("Gym Pro(*.jrsm)", "*.jrsm");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(ex1);
        fileChooser.setTitle("Select an lesson plan");
        fileChooser.setInitialDirectory(new File("D:/lessonplanfile"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null){
            LessonPlan openedFile = LessonPlan.fromJson(selectedFile.getPath());

        }
    }
}


