package org.maxym.spring.music;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

public class ClassicalMusic implements Music {

    @Override
    public List<String> getSong() {
        return List.of("Classical Song 1", "Classical Song 2", "Classical Song 3");
    }

    @PostConstruct
    public void doMyInit() {
        System.out.println("Doing my initialization");
    }

    @PreDestroy
    public void doMyDestroy() {
        System.out.println("Doing my destruction");
    }
}
