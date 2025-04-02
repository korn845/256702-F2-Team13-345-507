package com.lab;


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
import javafx.scene.input.KeyCode;
import com.lab.UI.PauseMenu;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class GamePlay {
    private static final int TOWER_COST = 100;
    private Pane gamePane;
    private Hp hp;
    private Money money;
    private String selectedTowerType = null;
    private AnimationTimer gameLoop;
    private List<PathTransition> activeTransitions = new ArrayList<>();
    private List<PauseTransition> activePauseTransitions = new ArrayList<>();
    private MediaPlayer mediaPlayer;
    private Wave wave; // Add Wave reference

    public GamePlay() {
        gamePane = new Pane();
        money = new Money(gamePane, 100);
        wave = new Wave(); // Initialize Wave object
        initializeGame();
    }

    private List<Army> armies = new ArrayList<>();

    private void initializeGame() {
        try {
            Image backgroundImage = new Image(getClass().getResource("/images/BG_GAMEPLAY.jpg").toExternalForm());
            BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(800, 600, false, false, false, false)
            );
            gamePane.setBackground(new Background(background));

            Polyline pathPolyline = new Polyline(
                0, 300, 200, 300, 200, 100, 400, 100, 400, 500, 600, 500, 600, 300, 800, 300
            );
            pathPolyline.setStroke(Color.TRANSPARENT);
            pathPolyline.setFill(null);
            gamePane.getChildren().add(pathPolyline);

            // Remove this block since we have placeTower method
            gamePane.setOnMouseClicked(event -> {
                if (selectedTowerType != null && money.canAfford(TOWER_COST)) {
                    // Create army with proper positioning
                    double x = event.getX() - 25; // Center the army on click position
                    double y = event.getY() - 25;
                    Army army = new Army(x, y, wave.getEnemyViews(), gamePane); // Changed from getEnemies() to getEnemyViews()
                    army.placeArmy(gamePane);
                    money.spendMoney(TOWER_COST);
                }
            });

            hp = new Hp(gamePane, 10, 5);

            // Setup background music
            try {
                // ตรวจสอบว่าไฟล์มีอยู่จริง
                java.io.File file = new java.io.File("src/main/resources/sounds/gamesong.mp3");
                if (file.exists()) {
                    System.out.println("File exists at: " + file.getAbsolutePath());
                    
                    // ใช้ URI แทน URL string
                    String musicFile = file.toURI().toString();
                    Media sound = new Media(musicFile);
                    mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                    mediaPlayer.setVolume(0.3); // เปลี่ยนจาก 1.0 เป็น 0.3 เพื่อลดระดับเสียง
                    
                    // เพิ่ม event handlers เพื่อตรวจสอบสถานะ
                    mediaPlayer.setOnReady(() -> {
                        mediaPlayer.play();
                        System.out.println("Media is ready and playing");
                    });
                    
                    mediaPlayer.setOnError(() -> {
                        System.err.println("Media error: " + mediaPlayer.getError());
                    });
                    
                    System.out.println("Media player created with: " + musicFile);
                } else {
                    System.err.println("Music file not found at: " + file.getAbsolutePath());
                }
            } catch (Exception e) {
                System.err.println("Error loading music: " + e.getMessage());
                e.printStackTrace();
            }
            // Add shooting timer
            AnimationTimer shootingTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    // Check all armies and make them shoot
                    for (Army army : armies) {
                        if (army != null) {
                            army.shootEnemies(wave.getEnemyViews());
                        }
                    }
                }
            };
            shootingTimer.start();
        } catch (Exception e) {
            System.err.println("Error loading background: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void spawnEnemyWave(Polyline path, int enemyCount) {
        for (int i = 0; i < enemyCount; i++) {
            try {
                // Load enemy image
                Image enemyImage = new Image(getClass().getResource("/images/enemy.png").toExternalForm());
                javafx.scene.image.ImageView enemy = new javafx.scene.image.ImageView(enemyImage);
                
                // Set size and initial position
                enemy.setFitWidth(30);
                enemy.setFitHeight(30);
                enemy.setX(-15); // Adjust position to start at the path
                enemy.setY(285); // Center vertically
                
                gamePane.getChildren().add(enemy);
                wave.getEnemyViews().add(enemy); // Add enemy to Wave's list of ImageViews

                PathTransition transition = new PathTransition();
                transition.setNode(enemy);
                transition.setPath(path);
                transition.setDuration(Duration.seconds(30)); // Adjust speed as needed
                transition.setCycleCount(1);
                transition.setOnFinished(event -> {
                    gamePane.getChildren().remove(enemy);
                    wave.getEnemies().remove(enemy); // Remove enemy from Wave's list
                    hp.updateBaseHealth(hp.getBaseHealth() - 1);
                });

                enemy.setOnMouseClicked(event -> {
                    gamePane.getChildren().remove(enemy);
                    wave.getEnemies().remove(enemy); // Remove enemy from Wave's list
                    money.addKillReward();
                    transition.stop();
                    activeTransitions.remove(transition);
                });

                PauseTransition delay = new PauseTransition(Duration.seconds(i * 2));
                activeTransitions.add(transition);
                activePauseTransitions.add(delay);
                delay.setOnFinished(event -> {
                    transition.play();
                    activePauseTransitions.remove(delay);
                });
                delay.play();
            } catch (Exception e) {
                System.err.println("Error loading enemy image: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Remove the duplicate pauseGame() and resumeGame() methods at the bottom of the file
    // Keep only these versions
    // ลบ comment และโค้ดที่ซ้ำซ้อนออก
    // เก็บเฉพาะ method นี้ไว้ (ประมาณบรรทัดที่ 80-100)
    // Remove duplicate method and keep only one pauseGame() implementation
    private void pauseGame() {
        if (gameLoop != null) gameLoop.stop();
        activeTransitions.forEach(transition -> {
            if (transition != null) transition.pause();
        });
        activePauseTransitions.forEach(delay -> {
            if (delay != null) delay.pause();
        });
    }

    public void resumeGame() {
        if (gameLoop != null) gameLoop.start();
        activeTransitions.forEach(transition -> {
            if (transition != null) transition.play();
        });
        activePauseTransitions.forEach(delay -> {
            if (delay != null) delay.play();
        });
    }

    public void startGame(Stage primaryStage) {
        Scene scene = new Scene(gamePane, 800, 600);
        
        // ลบโค้ดที่สร้างปุ่ม Start Wave
        // javafx.scene.control.Button startWaveButton = new javafx.scene.control.Button("Start Wave");
        // startWaveButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px;");
        // startWaveButton.setLayoutX(650);
        // startWaveButton.setLayoutY(50);
        // gamePane.getChildren().add(startWaveButton);
        
        // startWaveButton.setOnAction(e -> startWave());
        
        // Set stage style before setting the scene
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tower Defense");
        
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                pauseGame();
                PauseMenu pauseMenu = new PauseMenu(primaryStage, this);
                pauseMenu.show();
            }
        });
    
        primaryStage.show();
    
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (hp.getBaseHealth() <= 0) {
                    this.stop();
                    showGameOver(primaryStage);
                }
            }
        };
        gameLoop.start();
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void resumeMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public void setSelectedTowerType(String towerType) {
        this.selectedTowerType = towerType;
        System.out.println("Selected tower type: " + towerType);
    }
    
    public Pane getGamePane() {
        return gamePane;
    }
    
    public void placeTower(double x, double y) {
        if (selectedTowerType != null && money != null && money.canAfford(TOWER_COST)) {
            Army army = new Army(x, y, wave.getEnemyViews(), gamePane);
            army.placeArmy(gamePane);
            armies.add(army); // Add to list of active armies
            money.spendMoney(TOWER_COST);
        } else if (money != null && !money.canAfford(TOWER_COST)) {
            System.out.println("Not enough money to place tower!");
        }
    }
    
    public void startWave() {
        // สร้าง Polyline สำหรับเส้นทางของศัตรู
        Polyline enemyPath = new Polyline(
            0, 300, 
            200, 300, 
            200, 100, 
            400, 100, 
            400, 500, 
            600, 500, 
            600, 300, 
            800, 300
        );
        
        // เรียกเมธอด spawnEnemyWave เพื่อสร้างศัตรู
        spawnEnemyWave(enemyPath, 10); // สร้างศัตรู 10 ตัว
    }
    
    private void showGameOver(Stage primaryStage) {
        // หยุดเพลงและการเคลื่อนไหวทั้งหมด
        stopMusic();
        
        // สร้าง Pane สำหรับหน้า Game Over
        Pane gameOverPane = new Pane();
        
        // สร้างข้อความ Game Over
        Text gameOverText = new Text("Game Over");
        gameOverText.setStyle("-fx-font-size: 50px; -fx-fill: red;");
        gameOverText.setLayoutX(250);
        gameOverText.setLayoutY(250);
        
        // เพิ่มองค์ประกอบลงใน Pane
        gameOverPane.getChildren().addAll(gameOverText);
        
        
        // สร้าง Scene และแสดงผล
        Scene gameOverScene = new Scene(gameOverPane, 800, 600);
        primaryStage.setScene(gameOverScene);
    }
}