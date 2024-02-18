package org.maxym.spring.player;

import org.maxym.spring.music.Music;

import java.util.List;

public class MusicPlayer {

    private List<Music> music;
    private String name;
    private int volume;

    public void setMusic(List<Music> music) {
        this.music = music;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void playMusic() {
        System.out.println("Playing:");
        music.stream()
                .map(Music::getSong)
                .forEach(System.out::println);
    }
}
