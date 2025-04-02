package com.lab;

import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.LineTo;
import javafx.animation.PathTransition;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class EnemyPath {
    private List<Point2D> pathPoints; // เก็บจุดทั้งหมดในเส้นทาง
    private double width;  // ความกว้างของหน้าจอ
    private double height; // ความสูงของหน้าจอ

    public EnemyPath(double width, double height) {
        this.width = width;
        this.height = height;
        pathPoints = new ArrayList<>();
    }

    // เพิ่มจุดในเส้นทาง (พร้อมตรวจสอบขอบเขต)
    public void addPoint(double x, double y) {
        if (x >= 0 && x <= width && y >= 0 && y <= height) {
            pathPoints.add(new Point2D(x, y));
        } else {
            System.err.println("Point (" + x + ", " + y + ") is out of bounds and will not be added.");
        }
    }

    // ดึงรายการจุดทั้งหมดในเส้นทาง
    public List<Point2D> getPathPoints() {
        return pathPoints;
    }

    // สร้าง Polyline สำหรับแสดงผลเส้นทาง
    public Polyline getPathPolyline() {
        Polyline polyline = new Polyline();
        for (Point2D point : pathPoints) {
            polyline.getPoints().addAll(point.getX(), point.getY());
        }
        polyline.setStroke(Color.TRANSPARENT);
        polyline.setFill(null);
        return polyline;
    }

    // สร้างเส้นทางเริ่มต้นจากซ้ายไปขวา
    public static EnemyPath generateEnemyPath(double width, double height) {
        EnemyPath enemyPath = new EnemyPath(width, height);

        // กำหนดจุดในเส้นทาง
        enemyPath.addPoint(0, 300);      // Start
        enemyPath.addPoint(200, 300);    // Right
        enemyPath.addPoint(200, 100);    // Up
        enemyPath.addPoint(400, 100);    // Right
        enemyPath.addPoint(400, 500);    // Down
        enemyPath.addPoint(600, 500);    // Right
        enemyPath.addPoint(600, 300);    // Up
        enemyPath.addPoint(800, 300);    // End

        // แสดงเส้นทางใน FXGL GameScene
        FXGL.getGameScene().addUINode(enemyPath.getPathPolyline());

        return enemyPath;
    }
    
    // คลาสสำหรับจัดการพิกัด
    public static class Coordinate {
        private double x, y;
        
        public Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        public double getExactX() {
            return x;
        }
        
        public double getExactY() {
            return y;
        }
    }
    
    // คลาสสำหรับตรวจสอบการโจมตีของหอคอย
    private static class TowerAttackerService extends Service<Void> {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    // ตรวจสอบการโจมตีของหอคอย
                    return null;
                }
            };
        }
    }
    
    // สร้างและเคลื่อนที่ศัตรูตามเส้นทาง
    public ImageView createMovingEnemy(Pane monsterLayer, List<ImageView> enemies, List<Coordinate> pathCoords) {
        Rectangle rectBasicTimeline = new Rectangle(0, 0, 40, 40);
        monsterLayer.getChildren().add(rectBasicTimeline);
        
        // สร้าง ImageView สำหรับศัตรูที่เคลื่อนที่
        ImageView enemyView = new ImageView();
        enemyView.setFitWidth(40);
        enemyView.setFitHeight(40);
        enemyView.setX(0);
        enemyView.setY(0);
        monsterLayer.getChildren().add(enemyView);
        
        // เพิ่มศัตรูลงในรายการสำหรับการติดตาม
        if (enemies != null) {
            enemies.add(enemyView);
        }

        // ตรวจสอบหอคอยในระยะ
        TowerAttackerService testService = new TowerAttackerService();
        testService.start();

        Path path = new Path();
        boolean isFirst = true;

        // สร้างเส้นทางจากพิกัดที่กำหนด
        for (Coordinate coords : pathCoords) {
            if (isFirst) {
                path.getElements().add(new MoveTo(coords.getExactX(), coords.getExactY()));
                isFirst = false;
            } else {
                path.getElements().add(new LineTo(coords.getExactX(), coords.getExactY()));
            }
        }

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(10000));
        pathTransition.setPath(path);
        pathTransition.setNode(rectBasicTimeline);
        
        // ผูกตำแหน่งของ ImageView กับ Rectangle เพื่อการติดตาม
        rectBasicTimeline.xProperty().addListener((obs, oldVal, newVal) -> {
            enemyView.setX(newVal.doubleValue());
        });
        
        rectBasicTimeline.yProperty().addListener((obs, oldVal, newVal) -> {
            enemyView.setY(newVal.doubleValue());
        });
        
        pathTransition.play();
        
        return enemyView;
    }
    
    // แปลง Point2D เป็น Coordinate
    public List<Coordinate> convertPathPointsToCoordinates() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (Point2D point : pathPoints) {
            coordinates.add(new Coordinate(point.getX(), point.getY()));
        }
        return coordinates;
    }
}