package ru.maksimov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.maksimov.models.Actor;
import ru.maksimov.models.Movie;
import ru.maksimov.models.Producer;

public class ManyToMany
{
    public static void main( String[] args )
    {

//        Configuration configuration = new Configuration()
//                .addAnnotatedClass(Movie.class)
//                .addAnnotatedClass(Producer.class)
//                .addAnnotatedClass(Actor.class);
//
//        try (SessionFactory sessionFactory = configuration.buildSessionFactory(); Session session = sessionFactory.getCurrentSession()) {
//            session.beginTransaction();
//
//            Actor actor = new Actor("ABOBA", 99);
//
//            Movie movie = session.get(Movie.class, 2);
//
//            movie.addActor(actor);
//
//            session.getTransaction().commit();
//        }

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Producer.class)
                .addAnnotatedClass(Actor.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory(); Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Movie movie = session.get(Movie.class, 2);

            System.out.println("Movie with id = 2:");
            System.out.println(movie);

            System.out.println("Actors of this movie:");
            for (Actor actor : movie.getActors()) {
                System.out.println(actor);

                System.out.println("All movies of this actor:");

                for (Movie anotherMovie : actor.getMovies()) {
                    System.out.println(anotherMovie);
                }

            }

            session.getTransaction().commit();
        }

    }
}
