package com.lab;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    private MediaPlayer mediaPlayer;

    public Sound() {
        // Constructor can be used for initialization if needed
    }

    public void playBackgroundMusic() {
        try {
            String musicFile = getClass().getResource("/sounds/Oneway.wav").toExternalForm(); // Use resource path
            Media sound = new Media(musicFile);
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Error playing background music: " + e.getMessage());
        }
    }

    public void stopBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}