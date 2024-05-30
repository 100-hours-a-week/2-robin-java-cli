package com.game.service;

import com.game.bgm.MusicPlayer;

import java.io.FileInputStream;

public class MusicService {
    private MusicPlayer musicPlayer;

    public void playMusic(String filePath) {
        if(musicPlayer == null || !musicPlayer.isAlive()) {
            musicPlayer = new MusicPlayer(filePath);
            musicPlayer.start();
        }
    }
    public void stopMusic() {
        if(musicPlayer != null) {
            musicPlayer.stopMusic();
        }
    }
}

