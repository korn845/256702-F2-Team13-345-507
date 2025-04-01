package com.lab;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polyline;

public class GameLevel {
    private Pane levelPane;
    private Polyline enemyPath;
    private static final int TILE_SIZE = 40;
    private static final int MAP_WIDTH = 20; // 800/40
    private static final int MAP_HEIGHT = 15; // 600/40
    
    public GameLevel() {
        levelPane = new Pane();
        createGrid();
        createPath();
    }

    private void createGrid() {
        // Create grid of tiles
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                Rectangle tile = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                tile.setFill(Color.TRANSPARENT);
                tile.setStroke(Color.GRAY);
                tile.setStrokeWidth(0.5);
                
                // Add click event for tower placement
                tile.setOnMouseClicked(event -> {
                    if (canPlaceTower(tile.getX(), tile.getY())) {
                        tile.setFill(Color.LIGHTBLUE); // Indicate tower placement
                    }
                });
                
                levelPane.getChildren().add(tile);
            }
        }
    }

    private void createPath() {
        // Create a more complex path for enemies
        enemyPath = new Polyline(
            0, 300,      // Start
            200, 300,    // First turn
            200, 100,    // Up
            400, 100,    // Right
            400, 500,    // Down
            600, 500,    // Right
            600, 300,    // Up
            800, 300     // End
        );
        enemyPath.setStroke(Color.DARKGRAY);
        enemyPath.setStrokeWidth(2);
        levelPane.getChildren().add(enemyPath);
    }

    private boolean canPlaceTower(double x, double y) {
        // Check if the position is near the enemy path
        double pathDistance = 40; // Minimum distance from path
        for (int i = 0; i < enemyPath.getPoints().size() - 2; i += 2) {
            double pathX = enemyPath.getPoints().get(i);
            double pathY = enemyPath.getPoints().get(i + 1);
            if (Math.abs(x - pathX) < pathDistance && Math.abs(y - pathY) < pathDistance) {
                return false;
            }
        }
        return true;
    }

    public Pane getLevelPane() {
        return levelPane;
    }

    public Polyline getEnemyPath() {
        return enemyPath;
    }
}