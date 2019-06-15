package com.hibernate.table;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.configuration.Connect;
import com.hibernate.model.Employee;

public class WriteTable {

	public static void main(String[] args) {
		Connect connect = new Connect();
		
		Employee employee = new Employee();
		employee.setEmail("test@test.com");
		employee.setFirstName("aswin");
		employee.setLastName("r");
		Transaction tx = null;
		try(Session session = connect.getSession()) {
			tx = session.beginTransaction();
			session.save(employee);
			tx.commit();
		}catch(Exception e) {
			if(tx != null)
				tx.rollback();
			
			e.printStackTrace();
		}
	}
}
