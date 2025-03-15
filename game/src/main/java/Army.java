package game.src.main.java;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    }

    public ImageView getView() {
        return towerView;
    }

    public Circle getRange() {
        return range;
    }
}
