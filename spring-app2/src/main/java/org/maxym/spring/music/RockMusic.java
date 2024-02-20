package org.maxym.spring.music;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RockMusic implements Music {
    @Override
    public List<String> getSong() {
        return List.of("Rock Song 1", "Rock Song 2", "Rock Song 3");
    }
}
