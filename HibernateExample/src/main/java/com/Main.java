package com;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

	public static void testTransaction() {
		System.out.println("Hibernate ...");
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			for (int i = 1; i <= 3; i++) {
				User user = new User(null, "Java", new Date());
				if (i == 3) {
					// user.setId(Integer.parseInt("a"));
					user.setUsername("username");
				}
				session.save(user);
			}
			tx.commit();
			System.out.println("OK! ................");
		} catch (Exception e) {
			System.out.println("Rollback! .........");
			tx.rollback();
		}
	}

	public static void main(String[] args) {
		testTransaction();
	}

}
