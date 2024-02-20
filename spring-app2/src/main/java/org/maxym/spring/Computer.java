package org.maxym.spring;

import org.maxym.spring.enums.MusicGenre;
import org.maxym.spring.player.MusicPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Computer {

    private final int id;
    private final MusicPlayer musicPlayer;
    private MusicGenre musicGenre;

    @Autowired
    public Computer(MusicPlayer musicPlayer) {
        this.id = 1;
        this.musicPlayer = musicPlayer;
    }

    public void setGenre(MusicGenre musicGenre) {
        this.musicGenre = musicGenre;
    }

    @Override
    public String toString() {
        return "Computer " + id + " " + musicPlayer.playMusic(musicGenre);
    }
}
