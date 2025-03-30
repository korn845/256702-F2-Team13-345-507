package com.lab;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public class Projectile {
    private Circle bullet;
    private double speed;
    private double directionX, directionY; // ทิศทางการเคลื่อนที่
    private double x, y;

    public Projectile(double startX, double startY, double targetX, double targetY, double speed) {
        this.x = startX;
        this.y = startY;
        this.speed = speed;

        // คำนวณทิศทางการเคลื่อนที่
        double deltaX = targetX - startX;
        double deltaY = targetY - startY;
        double magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        this.directionX = deltaX / magnitude;
        this.directionY = deltaY / magnitude;

        // สร้างกระสุน
        bullet = new Circle(x, y, 5, Color.RED);
    }

    public Circle getBullet() {
        return bullet;
    }

    public void move() {
        // อัปเดตตำแหน่งกระสุน
        x += directionX * speed;
        y += directionY * speed;
        bullet.setCenterX(x);
        bullet.setCenterY(y);
    }

    public boolean isOutOfBounds(double width, double height) {
        // ตรวจสอบว่ากระสุนออกนอกขอบเขตหรือไม่
        return x < 0 || x > width || y < 0 || y > height;
    }

    private void shootProjectile(Pane gamePane, double startX, double startY, double targetX, double targetY) {
        Projectile projectile = new Projectile(startX, startY, targetX, targetY, 5);
        gamePane.getChildren().add(projectile.getBullet());

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                projectile.move();
                if (projectile.isOutOfBounds(800, 600)) {
                    gamePane.getChildren().remove(projectile.getBullet());
                    stop();
                }
            }
        };
        timer.start();
    }
}
