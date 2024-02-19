package org.maxym.spring.music;

public class ClassicalMusic implements Music {

    private String song;

    private ClassicalMusic() {}

    public static ClassicalMusic getClassicalMusic() {
        ClassicalMusic music = new ClassicalMusic();
        music.song = "Classical song";
        return music;
    }
    @Override
    public String getSong() {
        return song;
    }

    public void doMyInit() {
        System.out.println("Doing my initialization");
    }

    public void doMyDestroy() {
        System.out.println("Doing my destruction");
    }
}
