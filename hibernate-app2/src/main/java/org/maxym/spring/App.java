package org.maxym.spring;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.maxym.spring.model.Passport;
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
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 2);
            session.remove(person);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
