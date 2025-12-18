module gfj947_lab4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.utsa.cs3443.gfj947_lab4 to javafx.fxml;
    opens edu.utsa.cs3443.gfj947_lab4.model to javafx.fxml;

    exports edu.utsa.cs3443.gfj947_lab4;
}