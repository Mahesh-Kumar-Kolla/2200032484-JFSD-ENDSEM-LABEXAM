package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Values to update
        int departmentId = 6; // ID of the department to update
        String updatedName = "Artificial Intelligence Department";
        String updatedLocation = "Seattle";

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        
        session.beginTransaction();

        try {
            // Example update operation
            Department dept = session.get(Department.class, departmentId); // Fetch the record
            if (dept != null) {
                // Update the fields
                dept.setName(updatedName);
                dept.setLocation(updatedLocation);

                session.update(dept); // Update the record in the database
                session.getTransaction().commit(); // Commit the transaction

                System.out.println("Department updated successfully!");
            } else {
                System.out.println("Department with ID " + departmentId + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback(); // Rollback the transaction in case of error
        } finally {
            session.close();
            factory.close();
        }
    }
}
