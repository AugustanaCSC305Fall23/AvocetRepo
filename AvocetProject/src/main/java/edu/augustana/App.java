package edu.augustana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
                Card newCard = new Card(code, event, category, title, img, gender);
                cardCollection.add(newCard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        launch();
    }
}