package com.hibernate.configuration;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.hibernate.model.Address;
import com.hibernate.model.Employee;
import com.hibernate.model.Phone;

public class Connect {
	
	private SessionFactory sessionFactory;
	
	public Configuration config;
	public Properties property;
	
	public Connect() {
		config = new Configuration();
		property = new Properties();
	}
	
	public Session getSession() {
		return this.getSessionFactory().openSession();
	}
	
	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				this.setConfig();
                config.setProperties(property);
				
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                		.applySettings(config.getProperties()).build();
                
                this.setModel();
                sessionFactory = config.buildSessionFactory(serviceRegistry);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return sessionFactory;
		
	}
	
	public void setModel() {
		config.addAnnotatedClass(Employee.class);
		config.addAnnotatedClass(Phone.class);
		config.addAnnotatedClass(Address.class);
	}
	
	private void setConfig() {
		property.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		property.put(Environment.URL, "jdbc:mysql://localhost:3306/testing?useSSL=false");
		property.put(Environment.USER, "root");
        property.put(Environment.PASS, "");
        property.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        property.put(Environment.SHOW_SQL, "true");
        property.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        property.put(Environment.HBM2DDL_AUTO, "update");
        config.setProperties(property);
	}

}
