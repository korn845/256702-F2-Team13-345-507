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

public class Wave {

    private int currentWave = 1; // ตัวนับคลื่นปัจจุบัน
    private int playerHealth = 10; // พลังชีวิตของผู้เล่น
    private List<Circle> enemies = new ArrayList<>(); // รายการศัตรู

    public void spawnEnemyWave(Pane gamePane, Polyline path, int enemyCount) {
        for (int i = 0; i < enemyCount; i++) {
            Circle enemy = new Circle(10, Color.RED);
            enemies.add(enemy);
            gamePane.getChildren().add(enemy);

            // Delay each enemy spawn
            PauseTransition delay = new PauseTransition(Duration.seconds(i * 2));
            delay.setOnFinished(event -> startEnemyMovement(enemy, path, gamePane));
            delay.play();
        }
    }

    private void startEnemyMovement(Circle enemy, Polyline path, Pane gamePane) {
        PathTransition transition = new PathTransition();
        transition.setNode(enemy);
        transition.setPath(path);
        transition.setDuration(Duration.seconds(10));
        transition.setCycleCount(1);
        transition.setOnFinished(event -> {
            gamePane.getChildren().remove(enemy); // ลบศัตรูเมื่อถึงจุดสิ้นสุด
            playerHealth--; // ลดพลังชีวิตของผู้เล่น
            checkGameOver();
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
    }
}
