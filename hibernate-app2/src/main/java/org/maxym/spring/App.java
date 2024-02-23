package org.maxym.spring;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.maxym.spring.model.Actor;
import org.maxym.spring.model.Movie;
import org.maxym.spring.model.Passport;
import org.maxym.spring.model.Person;

import java.util.ArrayList;
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
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try(sessionFactory) {
            session.beginTransaction();


            Actor actor = session.get(Actor.class, 1);

            System.out.println(actor.getMovies());

            Movie movie = actor.getMovies().get(0);

            actor.getMovies().remove(0);

            movie.getActors().remove(actor);

            session.getTransaction().commit();
        }
    }
}
