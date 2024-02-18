package org.maxym.spring;

import org.maxym.spring.music.Music;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    "applicationContext.xml");
        Music music = context.getBean("musicBean", Music.class);

        MusicPlayer player = new MusicPlayer(music);

        player.playMusic();

        context.close();
    }
}
