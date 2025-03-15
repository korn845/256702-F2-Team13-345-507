package com.lab;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class StartButton {

    private Button startButton;

    public StartButton(Stage primaryStage) {
        startButton = new Button("Start Game");
        startButton.setOnAction(event -> startGame(primaryStage));
    }

    public Button getButton() {
        return startButton;
    }

    private void startGame(Stage primaryStage) {
        GameController gameController = new GameController();
        Pane gameRoot = gameController.getGamePane();

        Scene gameScene = new Scene(gameRoot, 800, 600);
        primaryStage.setTitle("Tower Defense Game");
        primaryStage.setScene(gameScene);
        primaryStage.show();

        gameController.startGame();
    }
}