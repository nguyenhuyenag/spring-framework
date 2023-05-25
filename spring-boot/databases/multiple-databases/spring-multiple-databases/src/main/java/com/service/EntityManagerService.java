package com.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.second.entity.Office;

@Service
public class EntityManagerService {

	@Autowired
	private EntityManager entity2ManagerByPersistenceUnit;

	@Autowired
	public EntityManagerService(@Qualifier("secondEntityManager") EntityManager entityManager) {
		this.entity2ManagerByPersistenceUnit = entityManager;
	}

	// @Autowired
	// @PersistenceContext(unitName = "secondPersistenceUnit")
	// private EntityManager entity2ManagerByPersistenceUnit;

	public List<Office> findAll() {
		String sql = "SELECT t.* FROM office t";
		Query query = entity2ManagerByPersistenceUnit.createNativeQuery(sql, Office.class);
		List<Office> resultList = query.getResultList();
		return resultList;
	}

}
