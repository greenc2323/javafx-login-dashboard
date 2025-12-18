package com.cain.green.javafxlogindashboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * main java
 * shows login screen
 */
public class AidShipApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("layouts/login-screen.fxml")
        );

        Scene scene = new Scene(loader.load());
        stage.setTitle("GERO Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

