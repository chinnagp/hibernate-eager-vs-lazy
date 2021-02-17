package com.chinnag.hibernate.demo;

import com.chinnag.hibernate.demo.entity.Course;
import com.chinnag.hibernate.demo.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            int instructorId = 1;
            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                                    + "JOIN FETCH i.courses "
                                    + "where i.id = :instructorId",
                            Instructor.class);

            query.setParameter("instructorId", instructorId);

            Instructor instructor = query.getSingleResult();

            System.out.println("Instructor: " + instructor);

            session.getTransaction().commit();
            session.close();

            System.out.println("CG: Courses: " + instructor.getCourses());
            System.out.println("CG: Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            sessionFactory.close();
        }
    }

}
