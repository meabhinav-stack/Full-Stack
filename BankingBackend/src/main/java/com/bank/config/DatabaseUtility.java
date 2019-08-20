package com.bank.config;

import java.lang.annotation.Annotation;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.bank.util.Customer;

@ComponentScan("com.*")
public class DatabaseUtility {
	private static SessionFactory sessionFactory;

	public static Properties getConfigure() {
		Properties settings = new Properties();
		settings.put(Environment.DRIVER, "org.h2.Driver");
		settings.put(Environment.URL, "jdbc:h2:~/Bank011991");
		settings.put(Environment.USER, "system");
		settings.put(Environment.PASS, "@123");
		settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
		settings.put(Environment.SHOW_SQL, true);
		settings.put(Environment.HBM2DDL_AUTO, "update");
		return settings;
	}
	
	@Autowired
	@Bean("sessionFectory")
	public SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				configuration.setProperties(getConfigure());
				configuration.addAnnotatedClass(Customer.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			}catch(Exception exp) {
				exp.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
