module edu.augustana {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires java.desktop;
    requires com.google.gson;

    opens edu.augustana to javafx.fxml;
    exports edu.augustana;
}
