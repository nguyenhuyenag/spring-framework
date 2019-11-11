package com;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			configuration.addAnnotatedClass(com.Users.class);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void close() {
		getSessionFactory().close();
	}

}