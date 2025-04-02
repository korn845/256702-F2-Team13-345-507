package com.lab;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.geometry.Point2D;

public class Bulletss {
    private List<Bullet> bulletList = new ArrayList<>();

    public void addNewBullet(double startX, double startY, double destX, double destY) {
        bulletList.add(new Bullet(startX, startY, destX, destY));
    }

    public void updateBullets(Pane gamePane) {
        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            bullet.move();
            Circle bulletCircle = new Circle(bullet.location.getX(), bullet.location.getY(), 5, Color.RED);
            gamePane.getChildren().add(bulletCircle);

            // Collision detection logic here
            // If collision detected, remove bullet
            // gamePane.getChildren().remove(bulletCircle);
            // bulletList.remove(i);
        }
    }

    private class Bullet {
        double startX, startY, destX, destY;
        double dx, dy, speed = 5;
        Point2D location;

        public Bullet(double startX, double startY, double destX, double destY) {
            this.startX = startX;
            this.startY = startY;
            this.destX = destX;
            this.destY = destY;
            this.location = new Point2D(startX, startY);
            recalculateVector(destX, destY);
        }

        private void recalculateVector(double destX, double destY) {
            double rad = Math.atan2(destX - startX, startY - destY);
            this.dx = Math.sin(rad) * speed;
            this.dy = -Math.cos(rad) * speed;
        }

        public void move() {
            location = location.add(dx, dy);
        }

        public void launch(Pane gamePane) {
            // Implement launch logic here if needed
        }
    }
}
