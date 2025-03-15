package game.src.main.java;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy {
    private ImageView enemyView;
    private double speed = 2;
    private double x = 50, y = 50;

    public Enemy() {
        Image enemyImage = new Image("enemy.png");
        enemyView = new ImageView(enemyImage);
        enemyView.setFitWidth(40);
        enemyView.setFitHeight(40);
        enemyView.setX(x);
        enemyView.setY(y);
    }

    public ImageView getView() {
        return enemyView;
    }

    public void move() {
        x += speed; // ศัตรูเดินไปทางขวา
        enemyView.setX(x);
    }
}
