package org.maxym.spring.player;

import org.maxym.spring.enums.MusicGenre;
import org.maxym.spring.music.ClassicalMusic;
import org.maxym.spring.music.Music;
import org.maxym.spring.music.RockMusic;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class MusicPlayer {

    private final List<Music> musicList;
    @Value("${player.name}")
    private String name;
    @Value("${player.volume}")
    private int volume;

    public MusicPlayer(List<Music> musicList) {
        this.musicList = musicList;
    }

    public String playMusic() {
        int songNumber = new Random().nextInt(3);
        int genreNumber = new Random().nextInt(musicList.size());
        return musicList.get(genreNumber).getSong().get(songNumber);
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }
}
