package com.lab;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Army {
    private ImageView towerView;
    private Circle range; // ระยะยิงของป้อม

    public Army(double x, double y) {
        Image towerImage = new Image("tower.png");
        towerView = new ImageView(towerImage);
        towerView.setFitWidth(50);
        towerView.setFitHeight(50);
        towerView.setX(x);
        towerView.setY(y);

        range = new Circle(x + 25, y + 25, 100); // กำหนดระยะยิง
        range.setFill(Color.TRANSPARENT); // Make the circle transparent
        range.setStroke(Color.RED); // Add a red border to the circle
        range.setVisible(false); // Initially hide the range

        // Show the range when the mouse enters the tower
        towerView.setOnMouseEntered(event -> range.setVisible(true));

        // Hide the range when the mouse exits the tower
        towerView.setOnMouseExited(event -> range.setVisible(false));
    }

    public ImageView getView() {
        return towerView;
    }

    public Circle getRange() {
        return range;
    }
}
