package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.Dao;
import com.entity.second.Bar;

@Service
public class BarDaoImpl implements Dao<Bar> {

	// private EntityManager secondEntityManager;
	//
	// @Autowired
	// public BarDaoImpl(@Qualifier("secondEntityManager") EntityManager
	// secondEntityManager) {
	// this.secondEntityManager = secondEntityManager;
	// }

	@Autowired
	@PersistenceContext(unitName = "secondPersistenceUnit")
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<Bar> getAll() {
		List<Bar> list = new ArrayList<>();
		String hql = "FROM Bar";
		Query query = entityManager.createQuery(hql);
		// Query query = secondEntityManager.createQuery(hql);
		list = query.getResultList();
		return list;
	}

}
