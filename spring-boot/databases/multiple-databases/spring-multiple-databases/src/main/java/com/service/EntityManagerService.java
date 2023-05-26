package com.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Service;

import com.primary.entity.Customer;
import com.second.entity.Office;

@Service
@SuppressWarnings("unchecked")
public class EntityManagerService {

	@Autowired
	@PersistenceContext(unitName = "primaryPersistenceUnit")
	private EntityManager entityManager;

	@Autowired
	@PersistenceContext(unitName = "secondPersistenceUnit")
	private EntityManager entity2Manager;

	public void showDataSourceURL() {
		EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) entityManager.getEntityManagerFactory();
		try {
			String url = info.getDataSource().getConnection().getMetaData().getURL();
			System.out.println("URL: " + url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void findAll() {
		find1All();
		find2All();
	}

	public void find1All() {
		String sql = "SELECT t.* FROM customer t;";
		Query query = entityManager.createNativeQuery(sql, Customer.class);
		List<Customer> result = query.getResultList();
		if (!result.isEmpty()) {
			result.forEach(t -> System.out.println(t));
		}
	}

	public void find2All() {
		String sql = "SELECT t.* FROM office t;";
		Query query = entity2Manager.createNativeQuery(sql, Office.class);
		List<Office> result = query.getResultList();
		if (!result.isEmpty()) {
			result.forEach(t -> System.out.println(t));
		}
	}

}
