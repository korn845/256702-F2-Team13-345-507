package com.lab;

import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

import java.util.ArrayList;
import java.util.List;

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
}