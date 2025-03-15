package game.src.main.java;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Projectile {
    private Circle bullet;
    private double speed = 5;
    private double x, y;

    public Projectile(double startX, double startY) {
        x = startX;
        y = startY;
        bullet = new Circle(x, y, 5, Color.RED);
    }

    public Circle getBullet() {
        return bullet;
    }

    public void move() {
        x += speed;
        bullet.setCenterX(x);
    }
}
