package edu.augustana;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.awt.Dimension;
import java.io.IOException;
import com.opencsv.CSVReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;



/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static List<Card> cardCollection = new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight() - 0.10*(screenSize.getHeight());
        scene = new Scene(loadFXML("WelcomeScreenView"), width, height);
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

        try (CSVReader reader = new CSVReader(new FileReader("DEMO1.csv"))) {
            List<String[]> data = reader.readAll();
            for (int i = 1; i < data.size(); i++) {
                String[] row = data.get(i);
                String code = row[0];
                String event = row[1];
                String category = row[2];
                String title = row[3];
                String img = row[5];
                String gender = row[6];
                String modelSex = row[7];
                String level = row[8];
                String equipment = row[9];
                String keywords = row[10];
                Card newCard = new Card(code, event, category, title, img, gender, modelSex, level, equipment,keywords);
                cardCollection.add(newCard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        launch();
    }
}


//package edu.augustana;
//
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.ComboBox;
//import javafx.stage.Stage;
//import com.opencsv.CSVReader;
//
//import java.awt.Dimension;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class App extends Application {
//
//    public static Scene scene;
//    public static List<Card> cardCollection = new ArrayList<>();
//
//    @Override
//    public void start(Stage stage) throws IOException {
//        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
//        double width = screenSize.getWidth();
//        double height = screenSize.getHeight() - 0.10 * (screenSize.getHeight());
//        scene = new Scene(loadFXML("WelcomeScreenView"), width, height);
//        stage.setScene(scene);
//        stage.show();
//        loadCSVFilesFromFolder(System.getProperty("user.dir"));
//    }
//
//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//
//    public static List<Card> getCardCollection() {
//        return cardCollection;
//    }
//
//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        return fxmlLoader.load();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//
//    private static void loadCSVFilesFromFolder(String folderPath) {
//        File folder = new File(folderPath);
//        if (folder.isDirectory()) {
//            File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));
//            if (files != null) {
//                for (File file : files) {
//                    processCSVFile(file);
//                }
//            }
//        }
//    }
//
//    private static void processCSVFile(File file) {
//
//        try (CSVReader reader = new CSVReader(new FileReader(file))) {
//            List<String[]> data = reader.readAll();
//            for (int i = 1; i < data.size(); i++) {
//                String[] row = data.get(i);
//                String code = row[0];
//                String event = row[1];
//                String category = row[2];
//                String title = row[3];
//                String img = row[5];
//                String gender = row[6];
//                String modelSex = row[7];
//                String level = row[8];
//                String equipment = row[9];
//                String keywords = row[10];
//                Card newCard = new Card(code, event, category, title, img, gender, modelSex, level, equipment, keywords);
//                cardCollection.add(newCard);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
