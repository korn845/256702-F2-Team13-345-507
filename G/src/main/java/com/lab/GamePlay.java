package com.lab;

import com.lab.UI.Hp;
import com.lab.UI.MainMenu; // เพิ่มการนำเข้าคลาส MainMenu
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.image.Image;

public class GamePlay extends Application {
    private Pane gamePane;
    private Hp hp;

    public GamePlay() {
        gamePane = new Pane();

        // เพิ่มภาพพื้นหลังให้กับ gamePane
        try {
            Image backgroundImage = new Image(getClass().getResource("/images/BG_GAMEPLAY.jpg").toExternalForm());
            BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
            );
            gamePane.setBackground(new Background(background));
        } catch (NullPointerException e) {
            System.err.println("Error: Background image not found. Ensure the image is in the resources folder.");
        }

        // สร้างเส้นทางศัตรู
        EnemyPath enemyPath = PathGenerator.generateEnemyPath(800, 600);
        Polyline pathPolyline = enemyPath.getPathPolyline();
        gamePane.getChildren().add(pathPolyline);

        // เริ่มต้นคลื่นแรก
        hp = new Hp(gamePane, 10, 1, 5); // เริ่มต้นด้วยพลังชีวิต 10, คลื่นที่ 1, และพลังชีวิตฐาน 5
        spawnEnemyWave(pathPolyline, 5); // เริ่มต้นด้วยศัตรู 5 ตัว
    }

    private void spawnEnemyWave(Polyline path, int enemyCount) {
        for (int i = 0; i < enemyCount; i++) {
            Circle enemy = new Circle(10, Color.RED); // ศัตรูเป็นวงกลมสีแดง
            gamePane.getChildren().add(enemy);

            PathTransition transition = new PathTransition();
            transition.setNode(enemy);
            transition.setPath(path);
            transition.setDuration(Duration.seconds(5 + i)); // ปรับความเร็วของการเคลื่อนที่
            transition.setCycleCount(1); // เดินทางเพียงครั้งเดียว
            transition.setOnFinished(event -> {
                gamePane.getChildren().remove(enemy); // ลบศัตรูเมื่อถึงจุดสิ้นสุด
                hp.updateBaseHealth(hp.getBaseHealth() - 1); // ลดพลังชีวิตของฐานเมื่อศัตรูถึงจุดสิ้นสุด
            });
            transition.play();
        }
    }

    public Pane getGamePane() {
        return gamePane;
    }

    @Override
    public void start(Stage primaryStage) {
        gamePane = new Pane();

        // สร้าง UI สำหรับแสดงพลังชีวิตและคลื่น
        hp = new Hp(gamePane, 10, 1, 5); // เริ่มต้นด้วยพลังชีวิต 10, คลื่นที่ 1, และพลังชีวิตฐาน 5

        // อัปเดตพลังชีวิตเมื่อศัตรูถึงจุดสิ้นสุด
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // ตัวอย่าง: เพิ่มคลื่นเมื่อคลื่นปัจจุบันเสร็จสิ้น
                if (hp.getBaseHealth() > 0) {
                    hp.updateWave(hp.getCurrentWave() + 1);
                }

                // หยุดเกมเมื่อพลังชีวิตฐานหมด
                if (hp.getBaseHealth() <= 0) {
                    System.out.println("Game Over!");
                    stop();

                    // แสดงข้อความ Game Over
                    Text gameOverText = new Text("Game Over!");
                    gameOverText.setFill(Color.RED);
                    gameOverText.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;");
                    gameOverText.setX(300);
                    gameOverText.setY(300);
                    gamePane.getChildren().add(gameOverText);

                    // หน่วงเวลา 3 วินาทีก่อนกลับไปยังเมนูหลัก
                    PauseTransition delay = new PauseTransition(Duration.seconds(3));
                    delay.setOnFinished(event -> MainMenu.showMainMenu(primaryStage)); // เรียก MainMenu.showMainMenu
                    delay.play();
                }
            }
        };
        timer.start();

        Scene scene = new Scene(gamePane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tower Defense");
        primaryStage.setResizable(false); // ล็อกขนาดหน้าต่าง
        primaryStage.setFullScreen(false); // ปิดโหมดเต็มหน้าจอ

        // ลบปุ่มขยายหน้าต่างโดยใช้ StageStyle
        primaryStage.initStyle(StageStyle.UTILITY); // ใช้สไตล์ UTILITY เพื่อลบปุ่มขยายหน้าต่าง

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}