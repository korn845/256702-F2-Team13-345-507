package com.lab.UI;

import com.lab.GamePlay;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

    public static void showMainMenu(Stage primaryStage) {
        VBox menuLayout = new VBox(20); // ระยะห่างระหว่างปุ่ม
        menuLayout.setAlignment(Pos.CENTER);

        // เล่นเพลงพื้นหลัง
        Sound.playBackgroundMusic(); // เรียกใช้เพลงพื้นหลัง

        // ปุ่มเริ่มเกม
        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px;");
        startButton.setOnAction(event -> {
            GamePlay gamePlay = new GamePlay();
            try {
                gamePlay.start(primaryStage); // เริ่มเกม
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // ปุ่มออกจากเกม
        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px;");
        exitButton.setOnAction(event -> {
            primaryStage.close(); // ปิดโปรแกรม
        });

        // เพิ่มปุ่มทั้งหมดลงใน VBox
        menuLayout.getChildren().addAll(startButton, exitButton);

        // สร้าง Scene สำหรับเมนูหลัก
        Scene mainMenuScene = new Scene(menuLayout, 800, 600);
        primaryStage.setScene(mainMenuScene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }
}
