package com;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * SessionFactory là đối tượng nặng (heavy weight object) và được sử dụng thường
 * xuyên nên cần tạo một class singleton HibernateUtils
 */
public class HibernateUtils {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private HibernateUtils() {

	}

	private static SessionFactory buildSessionFactory() {
		return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	@SuppressWarnings("unused")
	private static SessionFactory buildSessionFactory2() {
		Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(com.User.class);
		ServiceRegistry service = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config.buildSessionFactory(service);
		return sessionFactory;
	}

	@SuppressWarnings("unused")
	private static SessionFactory buildSessionFactory3() {
		try {
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