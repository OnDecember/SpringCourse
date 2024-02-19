package org.maxym.spring.music;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassicalMusic implements Music {

    @Override
    public List<String> getSong() {
        return List.of("Classical Song 1", "Classical Song 2", "Classical Song 3");
    }
}
