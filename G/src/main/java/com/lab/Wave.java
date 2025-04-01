package com.lab;

import javafx.animation.PauseTransition;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;

public class Wave {
    private int currentWave = 1;
    private int playerHealth = 10;
    private List<Circle> enemies = new ArrayList<>();
    private List<javafx.scene.image.ImageView> enemyViews = new ArrayList<>();
    private double enemyHealth = 1.0;
    private double baseSpeed = 30.0;

    public List<javafx.scene.image.ImageView> getEnemyViews() {
        return enemyViews;
    }

    public List<Circle> getEnemies() {
        return enemies;
    }

    public void spawnEnemyWave(Pane gamePane, Polyline path, int enemyCount) {
        // คำนวณเลือดและความเร็วตามจำนวน wave
        enemyHealth = 1.0 + (0.05 * (currentWave - 1)); // เพิ่มเลือด 5% ต่อ wave
        double currentSpeed = baseSpeed - ((currentWave / 10) * 0.2 * baseSpeed); // เพิ่มความเร็ว 20% ทุก 10 wave
        currentSpeed = Math.max(currentSpeed, 2.0); // กำหนดความเร็วขั้นต่ำ

        for (int i = 0; i < enemyCount; i++) {
            Circle enemy = new Circle(10, Color.RED);
            enemy.setUserData(enemyHealth); // เก็บค่าเลือดไว้ใน enemy
            enemies.add(enemy);
            gamePane.getChildren().add(enemy);
            PauseTransition delay = new PauseTransition(Duration.seconds(i * 2));
            double finalSpeed = currentSpeed;
            delay.setOnFinished(event -> startEnemyMovement(enemy, path, gamePane, finalSpeed));
            delay.play();
        }
    }

    private void startNextWaveCountdown(Pane gamePane, Polyline path, int enemyCount) {
        Text countdownText = new Text("Next wave in 5 seconds...");
        countdownText.setFill(Color.WHITE);
        countdownText.setLayoutX(350);
        countdownText.setLayoutY(300);
        gamePane.getChildren().add(countdownText);

        Timeline countdownTimeline = new Timeline();
        countdownTimeline.setCycleCount(5);
        countdownTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            int remainingSeconds = Integer.parseInt(countdownText.getText().replaceAll("[^0-9]", ""));
            remainingSeconds--;
            countdownText.setText("Next wave in " + remainingSeconds + " seconds...");
        }));

        countdownTimeline.setOnFinished(event -> {
            gamePane.getChildren().remove(countdownText);
            incrementWave();
            spawnEnemyWave(gamePane, path, enemyCount);
        });

        countdownTimeline.play();
    }

    private void startEnemyMovement(Circle enemy, Polyline path, Pane gamePane, double speed) {
        PathTransition transition = new PathTransition();
        transition.setNode(enemy);
        transition.setPath(path);
        transition.setDuration(Duration.seconds(speed));
        transition.setCycleCount(1);
        transition.setOnFinished(event -> {
            gamePane.getChildren().remove(enemy);
            enemies.remove(enemy);
            playerHealth--;
            checkGameOver();
            if (enemies.isEmpty()) {
                startNextWaveCountdown(gamePane, path, enemies.size());
            }
        });
        transition.play();
    }

    private void checkGameOver() {
        if (playerHealth <= 0) {
            System.out.println("Game Over!");
            // เพิ่ม logic สำหรับแสดงหน้าจอ Game Over
        }
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public void incrementWave() {
        currentWave++;
        System.out.println("Wave " + currentWave + 
                         " - Enemy Health: " + String.format("%.2f", enemyHealth) + 
                         "x - Speed: " + String.format("%.2f", (1 + (currentWave / 10) * 0.2)) + "x");
    }

    // เพิ่มเมธอดสำหรับดึงค่าเลือดของศัตรู
    public double getEnemyHealth() {
        return enemyHealth;
    }
}
