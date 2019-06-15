package com.hibernate.table;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.configuration.Connect;
import com.hibernate.model.Employee;

public class UpdateTable {

	public static void main(String[] args) {
		Connect connect = new Connect();
		
		Transaction tx = null;
		try(Session session = connect.getSession()) {
			tx = session.beginTransaction();
			Employee employee = session.createQuery("from Employee", Employee.class).list().get(0);
			employee.setEmail("aswin@test.com");
			session.update(employee);
			tx.commit();
		}catch(Exception e) {
			if(tx != null)
				tx.rollback();
			
			e.printStackTrace();
		}
	}
}
