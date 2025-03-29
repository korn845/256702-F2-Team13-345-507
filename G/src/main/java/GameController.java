package game;

import javafx.scene.layout.Pane;

public class GameController {

    private Pane gamePane;

    public GameController() {
        gamePane = new Pane();
        // Initialize game components here
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public void startGame() {
        // Start game logic here
    }
}