package com.lab.UI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.lab.GamePlay;

public class TowerSelectionBar {
    private HBox towerBar;
    private static final double ARMY_ICON_SIZE = 45; // ลดขนาดจาก 60 เป็น 50
    private GamePlay gamePlay; // เพิ่มตัวแปรเก็บ GamePlay instance

    public TowerSelectionBar(Pane gamePane, GamePlay gamePlay) { // เพิ่มพารามิเตอร์ GamePlay
        this.gamePlay = gamePlay; // เก็บ instance ไว้ใช้
        towerBar = new HBox(20);
        towerBar.setAlignment(Pos.CENTER);
        towerBar.setStyle("-fx-background-color: rgb(0, 0, 0); -fx-padding: 10px;");
        towerBar.setPrefHeight(70); // ลดความสูงจาก 100 เป็น 80
        towerBar.setLayoutY(530); // ปรับตำแหน่ง Y เพื่อให้อยู่ด้านล่างเหมือนเดิม
        towerBar.prefWidthProperty().bind(gamePane.widthProperty());

        initializeTowerButtons();
        gamePane.getChildren().add(towerBar);
    }

    private void initializeTowerButtons() {
        Button armyButton = createArmyButton("/images/army.png", "army");
        towerBar.getChildren().add(armyButton);
    }

    private Button createArmyButton(String imagePath, String armyType) {
        Button button = new Button();
        try {
            // First try resources folder
            Image armyImage = new Image(getClass().getResource(imagePath).toExternalForm());
            if (armyImage.isError()) {
                // If not found, try direct file path
                armyImage = new Image("file:" + imagePath);
            }
            ImageView imageView = new ImageView(armyImage);
            imageView.setFitHeight(ARMY_ICON_SIZE);
            imageView.setFitWidth(ARMY_ICON_SIZE);
            button.setGraphic(imageView);
            button.setStyle("-fx-background-color: transparent; -fx-padding: 5px;");
        } catch (Exception e) {
            System.err.println("Error loading army image: " + imagePath);
            button.setText(armyType.toUpperCase());
            button.setStyle("-fx-background-color: #4a4a4a; -fx-text-fill: white; -fx-padding: 5px;");
        }
        button.setOnAction(e -> selectTower(armyType));
        return button;
    }

    private void selectTower(String towerType) {
        gamePlay.setSelectedTowerType(towerType);
        
        // สร้าง preview tower ที่จะติดตามเมาส์
        ImageView previewTower = new ImageView(new Image(getClass().getResource("/images/army.png").toExternalForm()));
        previewTower.setFitHeight(40);
        previewTower.setFitWidth(40);
        previewTower.setOpacity(0.7);
        
        Pane gamePane = gamePlay.getGamePane();
        gamePane.getChildren().add(previewTower);
        
        gamePane.setOnMouseMoved(e -> {
            previewTower.setX(e.getX() - 20);
            previewTower.setY(e.getY() - 20);
        });
        
        gamePane.setOnMouseClicked(e -> {
            gamePane.getChildren().remove(previewTower);
            gamePane.setOnMouseMoved(null);
            // Check if the GamePlay instance has a method to handle tower placement
            if (gamePlay != null) {
                // Assuming the method is called createTower or addTower
                gamePlay.placeTower(e.getX(), e.getY());
            }
        });
    }
}