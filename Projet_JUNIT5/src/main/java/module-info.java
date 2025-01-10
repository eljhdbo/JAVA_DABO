module fr.esiee.projet_junit5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.esiee.projet_junit5 to javafx.fxml;
    exports fr.esiee.projet_junit5;
}