package org.maxym.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.maxym.spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> getPeople() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    public Optional<Person> getPerson(String  email) {
        return null;
    }

    public Person getPerson(int id) {
        return null;
    }

    public void save(Person person) {
    }

    public void update(Person person, int id) {
    }

    public void delete(int id) {
    }

    public void withoutBatch() {

    }

    private List<Person> createPeople() {
        return null;
    }

    public void withBatch() {

    }
}
