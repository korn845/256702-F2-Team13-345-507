package com.lab;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class GameMap {

    private Pane mapPane;

    public GameMap() {
        mapPane = new Pane();

        // Create the background of the map
        Rectangle background = new Rectangle(800, 600);
        background.setFill(Color.LIGHTGREEN); // Set the background color
        mapPane.getChildren().add(background);

        // Create a path for the game (example: a simple zigzag path)
        Line path1 = new Line(100, 100, 300, 100);
        Line path2 = new Line(300, 100, 300, 300);
        Line path3 = new Line(300, 300, 500, 300);
        Line path4 = new Line(500, 300, 500, 500);

        // Set path styles
        path1.setStroke(Color.BROWN);
        path1.setStrokeWidth(10);
        path2.setStroke(Color.BROWN);
        path2.setStrokeWidth(10);
        path3.setStroke(Color.BROWN);
        path3.setStrokeWidth(10);
        path4.setStroke(Color.BROWN);
        path4.setStrokeWidth(10);

        // Add paths to the map
        mapPane.getChildren().addAll(path1, path2, path3, path4);
    }

    public Pane getMapPane() {
        return mapPane;
    }
}