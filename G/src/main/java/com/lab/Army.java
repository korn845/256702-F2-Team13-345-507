package com.lab;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import java.util.List;

public class Army {
    private ImageView armyView; // แสดงภาพของ Army
    private Circle range; // ระยะยิงของ Army

    public Army(double x, double y) {
        // โหลดภาพ Army
        Image armyImage = new Image(getClass().getResource("/images/army.png").toExternalForm());
        armyView = new ImageView(armyImage);
        armyView.setFitWidth(50);
        armyView.setFitHeight(50);
        armyView.setX(x);
        armyView.setY(y);

        // สร้างระยะยิงของ Army
        range = new Circle(x + 25, y + 25, 100); // กำหนดระยะยิง
        range.setFill(Color.TRANSPARENT); // ทำให้วงกลมโปร่งใส
        range.setStroke(Color.BLUE); // เพิ่มเส้นขอบสีฟ้า
        range.setVisible(false); // ซ่อนระยะยิงเริ่มต้น

        // แสดงระยะยิงเมื่อเมาส์ชี้ที่ Army
        armyView.setOnMouseEntered(event -> range.setVisible(true));

        // ซ่อนระยะยิงเมื่อเมาส์ออกจาก Army
        armyView.setOnMouseExited(event -> range.setVisible(false));
    }

    // เพิ่ม Army ลงใน gamePane
    public void placeArmy(Pane gamePane) {
        gamePane.getChildren().addAll(armyView, range);
    }

    // โจมตีศัตรูที่อยู่ในระยะ
    public void attackEnemies(List<Circle> enemies) {
        for (Circle enemy : enemies) {
            if (range.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                System.out.println("Enemy hit by Army!");
                // เพิ่ม logic เพื่อลดพลังชีวิตของศัตรูหรือกำจัดศัตรู
                enemy.setFill(Color.GRAY); // ตัวอย่าง: เปลี่ยนสีศัตรูเมื่อถูกโจมตี
            }
        }
    }

    // Getter สำหรับ Army View
    public ImageView getView() {
        return armyView;
    }

    // Getter สำหรับระยะยิง
    public Circle getRange() {
        return range;
    }
}
