package com.lab;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.animation.PathTransition;
import javafx.util.Duration;
import javafx.scene.shape.Path;

public class Enemy2 extends ImageView {
    private double health = 100;
    private double speed = 10;
    private PathTransition pathTransition;

    public Enemy2() {
        Image enemyImage = new Image(getClass().getResource("/images/enemy.png").toExternalForm());
        this.setImage(enemyImage);
        this.setFitWidth(40);
        this.setFitHeight(40);
    }

    public void moveAlongPath(Path path, Pane gamePane) {
        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(speed));
        pathTransition.setPath(path);
        pathTransition.setNode(this);
        pathTransition.setCycleCount(1);
        pathTransition.play();
        
        gamePane.getChildren().add(this);
    }

    public void takeDamage(double damage) {
        health -= damage;
        if (health <= 0) {
            this.setVisible(false);
            if (pathTransition != null) {
                pathTransition.stop();
            }
        }
    }

    public double getHealth() {
        return health;
    }
}