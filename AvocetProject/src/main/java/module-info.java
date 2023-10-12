module edu.augustana {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires java.desktop;

    opens edu.augustana to javafx.fxml;
    exports edu.augustana;
}
