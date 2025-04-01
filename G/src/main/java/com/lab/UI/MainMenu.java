package com.lab.UI;

import com.lab.GameLevel;
import com.lab.GamePlay;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Pos;

public class MainMenu {
    public static void showMainMenu(Stage primaryStage) {
        try {
            GamePlay gamePlay = new GamePlay();
            
            // Add tower selection bar
            TowerSelectionBar towerBar = new TowerSelectionBar(gamePlay.getGamePane(), gamePlay);
            
            // สร้างปุ่ม Start Game ในหน้าเกมแรก
            Button startButton = new Button("Start Game");
            startButton.setStyle("-fx-font-size: 24px; -fx-padding: 15px 30px;");
            startButton.setLayoutX(300);
            startButton.setLayoutY(250);
            
            Text countdownText = new Text("30");
            countdownText.setStyle("-fx-font-size: 40px; -fx-fill: white;");
            countdownText.setLayoutX(380);
            countdownText.setLayoutY(200);
            
            gamePlay.getGamePane().getChildren().addAll(startButton, countdownText);
            
            Timeline countdown = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                int timeLeft = Integer.parseInt(countdownText.getText()) - 1;
                countdownText.setText(String.valueOf(timeLeft));
                if (timeLeft <= 0) {
                    gamePlay.getGamePane().getChildren().removeAll(startButton, countdownText);
                    gamePlay.startWave();
                }
            }));
            countdown.setCycleCount(30);
            
            startButton.setOnAction(e -> {
                countdown.stop();
                gamePlay.getGamePane().getChildren().removeAll(startButton, countdownText);
                gamePlay.startWave();
            });
            
            // Start the game
            gamePlay.startGame(primaryStage);
            countdown.play();
            
        } catch (Exception e) {
            System.err.println("Error showing main menu: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void startGame(Stage primaryStage) {
        try {
            GamePlay gamePlay = new GamePlay();
            
            // Add tower selection bar
            // Add tower selection bar
            new TowerSelectionBar(gamePlay.getGamePane(), gamePlay);
            
            Button startWaveButton = new Button("Start Wave");
            startWaveButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px;");
            startWaveButton.setLayoutX(350);
            startWaveButton.setLayoutY(250);

            Text countdownText = new Text("30");
            countdownText.setStyle("-fx-font-size: 40px; -fx-fill: white;");
            countdownText.setLayoutX(380);
            countdownText.setLayoutY(200);

            gamePlay.getGamePane().getChildren().addAll(startWaveButton, countdownText);

            Timeline countdown = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                int timeLeft = Integer.parseInt(countdownText.getText()) - 1;
                countdownText.setText(String.valueOf(timeLeft));
                if (timeLeft <= 0) {
                    gamePlay.getGamePane().getChildren().removeAll(startWaveButton, countdownText);
                    gamePlay.startWave();
                }
            }));
            countdown.setCycleCount(30);

            startWaveButton.setOnAction(e -> {
                countdown.stop();
                gamePlay.getGamePane().getChildren().removeAll(startWaveButton, countdownText);
                gamePlay.startWave();
            });

            // Start the game after setting up all UI elements
            gamePlay.startGame(primaryStage);
            countdown.play();
            
        } catch (Exception e) {
            System.err.println("Error starting game: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
