package com.lab;

import com.lab.UI.Sound;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // แสดงหน้าเมนูหลัก
        showMainMenu(primaryStage);
    }

    private void showMainMenu(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // ตั้งค่าพื้นหลัง
        try {
            Image backgroundImage = new Image(getClass().getResource("/images/BG_GAME.jpg").toExternalForm());
            BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
            );
            root.setBackground(new Background(background));
        } catch (NullPointerException e) {
            System.err.println("Error: Background image not found. Ensure the image is in the resources folder.");
        }

        // เล่นเพลงพื้นหลัง
        Sound.playBackgroundMusic();

        // เพิ่มโลโก้
        Image logoImage = new Image(getClass().getResource("/images/logo.png").toExternalForm()); // ใส่เส้นทางของไฟล์โลโก้
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(200); // กำหนดความกว้างของโลโก้
        logoImageView.setPreserveRatio(true); // รักษาอัตราส่วนของภาพ

        // ปุ่มเริ่มเกม
        Button startButton = new Button("START");
        startButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 40px; -fx-font-family: 'Comic Sans MS';");
        startButton.setOnAction(event -> {
            Sound.stopBackgroundMusic(); // หยุดเพลงพื้นหลังเมื่อเริ่มเกม
            showGameScreen(primaryStage);
        });

        // ปุ่มออกจากเกม
        Button exitButton = new Button("EXIT");
        exitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 40px; -fx-font-family: 'Comic Sans MS';");
        exitButton.setOnAction(event -> {
            Sound.stopBackgroundMusic(); // หยุดเพลงพื้นหลังเมื่อปิดโปรแกรม
            primaryStage.close();
        });

        // เพิ่มโลโก้และปุ่มทั้งหมดลงใน VBox
        VBox buttonsVBox = new VBox(60, logoImageView, startButton, exitButton);
        buttonsVBox.setAlignment(Pos.CENTER_LEFT);
        buttonsVBox.setStyle("-fx-padding: 40; -fx-background-color: rgba(0, 0, 0, 0.5);");
        root.setLeft(buttonsVBox);

        Scene mainMenuScene = new Scene(root, 800, 600);
        primaryStage.setTitle("Inferno&Tide");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png"))); // ตั้งค่าไอคอนของหน้าต่าง
        primaryStage.setResizable(false);
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

    private void showGameScreen(Stage primaryStage) {
        // โค้ดสำหรับหน้าจอเกม
    }

    public static void main(String[] args) {
        launch(args);
    }
}