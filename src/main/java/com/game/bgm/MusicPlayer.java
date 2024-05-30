package com.game.bgm;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer extends Thread {
    private final String filePath;
    private Clip clip;

    public MusicPlayer(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            InputStream audioSrc = getClass().getResourceAsStream(filePath);

            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            while(!clip.isRunning()) {
                Thread.sleep(100);
            }
            while(clip.isRunning()) {
                Thread.sleep(100);
            }
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void stopMusic() {
        if(clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
