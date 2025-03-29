package com.lab;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class StartButton {

    private Button startButton;

    public StartButton(Stage primaryStage) {
        // Initialize the button with the label "Start Game"
        startButton = new Button("Start Game");

        // Set an action to start the game when the button is clicked
        startButton.setOnAction(event -> startGame(primaryStage));
    }

    // Getter for the button
    public Button getButton() {
        return startButton;
    }

    // Method to start the game
    private void startGame(Stage primaryStage) {
        // Create a new GameController instance
        GameController gameController = new GameController();

        // Get the game pane from the GameController
        Pane gameRoot = gameController.getGamePane();

        // Create a new scene for the game
        Scene gameScene = new Scene(gameRoot, 800, 600);

        // Set the stage title and scene, then show the stage
        primaryStage.setTitle("Inferno&Tide");
        primaryStage.setScene(gameScene);
        primaryStage.show();

        // Start the game logic
        gameController.startGame();
    }
}