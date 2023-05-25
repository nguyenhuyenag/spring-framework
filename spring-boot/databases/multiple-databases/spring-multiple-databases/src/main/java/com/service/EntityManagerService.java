package com.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Service;

import com.second.entity.Office;

@Service
public class EntityManagerService {

	@Autowired
	@PersistenceContext(unitName = "primaryPersistenceUnit")
	// @PersistenceContext(unitName = "secondPersistenceUnit")
	private EntityManager entity2Manager;

	public void showDataSourceURL() {
		EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) entity2Manager.getEntityManagerFactory();
		try {
			String url = info.getDataSource().getConnection().getMetaData().getURL();
			System.out.println("URL: " + url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Office> findAll() {
		String sql = "SELECT t.* FROM office t";
		Query query = entity2Manager.createNativeQuery(sql, Office.class);
		List<Office> resultList = query.getResultList();
		return resultList;
	}

}
