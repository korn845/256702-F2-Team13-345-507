package com.lab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        // สร้างปุ่ม StartButton และเพิ่มลงใน root pane
        StartButton startButton = new StartButton(primaryStage);
        root.getChildren().add(startButton.getButton());

        // สร้างปุ่ม settingButton และเพิ่มลงใน root pane (ถ้าต้องการ)
        // StartButton settingButton = new StartButton(primaryStage);
        // root.getChildren().add(settingButton.getButton());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Inferno&Tide"); // หรือ "Primary View"
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}