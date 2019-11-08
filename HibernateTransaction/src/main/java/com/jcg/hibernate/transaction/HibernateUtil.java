package com.jcg.hibernate.transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.transaction.annotation.Transactional;

public class HibernateUtil {

	static Session session;
	static SessionFactory sessionFactoryObj;

	// This Method Is Used To Create The Hibernate's SessionFactory Object
	private static SessionFactory buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");
		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}

	// Method 1: This Method Used To Create A New User Record In The Database Table
	@Transactional(readOnly = true) // <- spring
	public static void createRecord() {
		Users userObj;
		try {
			session = buildSessionFactory().openSession();
			session.beginTransaction();
			for (int j = 9; j <= 12; j++) {
				// Creating User Data & Saving It To The Database
				userObj = new Users();
				userObj.setId(j);
				userObj.setName("abcd" + j);
				userObj.setCreatedBy("Administrator");
				userObj.setCreatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
				session.save(userObj);
			}
			// Committing The Transactions To The Database
			session.getTransaction().commit();
		} catch (Exception sqlException) {
			if (null != session.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		System.out.println("\n.......Records Saved Successfully In The Database.......\n");
	}

	// Method 2: This Method Used To Update A User Record In The Database Table
	public static void updateRecord() {
		Users userObj;
		int user_id = 103;
		try {
			session = buildSessionFactory().openSession();
			session.beginTransaction();
			userObj = (Users) session.get(Users.class, new Integer(user_id));
			// This line Will Result In A 'Database Exception' & The Data Will Rollback
			// (i.e. No Updations Will Be Made In The Database Table)
			userObj.setName("A Very Very Long String Resulting In A Database Error");
			// Committing The Transactions To The Database
			session.getTransaction().commit();
		} catch (Exception sqlException) {
			if (null != session.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}