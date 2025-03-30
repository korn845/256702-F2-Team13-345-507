package com.lab;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private Pane gamePane;
    private List<Enemy> enemies; // List to track enemies
    private int playerHealth;
    private Text healthText;

    public GameController() {
        gamePane = new Pane();
        enemies = new ArrayList<>();
        playerHealth = 10; // Initial player health

        // Display player health
        healthText = new Text("Health: " + playerHealth);
        healthText.setFill(Color.WHITE);
        healthText.setStyle("-fx-font-size: 20px;");
        healthText.setX(10);
        healthText.setY(20);
        gamePane.getChildren().add(healthText);

        // Initialize game components
        initializeGame();
    }

    public Pane getGamePane() {
        return gamePane;
    }

    private void initializeGame() {
        // Create enemy path
        EnemyPath enemyPath = EnemyPath.generateEnemyPath(800, 600);
        Polyline pathPolyline = enemyPath.getPathPolyline();
        gamePane.getChildren().add(pathPolyline);

        // Start spawning enemies
        spawnEnemyWave(5); // Spawn 5 enemies
    }

    public void startGame() {
        System.out.println("Game started!");
        // Add additional game logic here if needed
    }

    public static void startGame(Stage primaryStage, String difficulty) {
        Pane gameRoot = new Pane(); // สร้าง Pane สำหรับเกม
        Scene gameScene = new Scene(gameRoot, 800, 600);

        primaryStage.setTitle("Inferno&Tide");
        primaryStage.setScene(gameScene);
        primaryStage.show();

        System.out.println("Game started with difficulty: " + difficulty);
        // เพิ่ม logic สำหรับเริ่มเกมตามความยาก
    }

    private void spawnEnemyWave(int enemyCount) {
        Timeline timeline = new Timeline();
        for (int i = 0; i < enemyCount; i++) {
            int delay = i * 2; // Delay each enemy by 2 seconds
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(delay), event -> {
                spawnEnemy();
            }));
        }
        timeline.play();
    }

    private void spawnEnemy() {
        Enemy enemy = new Enemy(100, 10); // Enemy with 100 health and speed of 10 seconds
        enemies.add(enemy);
        enemy.spawnEnemy(gamePane, EnemyPath.generateEnemyPath(800, 600).getPathPolyline());

        // Check if enemy reaches the end
        enemy.getView().setOnMouseClicked(event -> {
            enemy.takeDamage(50); // Example: Reduce health by 50 when clicked
            if (enemy.getHealth() <= 0) {
                gamePane.getChildren().remove(enemy.getView());
                enemies.remove(enemy);
            }
        });
    }

    private void updateHealth() {
        playerHealth--;
        healthText.setText("Health: " + playerHealth);
        if (playerHealth <= 0) {
            System.out.println("Game Over!");
            // Add game over logic here
        }
    }
}