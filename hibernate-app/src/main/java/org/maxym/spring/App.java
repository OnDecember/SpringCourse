package org.maxym.spring;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.maxym.spring.model.Person;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person = new Person("Some name", 40);

            session.save(person);

            System.out.println(person.getId());

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
