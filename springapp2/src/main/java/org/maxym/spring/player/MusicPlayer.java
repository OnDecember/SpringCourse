package org.maxym.spring.player;

import org.maxym.spring.enums.MusicGenre;
import org.maxym.spring.music.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MusicPlayer {

    private final Music classicalMusic;
    private final Music rockMusic;
    @Value("${player.name}")
    private String name;
    @Value("${player.volume}")
    private int volume;

    @Autowired
    public MusicPlayer(@Qualifier("classicalMusic") Music classicalMusic,
                       @Qualifier("rockMusic") Music rockMusic) {
        this.classicalMusic = classicalMusic;
        this.rockMusic = rockMusic;
    }

    public String playMusic(MusicGenre musicGenre) {
        int songNumber = new Random().nextInt(3);
        return switch (musicGenre) {
            case ROCK -> rockMusic.getSong().get(songNumber);
            case CLASSICAL -> classicalMusic.getSong().get(songNumber);
        };
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }
}
