package org.maxym.spring;

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

            Person person = new Person("New Person", 20);

            Item item1 = new Item("Test Item 1");
            Item item2 = new Item("Test Item 2");
            Item item3 = new Item("Test Item 3");

            person.addItem(item1);
            person.addItem(item2);
            person.addItem(item3);

            session.save(person);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }

    }
}
