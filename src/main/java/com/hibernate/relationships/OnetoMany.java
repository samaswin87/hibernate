package com.hibernate.relationships;

import java.util.List;

import org.hibernate.Session;

import com.hibernate.configuration.Connect;
import com.hibernate.model.Employee;

public class OnetoMany {

	public static void main(String[] args) {
		Connect connect = new Connect();
		
		try(Session session = connect.getSession()) {
			
			List<Employee> data = session.createQuery("from Employee", Employee.class).list();
			data.forEach(employee -> {
				System.out.println(employee.toString());
				System.out.println(employee.getAddresses());
			});
		}
	}
}
