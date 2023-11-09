package edu.augustana;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCourse {
    public static Object saveFile;

    static void saveFile(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Gym Pro (*.jrsm","*.jrsm");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(App.scene.getWindow());

        if (file != null) {
//            Course course = Course.;
            String json = Course.toJson();
            System.out.println(json);
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(json);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
