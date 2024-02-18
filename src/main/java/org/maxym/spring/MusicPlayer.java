package org.maxym.spring;

import org.maxym.spring.music.Music;

public class MusicPlayer {

    private Music music;

    public MusicPlayer(Music music) {
        this.music = music;
    }

    public void playMusic() {
        System.out.println("Playing: " + music.getSong());
    }
}
