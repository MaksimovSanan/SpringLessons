package ru.maksimov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.maksimov.models.Movie;
import ru.maksimov.models.Producer;

public class App
{
    public static void main( String[] args )
    {
//        Configuration configuration = new Configuration().
//                addAnnotatedClass(Producer.class).
//                addAnnotatedClass(Movie.class);
//
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//
//        try(Session session = sessionFactory.getCurrentSession()) {
//            session.beginTransaction();
//
//            Producer producer = new Producer("aboba", 99);
//
//            Movie movie = new Movie(producer, "abobaMovie", 2023);
//            producer.addMovie(movie);

//        CASCADE SAVE

//            session.save(producer);
//
//            session.getTransaction().commit();
//        }

        {
            Configuration configuration = new Configuration().
                    addAnnotatedClass(Producer.class).
                    addAnnotatedClass(Movie.class);

            SessionFactory sessionFactory = configuration.buildSessionFactory();

            try(Session session = sessionFactory.getCurrentSession()) {
                session.beginTransaction();

                Movie movie = session.get(Movie.class, 3);
                System.out.println("Movie with id = 3:");
                System.out.println(movie);

                System.out.println("Producer of this movie:");
                Producer producer = movie.getProducer();

                System.out.println(producer);

                System.out.println("All movies this producer:");
                for(Movie anotherMovies : producer.getMovies()) {
                    System.out.println(anotherMovies);
                }
                session.getTransaction().commit();
            }
        }
    }
}
