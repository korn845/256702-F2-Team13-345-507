package com.lab;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameMap {
    private Pane mapPane;

    public GameMap() {
        mapPane = new Pane();

        // Example: Add a background to the map
        Rectangle background = new Rectangle(800, 600);
        background.setFill(Color.LIGHTGREEN);
        mapPane.getChildren().add(background);
    }

    public Pane getMapPane() {
        return mapPane;
    }
}