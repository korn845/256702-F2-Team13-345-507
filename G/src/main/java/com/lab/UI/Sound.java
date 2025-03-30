package com.lab.UI;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    private static MediaPlayer mediaPlayer;
    private static double soundLevel = 25; // ระดับเสียงเริ่มต้น (50%)

    public Sound() {
        // Constructor ไม่จำเป็นต้องมีอะไรเพิ่มเติม
    }

    public static void playBackgroundMusic() {
        try {
            // ตรวจสอบว่ามี MediaPlayer ที่เล่นอยู่หรือไม่ และหยุดการเล่นก่อน
            stopBackgroundMusic();

            String musicFile = "/sounds/song.mp3"; // ไฟล์เพลง
            Media sound = new Media(Sound.class.getResource(musicFile).toExternalForm());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // เล่นซ้ำ
            mediaPlayer.setVolume(soundLevel / 50.0); // ตั้งค่าระดับเสียงเริ่มต้น
            mediaPlayer.play();
            System.out.println("Playing background music...");
        } catch (Exception e) {
            System.err.println("Error playing background music: " + e.getMessage());
        }
    }

    public static void stopBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose(); // ปลดปล่อยทรัพยากร
            mediaPlayer = null;
        }
    }

    public static void setSoundLevel(double level) {
        if (level >= 0 && level <= 100) {
            soundLevel = level;
            updateVolume(); // อัปเดตระดับเสียงใน MediaPlayer ทันที
        } else {
            System.err.println("Error: Sound level must be between 0 and 100.");
        }
    }

    private static void updateVolume() {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(soundLevel / 50.0); // แปลงระดับเสียงเป็นค่า 0.0 - 1.0
            System.out.println("Updated volume to: " + (soundLevel / 50.0));
        }
    }
}