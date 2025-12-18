module gfj947_lab4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cain.green.javafxlogindashboard to javafx.fxml;
    opens com.cain.green.javafxlogindashboard.model to javafx.fxml;

    exports com.cain.green.javafxlogindashboard;
}