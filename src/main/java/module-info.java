module abc123_lab4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.first.last.javafxlogindashboard to javafx.fxml;
    opens com.first.last.javafxlogindashboard.model to javafx.fxml;

    exports com.first.last.javafxlogindashboard;
}
