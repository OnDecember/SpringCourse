package org.maxym.spring;

import org.maxym.spring.enums.MusicGenre;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        Computer computer = context.getBean("computer", Computer.class);
        computer.setGenre(MusicGenre.ROCK);
        System.out.println(computer);
        context.close();
    }
}
