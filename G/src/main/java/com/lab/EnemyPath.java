package com.lab;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polyline;

import java.util.ArrayList;
import java.util.List;

public class EnemyPath {
    private List<Point2D> pathPoints;
    private double width;  // Maximum width of the screen
    private double height; // Maximum height of the screen

    public EnemyPath(double width, double height) {
        this.width = width;
        this.height = height;
        pathPoints = new ArrayList<>();
    }

    // Add a point to the path (with boundary check)
    public void addPoint(double x, double y) {
        // Ensure the point is within the screen bounds
        if (x >= 0 && x <= width && y >= 0 && y <= height) {
            pathPoints.add(new Point2D(x, y));
        } else {
            System.err.println("Point (" + x + ", " + y + ") is out of bounds and will not be added.");
        }
    }

    // Get the list of points
    public List<Point2D> getPathPoints() {
        return pathPoints;
    }

    // Generate a Polyline for visualization
    public Polyline getPathPolyline() {
        Polyline polyline = new Polyline();
        for (Point2D point : pathPoints) {
            polyline.getPoints().addAll(point.getX(), point.getY());
        }
        polyline.setStrokeWidth(2);
        polyline.setStyle("-fx-stroke: red; -fx-stroke-dash-array: 5 5;"); // Dashed red line
        return polyline;
    }

    // Generate a fixed path for the enemy
    public static EnemyPath generateEnemyPath(double width, double height) {
        EnemyPath enemyPath = new EnemyPath(width, height);

        // Starting point: middle-left
        enemyPath.addPoint(0, height / 2);

        // Fixed points along the path
        enemyPath.addPoint(200, height / 2);       // Move right
        enemyPath.addPoint(200, height - 200);    // Move down
        enemyPath.addPoint(width - 200, height - 200); // Move right
        enemyPath.addPoint(width - 200, 200);     // Move up

        // Ending point: middle-right
        enemyPath.addPoint(width, 200);

        return enemyPath;
    }
}