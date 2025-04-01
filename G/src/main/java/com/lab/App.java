package com.lab;

import com.lab.UI.MainMenu;
import com.lab.UI.Sound;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
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
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
        }

        // เล่นเพลงพื้นหลัง
        Sound.playBackgroundMusic();

        // เพิ่มโลโก้
        Image logoImage = new Image(getClass().getResource("/images/logo.png").toExternalForm());
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(200);
        logoImageView.setPreserveRatio(true);

        // ปุ่มเริ่มเกม
        Button startButton = new Button("START");
        startButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 40px; -fx-font-family: 'Comic Sans MS';");
        startButton.setOnAction(event -> {
            Sound.stopBackgroundMusic();
            MainMenu.showMainMenu(primaryStage);
        });

        // ปุ่มออกจากเกม
        Button exitButton = new Button("EXIT");
        exitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 40px; -fx-font-family: 'Comic Sans MS';");
        exitButton.setOnAction(event -> {
            Sound.stopBackgroundMusic();
            primaryStage.close();
        });

        // จัดวางปุ่มและโลโก้
        VBox buttonsVBox = new VBox(60, logoImageView, startButton, exitButton);
        buttonsVBox.setAlignment(Pos.CENTER_LEFT);
        buttonsVBox.setStyle("-fx-padding: 40; -fx-background-color: rgba(0, 0, 0, 0.5);");
        root.setLeft(buttonsVBox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Inferno&Tide");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}