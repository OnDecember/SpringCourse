package org.maxym.spring.config;

import org.maxym.spring.Computer;
import org.maxym.spring.music.ClassicalMusic;
import org.maxym.spring.music.DubstepMusic;
import org.maxym.spring.music.Music;
import org.maxym.spring.music.RockMusic;
import org.maxym.spring.player.MusicPlayer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {

    @Bean
    public ClassicalMusic classicalMusic() {
        return new ClassicalMusic();
    }

    @Bean
    public RockMusic rockMusic() {
        return new RockMusic();
    }

    @Bean
    public DubstepMusic dubstepMusic() {
        return new DubstepMusic();
    }

    @Bean
    public List<Music> musicList() {
        return List.of(classicalMusic(), rockMusic(), dubstepMusic());
    }

    @Bean
    public MusicPlayer musicPlayer() {
        return new MusicPlayer(musicList());
    }

    @Bean
    public Computer computer() {
        return new Computer(musicPlayer());
    }

}
