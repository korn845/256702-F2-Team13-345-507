package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        StartButton startButton = new StartButton(primaryStage);
        root.getChildren().add(startButton.getButton());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Primary View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}