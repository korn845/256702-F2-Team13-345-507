package com.lab;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import java.util.List;
import com.lab.Projectile;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.scene.shape.Line;

public class Army {
    private ImageView armyView; // Display image of Army
    private Circle range; // Shooting range of Army
    private Timeline shootingTimer;
    private Pane gamePane;  // Reference to game pane
    private List<ImageView> enemies;  // Correct type for enemies list
    private static final double SHOOTING_RANGE = 200;
    private static final long SHOOTING_INTERVAL = 500_000_000; // 0.5 seconds in nanoseconds
    private double x; // Add x position
    private double y; // Add y position
// Remove duplicate field declaration since it's already declared later in the class

    // Modified constructor to receive enemies and gamePane
    public Army(double x, double y, List<ImageView> enemies, Pane gamePane) {
        this.x = x; // Initialize x position
        this.y = y; // Initialize y position
        this.enemies = enemies;
        this.gamePane = gamePane;
        // Load Army image
        Image armyImage = new Image(getClass().getResource("/images/army.png").toExternalForm());
        armyView = new ImageView(armyImage);
        armyView.setFitWidth(50);
        armyView.setFitHeight(50);
        armyView.setX(x);
        armyView.setY(y);

        // Create shooting range of Army
        range = new Circle(x + 25, y + 25, 100);
        range.setFill(Color.TRANSPARENT);
        range.setStroke(Color.BLUE);
        range.setVisible(false);

        // Mouse event handlers to show/hide the range circle
        armyView.setOnMouseEntered(event -> range.setVisible(true));
        armyView.setOnMouseExited(event -> range.setVisible(false));
        
        // Add shooting interval
        shootingTimer = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> attackEnemies(enemies, gamePane))
        );
        shootingTimer.setCycleCount(Timeline.INDEFINITE);
        shootingTimer.play();
    }

    private long lastShotTime = 0;

    // Add Army to gamePane
    public void placeArmy(Pane gamePane) {
        gamePane.getChildren().addAll(armyView, range);
    }

    // Attack enemies within range
    public void attackEnemies(List<javafx.scene.image.ImageView> enemies, Pane gamePane) {
        for (javafx.scene.image.ImageView enemy : enemies) {
            if (enemy.isVisible() && range.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                System.out.println("Enemy in range - firing bullet!");
                Projectile projectile = new Projectile(
                    armyView.getX() + armyView.getFitWidth() / 2, 
                    armyView.getY() + armyView.getFitHeight() / 2, 
                    enemy.getX() + enemy.getFitWidth() / 2, 
                    enemy.getY() + enemy.getFitHeight() / 2,
                    new Circle(enemy.getX() + enemy.getFitWidth() / 2, enemy.getY() + enemy.getFitHeight() / 2, 15)
                );
                projectile.launch(gamePane);
            }
        }
    }

    // Getter for Army View
    public ImageView getView() {
        return armyView;
    }

    // Getter for shooting range
    public Circle getRange() {
        return range;
    }
    
    // Add cleanup method
    public void stopShooting() {
        shootingTimer.stop();
    }

    public void shootEnemies(List<ImageView> enemies) {
        long currentTime = System.nanoTime();
        if (currentTime - lastShotTime < SHOOTING_INTERVAL) {
            return;
        }

        for (ImageView enemy : enemies) {
            if (enemy != null && isInRange(enemy)) {
                // Create bullet
                Circle bullet = new Circle(5, Color.RED);
                bullet.setCenterX(x + 25);
                bullet.setCenterY(y + 25);
                gamePane.getChildren().add(bullet);

                // Animate bullet
                PathTransition bulletTransition = new PathTransition();
                bulletTransition.setNode(bullet);
                bulletTransition.setDuration(Duration.millis(200));
                bulletTransition.setPath(new Line(x + 25, y + 25, 
                    enemy.getX() + 15, enemy.getY() + 15));
                bulletTransition.setOnFinished(event -> {
                    gamePane.getChildren().remove(bullet);
                    enemies.remove(enemy);
                    gamePane.getChildren().remove(enemy);
                });
                bulletTransition.play();

                lastShotTime = currentTime;
                break;
            }
        }
    }

    private boolean isInRange(ImageView enemy) {
        double dx = enemy.getX() - x;
        double dy = enemy.getY() - y;
        return Math.sqrt(dx * dx + dy * dy) <= SHOOTING_RANGE;
    }
}
