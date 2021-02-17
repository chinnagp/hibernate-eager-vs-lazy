package com.chinnag.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.chinnag.hibernate.demo.entity.Course;
import com.chinnag.hibernate.demo.entity.Instructor;

public class CreateCoursesDemo {

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
			Instructor instructor = session.get(Instructor.class, instructorId);
			
			Course courseJava = new Course("Java Programming");
			Course coursePython = new Course("Python Programming");
			
			instructor.add(courseJava);
			instructor.add(coursePython);
			
			session.save(courseJava);
			session.save(coursePython);
			
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}
