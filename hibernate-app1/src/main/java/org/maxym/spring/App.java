package org.maxym.spring;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.maxym.spring.model.Item;
import org.maxym.spring.model.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Item item = session.get(Item.class, 1);

            System.out.println("get item");

            System.out.println(item.getOwner());

            Person person = session.get(Person.class, 1);

            session.getTransaction().commit();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            person = session.merge(person);

            System.out.println(person.getItems());

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }

    }
}
