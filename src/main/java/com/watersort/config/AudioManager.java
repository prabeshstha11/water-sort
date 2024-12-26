package com.watersort.config;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioManager {
    public static void playAudio(String audioName) {
        try {
            File audioFile = new File(AudioManager.class.getClassLoader().getResource(audioName).toURI());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (IOException | URISyntaxException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println("Error while playing audio: " + e.getMessage());
        }
    }

    public static void playSelectEffect() {
        playAudio("sfx/select.wav");
    }

    public static void playPourEffect() {
        playAudio("sfx/pour.wav");
    }

    public static void playErrorEffect() {
        playAudio("sfx/error.wav");
    }

    public static void playUndoEffect() {
        playAudio("sfx/undo.wav");
    }

    public static void playRestartEffect() {
        playAudio("sfx/restart.wav");
    }

    public static void playNewGameEffect() {
        playAudio("sfx/new.wav");
    }
}
