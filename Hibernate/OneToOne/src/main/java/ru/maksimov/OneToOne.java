package ru.maksimov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.maksimov.models.Director;
import ru.maksimov.models.School;

public class OneToOne
{
    public static void main( String[] args )
    {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(School.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

//        try(Session session = sessionFactory.getCurrentSession()) {
//            session.beginTransaction();
//
//            Director director = new Director("ABOBA", 100);
//
//            School school = new School(director, 108);
//
//            director.setSchool(school);

//        CASCADE SAVE

//            session.save(director);
//
//            session.getTransaction().commit();
//        }

        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            School school = session.get(School.class, 2);

            System.out.println("School with id = 2:");
            System.out.println(school);


            System.out.println("Director of this school");
            System.out.println(school.getDirector());

            session.getTransaction().commit();
        }


//        ERROR 2 SCHOOLS WILL HAVE 1 DIRECTORS

//        try(Session session = sessionFactory.getCurrentSession()) {
//            session.beginTransaction();
//
//            Director director = session.get(Director.class, 14);
//
//            School school = session.get(School.class, 1);
//
//            director.setSchool(school);
//
//            session.getTransaction().commit();
//        }
//        catch(Exception e) {
//            System.out.println("This current director already has a school");
//        }
    }
}
