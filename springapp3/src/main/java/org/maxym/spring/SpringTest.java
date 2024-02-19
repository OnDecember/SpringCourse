package org.maxym.spring;

import org.maxym.spring.config.SpringConfig;
import org.maxym.spring.enums.MusicGenre;
import org.maxym.spring.music.ClassicalMusic;
import org.maxym.spring.player.MusicPlayer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);

        System.out.println(player.playMusic());

        context.close();
    }
}
