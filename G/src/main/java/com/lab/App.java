package com.lab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        // Create the StartButton
        StartButton startButton = new StartButton(primaryStage);

        // Set the action for the StartButton to display the map
        startButton.getButton().setOnAction(event -> {
            // Create the game map
            GameMap gameMap = new GameMap();

            // Clear the root and add the map to it
            root.getChildren().clear();
            root.getChildren().add(gameMap.getMapPane());
        });

        // Add the StartButton to the root pane initially
        root.getChildren().add(startButton.getButton());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Inferno&Tide");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}