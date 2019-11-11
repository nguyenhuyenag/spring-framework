package com;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

	private HibernateUtils() {
		super();
	}

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// return new
			// Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

			// Configuration config = new
			// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(com.User.class);
			// ServiceRegistry service = new
			// StandardServiceRegistryBuilder().applySettings(config.getProperties())
			// .build();
			// SessionFactory sessionFactory = config.buildSessionFactory(service);
			// return sessionFactory;

			ServiceRegistry service = new StandardServiceRegistryBuilder().configure().build();
			Metadata metadata = new MetadataSources(service).getMetadataBuilder().build();
			return metadata.getSessionFactoryBuilder().build();
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