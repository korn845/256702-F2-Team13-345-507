package com.lab.UI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Hp {

    private Text healthText;
    private Text waveText;
    private Text baseHealthText;
    private int playerHealth;
    private int currentWave;
    private int baseHealth;

    public Hp(Pane gamePane, int initialHealth, int initialWave, int initialBaseHealth) {
        this.playerHealth = initialHealth;
        this.currentWave = initialWave;
        this.baseHealth = initialBaseHealth;

        // สร้างข้อความสำหรับแสดงพลังชีวิต
        healthText = new Text("Health: " + playerHealth);
        healthText.setFill(Color.WHITE);
        healthText.setStyle("-fx-font-size: 20px;");
        healthText.setX(10);
        healthText.setY(20);
        gamePane.getChildren().add(healthText);

        // สร้างข้อความสำหรับแสดงคลื่น
        waveText = new Text("Wave: " + currentWave);
        waveText.setFill(Color.WHITE);
        waveText.setStyle("-fx-font-size: 20px;");
        waveText.setX(10);
        waveText.setY(50);
        gamePane.getChildren().add(waveText);

        // สร้างข้อความสำหรับแสดงพลังชีวิตของฐาน
        baseHealthText = new Text("Base Health: " + baseHealth);
        baseHealthText.setFill(Color.WHITE);
        baseHealthText.setStyle("-fx-font-size: 20px; -fx-font-family: 'Comic Sans MS';");
        baseHealthText.setX(10);
        baseHealthText.setY(80);
        gamePane.getChildren().add(baseHealthText);
    }

    // อัปเดตพลังชีวิต
    public void updateHealth(int health) {
        this.playerHealth = health;
        healthText.setText("Health: " + playerHealth);
    }

    // อัปเดตคลื่น
    public void updateWave(int wave) {
        this.currentWave = wave;
        waveText.setText("Wave: " + currentWave);
    }

    // อัปเดตพลังชีวิตของฐาน
    public void updateBaseHealth(int health) {
        this.baseHealth = health;
        baseHealthText.setText("Base Health: " + baseHealth);

        if (baseHealth <= 0) {
            System.out.println("Game Over! The base has been destroyed.");
            // เพิ่ม logic สำหรับแสดงหน้าจอ Game Over
        }
    }

    // รับค่าพลังชีวิตของฐานปัจจุบัน
    public int getBaseHealth() {
        return baseHealth;
    }

    // รับค่าพลังชีวิตของผู้เล่น
    public int getPlayerHealth() {
        return playerHealth;
    }

    // รับค่าคลื่นปัจจุบัน
    public int getCurrentWave() {
        return currentWave;
    }
}
