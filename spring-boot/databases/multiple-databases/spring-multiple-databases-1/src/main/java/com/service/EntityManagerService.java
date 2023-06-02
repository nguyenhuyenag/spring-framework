package com.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Service;

import com.config.SecondDbConfig;
import com.primary.entity.Customer;
import com.second.entity.Office;

@Service
@SuppressWarnings("unchecked")
public class EntityManagerService {

	@PersistenceContext // or (unitName = "persistence1Unit")
	private EntityManager entity1Manager;

	@PersistenceContext(unitName = SecondDbConfig.PERSISTENCE_UNIT_NAME)
	private EntityManager entity2Manager;

	@Autowired
	private SessionFactory sessionFactory;

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

	public void save() {
		save1();
		save2();
	}

	public void save1() {
		Session session = entity1Manager.unwrap(Session.class);
		session.save(getC1());
	}

	public void save2() {
		Session session = entity2Manager.unwrap(Session.class);
		session.save(getOff1());
	}

	public void saveAll() {
		// SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		System.out.println(transaction);
//	     
//	    transaction.commit();
//	    session.close();
//	    sessionFactory.close();
	}

	public Customer getC1() {
		Customer c1 = new Customer();
		c1.setCustomerName("Euro+ Shopping Channel");
		c1.setContactLastName("Freyre");
		c1.setContactFirstName("Diego");
		c1.setPhone("(91) 555 94 44");
		c1.setAddressLine1("Moralzarzal");
		c1.setAddressLine2("C86");
		c1.setCity("Madrid");
		c1.setState("28034");
		c1.setPostalCode("44000");
		c1.setCountry("Spain");
		c1.setSalesRepEmployeeNumber(1370);
		c1.setCreditLimit(227600.00);
		return c1;
	}

	public Office getOff1() {
		Office office1 = new Office();
		// office1.setOfficeCode(8);
		office1.setCity("Tokyo");
		office1.setPhone("+27 10 5887 1952");
		office1.setAddressLine1("89 New Lat Street");
		office1.setAddressLine2("Level 9");
		office1.setCountry("Japan");
		office1.setPostalCode("JC2N 1CR");
		office1.setTerritory("QMEA");
		return office1;
	}

}
