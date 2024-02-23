package org.maxym.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.maxym.spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public Optional<Person> getPerson(String  email) {
        Session session = sessionFactory.getCurrentSession();
        List<Person> people = session.createQuery("FROM Person WHERE email = :email", Person.class)
                .setParameter("email", email).getResultList();
        if (people.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(people.get(0));
    }

    @Transactional(readOnly = true)
    public Person getPerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Transactional
    public void update(Person person, int id) {
        Session session = sessionFactory.getCurrentSession();
        person.setId(id);
        session.merge(person);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.remove(person);
    }

    public void withoutBatch() {

    }

    private List<Person> createPeople() {
        return null;
    }

    public void withBatch() {

    }
}
