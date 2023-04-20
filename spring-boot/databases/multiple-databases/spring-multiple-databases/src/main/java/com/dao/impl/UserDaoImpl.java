package com.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.Dao;
import com.entity.primary.User;

@Service
public class UserDaoImpl implements Dao<User> {

	// @Autowired
	// private EntityManager entityManager;
	//
	// @Autowired
	// public UserDaoImpl(@Qualifier("entityManager") EntityManager entityManager) {
	// this.entityManager = entityManager;
	// }

	@Autowired
	@PersistenceContext(unitName = "primaryPersistenceUnit")
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		List<User> list = null;
		String hql = "FROM User";
		Query query = entityManager.createQuery(hql);
		list = query.getResultList();
		return list;
	}

}
