package com.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class GetSession {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void getSession() {
		Session session = entityManager.unwrap(Session.class);
		System.out.println(session.getTenantIdentifier());
	}
	
}
