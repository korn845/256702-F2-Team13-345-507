package com.lab;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import java.util.List;
import com.lab.Bulletss; // Update import to use Bullets instead of Projectile
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.scene.shape.Line;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;  // Add this import

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
    private Set<ImageView> targetedEnemies = new HashSet<>(); // Track targeted enemies
    private Map<ImageView, Double[]> previousPositions = new HashMap<>(); // Track previous positions
    private Bulletss bullets = new Bulletss(); // Initialize Bullets class

    // Modified constructor to receive enemies and gamePane
    // Keep only one constructor and combine the functionality
    public Army(double x, double y, List<ImageView> enemies, Pane gamePane) {
        this.x = x;
        this.y = y;
        this.enemies = enemies;
        this.gamePane = gamePane;
        this.enemies2 = new ArrayList<>(); // Initialize enemies2 list
        
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
        // Update the shooting timer to use shootEnemies instead of attackMovingObjects
        shootingTimer = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> shootEnemies(enemies))
        );
        shootingTimer.setCycleCount(Timeline.INDEFINITE);
        shootingTimer.play();
    }

    private long lastShotTime = 0;

    // Add Army to gamePane
    public void placeArmy(Pane gamePane) {
        gamePane.getChildren().addAll(armyView, range);
    }

    // NullPointerException in Projectile Class
    
    // Attack enemies within range
    // Add new field for Enemy2 list
    private List<Enemy2> enemies2 = new ArrayList<>();
    
    // Modify constructor to include Enemy2 list
    // Remove the first constructor and keep only this one
    public Army(double x, double y, List<ImageView> enemies, List<Enemy2> enemies2, Pane gamePane) {
        this.enemies2 = enemies2;
        this.x = x;
        this.y = y;
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
        // Update shooting timer to handle both enemy types
        shootingTimer = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> {
                shootEnemies(enemies);
                attackEnemy2(enemies2, gamePane);
            })
        );
        shootingTimer.setCycleCount(Timeline.INDEFINITE);
        shootingTimer.play();
    }

    // Add method to register Enemy2 instances
    public void addEnemy2(Enemy2 enemy) {
        if (!enemies2.contains(enemy)) {
            enemies2.add(enemy);
        }
    }

    // Add new method to attack Enemy2
    public void attackEnemy2(List<Enemy2> enemies2, Pane gamePane) {
        for (Enemy2 enemy : enemies2) {
            if (enemy.isVisible() && range.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                System.out.println("Enemy2 in range - firing bullet!");
                bullets.addNewBullet(
                    armyView.getX() + armyView.getFitWidth() / 2,
                    armyView.getY() + armyView.getFitHeight() / 2,
                    enemy.getX() + enemy.getFitWidth() / 2,
                    enemy.getY() + enemy.getFitHeight() / 2
                );
                
                // Handle damage to Enemy2
                enemy.takeDamage(20);
                if (enemy.getHealth() <= 0) {
                    gamePane.getChildren().remove(enemy);
                    enemies2.remove(enemy);
                    break;
                }
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
            if (enemy != null && isInRange(enemy)) { // Check if enemy is in range
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
                    // Check for collision
                    if (bullet.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                        gamePane.getChildren().remove(bullet);
                        enemies.remove(enemy);
                        gamePane.getChildren().remove(enemy);
                    }
                });
                bulletTransition.play();

                lastShotTime = currentTime;
                break; // Stop after shooting one enemy
            }
        }
    }

    private boolean isInRange(ImageView enemy) {
        double dx = enemy.getX() - x;
        double dy = enemy.getY() - y;
        return Math.sqrt(dx * dx + dy * dy) <= SHOOTING_RANGE;
    }
}
