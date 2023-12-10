package edu.augustana;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import com.opencsv.CSVReader;
import javafx.stage.WindowEvent;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The main class for the application.
 */
public class App extends Application {

    /** The primary scene for the application. */
    public static Scene scene;

    /** The collection of cards used in the application. */
    public static List<Card> cardCollection = new ArrayList<>();

    private static LessonPlan currentLessonPlan = new LessonPlan();

    private static File currentLessonPlanFile = null;


    /**
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     * @throws IOException IOException If an error occurs during the initialization of the application.
     */
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

    /**
     * Sets the root of the scene to the specified FXML file.
     *
     * @param fxml The FXML file to be loaded.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Gets the list of cards in the data packs.
     *
     * @return The list of cards.
     */
    public static List<Card> getCardCollection() {
        return cardCollection;
    }

    /**
     * Loads the specified FXML file and returns its root node.
     *
     * @param fxml The FXML file to be loaded.
     * @return The root node of the loaded FXML file.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * The main method that launches the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        File directory = new File("packs");
        File[] packs = directory.listFiles();
        Arrays.sort(packs);
        FileFilter filter = new FileFilter() {
            /**
             * Tests whether or not the specified abstract pathname should be included in a pathname list.
             *
             * @param f The abstract pathname to be tested.
             * @return True if the file name ends with "csv", false otherwise.
             */
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

    public static LessonPlan getCurrentLessonPlan(){
        return currentLessonPlan;
    }

//    public static void saveCurrentLessonPlanToFile(File chosenFile) throws IOException{
//        currentLessonPlan.saveToFile(chosenFile);
//        currentLessonPlanFile = chosenFile;
//    }

    public static void loadCurrentLessonPlanToFile(File lessonPlanFile) throws IOException{
        currentLessonPlan = LessonPlan.loadFromFile(lessonPlanFile);
        currentLessonPlanFile = lessonPlanFile;
    }
    public static File getCurrentLessonPlanFile(){return currentLessonPlanFile;}
}

