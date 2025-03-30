package com.lab;

import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.animation.PathTransition;
import javafx.util.Duration;

public class GameMap {
    private StackPane mapPane;

    public GameMap() {
        mapPane = new StackPane();

        // สร้างปุ่มสำหรับโหมด Easy, Normal, Hard
        Pane easyPane = createLabelPane("Easy", 40);
        Pane normalPane = createLabelPane("Normal", 40);
        Pane hardPane = createLabelPane("Hard", 40);

        // เพิ่มการคลิกที่ปุ่ม Easy
        easyPane.setOnMouseClicked(event -> startGame("Easy"));

        // เพิ่มการคลิกที่ปุ่ม Normal
        normalPane.setOnMouseClicked(event -> startGame("Normal"));

        // เพิ่มการคลิกที่ปุ่ม Hard
        hardPane.setOnMouseClicked(event -> startGame("Hard"));

        // เพิ่มปุ่มทั้งหมดลงใน VBox
        VBox vbox = new VBox(20, easyPane, normalPane, hardPane); // 20 คือระยะห่างระหว่างปุ่ม
        vbox.setAlignment(Pos.CENTER);
        mapPane.getChildren().add(vbox);
    }

    private Pane createLabelPane(String text, int fontSize) {
        Label label = new Label(text);
        label.setFont(new Font("Times New Roman", fontSize));
        label.setTextFill(Color.WHITE);

        Pane pane = new Pane();
        pane.getChildren().add(label);

        // จัดตำแหน่งข้อความให้อยู่ตรงกลาง
        label.layoutXProperty().bind(pane.widthProperty().subtract(label.widthProperty()).divide(2));
        label.layoutYProperty().bind(pane.heightProperty().subtract(label.heightProperty()).divide(2));

        pane.setPrefSize(200, 50); // ขนาดของปุ่ม
        pane.setStyle("-fx-border-color: white; -fx-border-width: 0.5; -fx-background-color: rgba(74, 74, 74, 0.4);");
        return pane;
    }

    private void startGame(String difficulty) {
        GamePlay gamePlay = new GamePlay();
        Scene gameplayScene = new Scene(gamePlay.getGamePane(), 800, 600);
        Stage primaryStage = (Stage) mapPane.getScene().getWindow();
        primaryStage.setScene(gameplayScene);

        // สร้างเส้นทางศัตรู
        Polyline enemyPath = PathGenerator.generateEnemyPath(800, 600).getPathPolyline();
        gamePlay.getGamePane().getChildren().add(enemyPath);

        // เพิ่มลอจิกสำหรับการเริ่มเกมตามระดับความยาก
        System.out.println("Game started with difficulty: " + difficulty);
    }

    public Pane getMapPane() {
        return mapPane;
    }

    public static void main(String[] args) {
        BorderPane root = new BorderPane();
        Button startButton = new Button("Start");
        root.setTop(startButton);

        startButton.setOnAction(event -> {
            GameMap gameMode = new GameMap();
            root.setCenter(gameMode.getMapPane());
        });

        Scene scene = new Scene(root, 800, 600);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tower Defense Game");
        primaryStage.show();
    }
}