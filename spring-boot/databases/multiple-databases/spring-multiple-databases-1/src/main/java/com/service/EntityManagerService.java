package com.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Service;

import com.primary.entity.Customer;
import com.second.entity.Office;

@Service
@SuppressWarnings("unchecked")
public class EntityManagerService {

	@PersistenceContext(unitName = "persistence1Unit")
	private EntityManager entity1Manager;

	@PersistenceContext(unitName = "persistence2Unit")
	private EntityManager entity2Manager;

	public void showDataSourceURL() {
		EntityManagerFactoryInfo info1 = (EntityManagerFactoryInfo) entity1Manager.getEntityManagerFactory();
		EntityManagerFactoryInfo info2 = (EntityManagerFactoryInfo) entity2Manager.getEntityManagerFactory();
		try {
			String url1 = info1.getDataSource().getConnection().getMetaData().getURL();
			String url2 = info2.getDataSource().getConnection().getMetaData().getURL();
			System.out.println(url1);
			System.out.println(url2);
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
		Query query = entity1Manager.createNativeQuery(sql, Customer.class);
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
