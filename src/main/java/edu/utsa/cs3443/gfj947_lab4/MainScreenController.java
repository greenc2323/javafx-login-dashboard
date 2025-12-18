package edu.utsa.cs3443.gfj947_lab4;

import edu.utsa.cs3443.gfj947_lab4.model.AidShip;
import edu.utsa.cs3443.gfj947_lab4.model.AidShipManager;
import edu.utsa.cs3443.gfj947_lab4.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

/**
 * Controller main dashboard screen
 * Handles listing, sorting ships and the logging out
 */
public class MainScreenController implements Initializable {

    @FXML private Label welcomeLabel;
    @FXML private RadioButton sortByNameRadio;
    @FXML private RadioButton sortByRegRadio;
    @FXML private TextArea outputArea;

    private final AidShipManager manager = new AidShipManager();
    private User loggedInUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Group the radio buttons
        ToggleGroup group = new ToggleGroup();
        sortByNameRadio.setToggleGroup(group);
        sortByRegRadio.setToggleGroup(group);
        sortByNameRadio.setSelected(true);

        // Load ship data
        try {
            manager.loadFromCSV("/data/aid_ships.csv");
        } catch (IOException e) {
            showError("Error loading aid_ships.csv: " + e.getMessage());
        }
    }

    /**
     * this is called by  the login controller after a successful login.
     */
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        if (user != null) {
            welcomeLabel.setText("Welcome," + user.getFirstName() + " " + user.getLastName());
        }
    }

    @FXML
    private void handleListShips() {
        if (manager.getShips().isEmpty()) {
            outputArea.setText("No ships loaded.");
            return;
        }

        // Choose sort order
        Comparator<AidShip> comparator;
        if (sortByRegRadio.isSelected()) {
            comparator = Comparator.comparing(AidShip::getRegistrationNumber);
        } else {
            comparator = Comparator.comparing(AidShip::getName);
        }

        var ships = manager.getShips();
        ships.sort(comparator);

        StringBuilder sb = new StringBuilder();
        for (AidShip ship : ships) {
            sb.append(ship.toString()).append(System.lineSeparator());
        }
        outputArea.setText(sb.toString());
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("layouts/login-screen.fxml")
            );
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setTitle("GERO Login");
            stage.setScene(scene);
        } catch (IOException e) {
            showError("Unable to return to login screen: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.setHeaderText("Error");
        alert.showAndWait();
    }
}
