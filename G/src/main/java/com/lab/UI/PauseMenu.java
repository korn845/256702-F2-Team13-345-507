package com.lab.UI;

import com.lab.GamePlay;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PauseMenu {
    private Stage pauseStage;
    private Stage primaryStage;
    private GamePlay gamePlay;

    public PauseMenu(Stage primaryStage, GamePlay gamePlay) {
        this.primaryStage = primaryStage;
        this.gamePlay = gamePlay;
    }

    public void show() {
        pauseStage = new Stage();
        pauseStage.initModality(Modality.APPLICATION_MODAL);
        pauseStage.initOwner(primaryStage);
        pauseStage.setTitle("Pause Menu");

        VBox pauseMenu = new VBox(20);
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.setStyle("-fx-background-color: white; -fx-padding: 20px;");

        Button resumeButton = new Button("Resume");
        Button exitButton = new Button("Restart");

        String buttonStyle = "-fx-min-width: 120px; -fx-min-height: 30px;";
        resumeButton.setStyle(buttonStyle);
        exitButton.setStyle(buttonStyle);

        resumeButton.setOnAction(e -> {
            gamePlay.resumeGame();
            pauseStage.close();
        });

        exitButton.setOnAction(e -> {
            pauseStage.close();
            MainMenu.showMainMenu(primaryStage);
        });

        pauseMenu.getChildren().addAll(resumeButton, exitButton);

        Scene pauseScene = new Scene(pauseMenu, 200, 150);
        pauseStage.setScene(pauseScene);
        pauseStage.show();
    }
}