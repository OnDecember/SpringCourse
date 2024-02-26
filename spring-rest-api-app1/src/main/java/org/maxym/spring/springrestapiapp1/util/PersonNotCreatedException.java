package org.maxym.spring.springrestapiapp1.util;

public class PersonNotCreatedException extends RuntimeException {

    public PersonNotCreatedException(String message) {
        super(message);
    }
}
