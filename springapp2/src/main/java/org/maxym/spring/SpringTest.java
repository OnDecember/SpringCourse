package org.maxym.spring;

import org.maxym.spring.music.ClassicalMusic;
import org.maxym.spring.music.Music;
import org.maxym.spring.player.MusicPlayer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    "applicationContext.xml");

        Music rockMusic = context.getBean("rockMusic", Music.class);

        MusicPlayer rockPlayer = new MusicPlayer(rockMusic);

        rockPlayer.playMusic();

        Music classicalMusic = context.getBean("classicalMusic", Music.class);

        MusicPlayer classicalPlayer = new MusicPlayer(classicalMusic);

        classicalPlayer.playMusic();

        context.close();
    }
}
