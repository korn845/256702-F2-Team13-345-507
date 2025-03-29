package com.lab;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    private Sound sound; // Class-level variable for Sound

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        sound = new Sound(); // Use the class-level variable

        // Set a background image for the BorderPane
        try {
            Image backgroundImage = new Image(getClass().getResource("/images/BG_GAME.jpg").toExternalForm());

            BackgroundImage background = new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
            );
            root.setBackground(new Background(background));
        } catch (NullPointerException e) {
            System.err.println("Error: Background image not found. Ensure BG_GAME.jpg is in the resources folder.");
        }

        // Create the START button
        Button startButton = new Button("START");
        startButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");

        // Create the OPTIONS button
        Button optionsButton = new Button("OPTIONS");
        optionsButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");

        // Create the EXIT button
        Button exitButton = new Button("EXIT");
        exitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");

        // Create a VBox to hold the buttons and style it

        VBox buttonsVBox = new VBox(15, startButton, settingsButton, exitButton); // 15 is spacing
        buttonsVBox.setAlignment(Pos.CENTER_LEFT);
        buttonsVBox.setStyle("-fx-padding: 15; -fx-background-color: #333333;");


        // Add the VBox to the left side of the BorderPane
        root.setLeft(buttonsVBox);

        // Play background music
        sound.playBackgroundMusic();
        primaryStage.setOnCloseRequest(event -> sound.stopBackgroundMusic());

        // Set action for the START button
        startButton.setOnAction(event -> {
            GameMap gameMap = new GameMap(); // Ensure GameMap exists
            root.setCenter(gameMap.getMapPane());
        });

        // Set action for the SETTINGS button (open a new window)
        settingsButton.setOnAction(event -> {
            SettingsWindow.display(); // Use display() to open the settings window
        });

        // Set action for the EXIT button
        exitButton.setOnAction(event -> {
            primaryStage.close();
        });

        // Create and set the scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Inferno&Tide");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Sound getSound() {
        return sound;
    }
}