package edu.augustana;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.awt.Dimension;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import com.opencsv.CSVReader;
import javafx.stage.WindowEvent;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App extends Application {

    public static Scene scene;
    public static List<Card> cardCollection = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight() - 0.10 * (screenSize.getHeight());
        scene = new Scene(loadFXML("WelcomeScreenView"), width, height);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                NewLessonPlanController.handleCloseRequest(event);
            }
        });
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static List<Card> getCardCollection() {
        return cardCollection;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        File directory = new File("packs");
        File[] packs = directory.listFiles();
        Arrays.sort(packs);
        FileFilter filter = new FileFilter() {
            public boolean accept(File f)
            {
                return f.getName().endsWith("csv");
            }
        };
        for (File pack : packs) {
            if (pack.getName().charAt(0) != '.') {
                File[] files = pack.listFiles(filter);
                File csvFile = files[0];
                try (CSVReader reader = new CSVReader(new FileReader("packs/" + pack.getName() + "/" + csvFile.getName()))) {
                    List<String[]> data = reader.readAll();
                    for (int i = 1; i < data.size(); i++) {
                        String[] row = data.get(i);
                        String code = row[0];
                        String event = row[1];
                        String category = row[2];
                        String title = row[3];
                        String img = row[5].substring(0, row[5].lastIndexOf('.'));;
                        String gender = row[6];
                        String modelSex = row[7];
                        String level = row[8];
                        String equipment = row[9];
                        String keywords = row[10];
                        String packName = pack.getName();
                        Card newCard = new Card(code, event, category, title, img, gender, modelSex, level, equipment, keywords, packName);
                        cardCollection.add(newCard);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        launch();

    }
}
