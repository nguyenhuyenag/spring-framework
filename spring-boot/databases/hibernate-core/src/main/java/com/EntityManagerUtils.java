package com;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/*
	Tạo EntityManager từ file `persistence.xml`
 */
public class EntityManagerUtils {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexample");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		User user = new User(null, "Java", new Date());
		em.persist(user);
		transaction.commit();
		System.out.println("OK! ......");
	}

}
