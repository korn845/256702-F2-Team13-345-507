package com.lab;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Hp {
    private Text healthText;
    private int baseHealth;

    public Hp(Pane gamePane, int initialHealth, int initialBaseHealth) {
        this.baseHealth = initialBaseHealth;

        // สร้างข้อความแสดง Base Health ตรงกลางด้านบน
        healthText = new Text("Base Health: " + baseHealth);
        healthText.setFill(Color.WHITE);
        healthText.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        // คำนวณตำแหน่งกึ่งกลาง
        double textWidth = healthText.getLayoutBounds().getWidth();
        healthText.setX((800 - textWidth) / 2);  // 800 คือความกว้างของ gamePane
        healthText.setY(30);
        
        gamePane.getChildren().add(healthText);
    }

    public void updateBaseHealth(int health) {
        this.baseHealth = health;
        healthText.setText("Base Health: " + baseHealth);

        // อัพเดทตำแหน่งกึ่งกลางเมื่อข้อความเปลี่ยน
        double textWidth = healthText.getLayoutBounds().getWidth();
        healthText.setX((800 - textWidth) / 2);

        if (baseHealth <= 0) {
            System.out.println("Game Over! The base has been destroyed.");
        }
    }

    public int getBaseHealth() {
        return baseHealth;
    }
}
