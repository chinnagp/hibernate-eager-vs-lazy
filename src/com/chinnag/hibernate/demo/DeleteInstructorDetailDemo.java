package com.chinnag.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.chinnag.hibernate.demo.entity.Instructor;

public class DeleteInstructorDetailDemo {

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
		    
		    int instructorId = 4; 
		    
		    // get the instructor detail object
		    InstructorDetail instructorDetail = session.get(InstructorDetail.class, instructorId);
		    
		    // print instructor details
		    System.out.println("instructorDetail: " +  instructorDetail);
		    
		    // print the associated instructor
		    System.out.println("Associated instructor: " + instructorDetail.getInstructor());
		    
		    // remove the connection with instructor
		    instructorDetail.getInstructor().setInstructorDetail(null);
		    //instructorDetail.setInstructor(null);
		    
		    
		    System.out.println("Deleting instructorDetail: " + instructorDetail);
		    session.delete(instructorDetail);
		    
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}

}
