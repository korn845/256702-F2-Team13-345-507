package com.lab;

import java.util.Random;

public class PathGenerator {

    public static EnemyPath generateEnemyPath(double width, double height) {
        Random random = new Random();
        EnemyPath enemyPath = new EnemyPath(width, height);

        // Starting point: middle-left
        double startX = 0;
        double startY = height / 2;
        enemyPath.addPoint(startX, startY);

        System.out.println("Added starting point: X=" + startX + ", Y=" + startY);

        // Add random points along the path
        double x = startX;
        while (x < width - 100) { // Ensure the last point is near the right edge
            x += 100 + random.nextDouble() * 100; // Random step size

            // Calculate y with bounds check
            double yOffset = random.nextDouble() * 200 - 100; // Random offset (-100 to +100)
            double y = startY + yOffset;

            // Clamp y to ensure it stays within bounds
            y = Math.max(0, Math.min(height, y));

            // Add the point to the path
            enemyPath.addPoint(x, y);
            System.out.println("Added point: X=" + x + ", Y=" + y);
        }

        // Ending point: middle-right
        double endX = width;
        double endY = height / 2;
        enemyPath.addPoint(endX, endY);

        System.out.println("Added ending point: X=" + endX + ", Y=" + endY);

        return enemyPath;
    }
}