package org.maxym.spring;

import org.maxym.spring.music.Music;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    "applicationContext.xml");

        MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);

        player.playMusic();

        context.close();
    }
}
