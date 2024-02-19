package org.maxym.spring.music;

import java.util.List;

public class DubstepMusic implements Music {
    @Override
    public List<String> getSong() {
        return List.of("Dubstep Song 1", "Dubstep Song 2", "Dubstep Song 3");
    }
}
