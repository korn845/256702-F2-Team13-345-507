package com.lab;

import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Projectile {
    private Circle bullet;
    private Circle targetEnemy;

    public Projectile(double startX, double startY, double targetX, double targetY, Circle enemy) {
        bullet = new Circle(5, Color.BLUE);
        this.targetEnemy = enemy;
        
        bullet.setCenterX(startX);
        bullet.setCenterY(startY);

        Line path = new Line(startX, startY, targetX, targetY);
        PathTransition transition = new PathTransition(Duration.seconds(0.8), path, bullet);
        
        // Add collision detection during bullet movement
        javafx.animation.AnimationTimer collisionChecker = new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                if (targetEnemy.isVisible() && bullet.isVisible() && 
                    bullet.getBoundsInParent().intersects(targetEnemy.getBoundsInParent())) {
                    // Handle collision
                    Pane parentPane = (Pane) targetEnemy.getParent();
                    parentPane.getChildren().remove(targetEnemy);
                    parentPane.getChildren().remove(bullet);
                    targetEnemy.setVisible(false);
                    bullet.setVisible(false);
                    this.stop();
                    transition.stop();
                }
            }
        };
        
        collisionChecker.start();
        transition.play();
    }

    public void launch(Pane gamePane) {
        gamePane.getChildren().add(bullet);
    }
}
