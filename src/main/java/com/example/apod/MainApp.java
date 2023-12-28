package com.example.apod;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for the NASA Space Image Viewer. This class sets up and displays the main application window.
 */

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setTitle("NASA Space Image Viewer");
        stage.setResizable(false);
        stage.setWidth(1280);
        stage.setHeight(960);
        stage.setScene(scene);
        stage.show();
    }
}
