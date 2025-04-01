package com.lab;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.scene.paint.Color;
import javafx.animation.PathTransition;
import javafx.util.Duration;

public class Enemy {
    private ImageView enemyView; // แสดงภาพของศัตรู
    private int health; // พลังชีวิตของศัตรู
    private double speed; // ความเร็วของศัตรู

    public Enemy(int health, double speed) {
        this.health = health;
        this.speed = speed;

        // โหลดภาพศัตรู
        Image enemyImage = new Image(getClass().getResource("/images/enemy.png").toExternalForm());
        enemyView = new ImageView(enemyImage);
        enemyView.setFitWidth(40);
        enemyView.setFitHeight(40);
    }

    public ImageView getView() {
        return enemyView;
    }

    // Getter สำหรับพลังชีวิตของศัตรู
    public int getHealth() {
        return health;
    }

    // ลดพลังชีวิตของศัตรู
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println("Enemy defeated!");
            enemyView.setVisible(false); // ซ่อนศัตรูเมื่อถูกกำจัด
        }
    }

    // เพิ่มศัตรูลงใน gamePane และทำให้เดินตามเส้นทาง
    public void spawnEnemy(Pane gamePane, Polyline path) {
        gamePane.getChildren().add(enemyView);

        PathTransition transition = new PathTransition();
        transition.setNode(enemyView);
        transition.setPath(path);
        transition.setDuration(Duration.seconds(speed)); // ใช้ความเร็วที่กำหนด
        transition.setCycleCount(1); // เดินตามเส้นทางเพียงครั้งเดียว
        transition.setOnFinished(event -> {
            System.out.println("Enemy reached the end!");
            gamePane.getChildren().remove(enemyView); // ลบศัตรูเมื่อถึงจุดสิ้นสุด
        });
        transition.play();
    }

    // สร้างเส้นทางสำหรับศัตรู
    public static Polyline createEnemyPath() {
        Polyline path = new Polyline();
        path.getPoints().addAll(
            50.0, 50.0,  // Starting point
            200.0, 200.0, // First turn
            400.0, 400.0  // Ending point
        );
        path.setStroke(Color.RED);
        path.setStrokeWidth(3);
        return path;
    }
}
