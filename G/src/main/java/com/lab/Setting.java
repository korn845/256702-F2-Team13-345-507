package com.lab;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Setting {

    private Button settingButton;

    public Setting(Stage primaryStage) {
        settingButton = new Button("Setting");
        settingButton.setOnAction(event -> startGame(primaryStage));
    }

    public Button getButton() {
        return settingButton;
    }

    private void startGame(Stage primaryStage) {
        GameController gameController = new GameController();
        Pane gameRoot = gameController.getGamePane();

        Scene gameScene = new Scene(gameRoot, 800, 600);
        primaryStage.setTitle("Inferno&Tide");
        primaryStage.setScene(gameScene);
        primaryStage.show();

        gameController.startGame();
    }
}