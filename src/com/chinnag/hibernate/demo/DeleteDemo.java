package com.chinnag.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.chinnag.hibernate.demo.entity.Instructor;

public class DeleteDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		// create session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			// start a transaction
		    session.beginTransaction();
		    
		    int instructorId = 1;
		    Instructor instructor = session.get(Instructor.class, instructorId);
		    
		    System.out.println("Found instructor: " + instructor);
		    
		    if(instructor != null) {
		    	System.out.println("Deleting: " + instructor);
		    	
		    	// this will ALSO delete entry in the child table i.e instructor_detail
		    	// due to CaseCaseType.ALL
		    	session.delete(instructor);
		    }
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}

}
