package com.cain.green.javafxlogindashboard;

import com.cain.green.javafxlogindashboard.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * this is the controller fo the login screen
 * this validates and takes user input.
 *
 */
public class LoginScreenController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void handleLogin() {
        errorLabel.setText("");

        String username = usernameField.getText() == null
                ? "" : usernameField.getText().trim();
        String password = passwordField.getText() == null
                ? "" : passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Username and password cannot be empty.");
            return;
        }

        // Authenticate against users.csv
        User user = User.authenticate(username, password);

        if (user == null) {
            errorLabel.setText("Invalid username or password.");
            return;
        }

        // Load main dashboard
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("layouts/main-screen.fxml")
            );
            Scene scene = new Scene(loader.load());

            MainScreenController controller = loader.getController();
            controller.setLoggedInUser(user);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setTitle("GERO AidShip Dashboard");
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Error loading dashboard.");
        }
    }
}

