module ru.vorotov.simulationslab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.vorotov.simulationslab2 to javafx.fxml;
    exports ru.vorotov.simulationslab2;
}