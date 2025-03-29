package com.lab;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class SettingsWindow {
    
    public static void display() {
        Stage settingsStage = new Stage();
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.setTitle("Sound Settings");
        settingsStage.setMinWidth(300);

        Label label = new Label("Adjust Volume:");
        Slider volumeSlider = new Slider(0, 100, 50);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> settingsStage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, volumeSlider, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 800, 600);
        settingsStage.setScene(scene);
        settingsStage.showAndWait();

        
        }
    }

