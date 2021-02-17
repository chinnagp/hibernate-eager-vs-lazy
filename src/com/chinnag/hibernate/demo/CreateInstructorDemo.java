package com.chinnag.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.chinnag.hibernate.demo.entity.Course;
import com.chinnag.hibernate.demo.entity.Instructor;

public class CreateInstructorDemo {

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
			
			Instructor instructor = new Instructor("Sravan", "Sareka", "sravan.sareka@chinnag.com");
			
			InstructorDetail instructorDetail = new InstructorDetail("http://youtube.com/sravan", "love to dance");
			
			instructor.setInstructorDetail(instructorDetail);
						
			session.beginTransaction();
			
		    // save the instructor &
			// this will also save the instructor_detail object 
			// because of the CasecadeType.ALL
			System.out.println("Saving instructor: " +  instructor);
			System.out.println("Saving instructorDetail: " + instructorDetail);
			session.save(instructor);	
			
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
