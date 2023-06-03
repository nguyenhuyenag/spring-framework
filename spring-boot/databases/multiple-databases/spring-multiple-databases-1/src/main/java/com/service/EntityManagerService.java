//package com.service;
//
//import org.springframework.stereotype.Service;
//
//import com.primary.entity.Customer;
//
//@Service
//// @SuppressWarnings("unchecked")
//public class EntityManagerService {
//
////	@PersistenceContext // or (unitName = "persistence1Unit")
////	private EntityManager entity1Manager;
////
////	@PersistenceContext(unitName = SecondDbConfig.PERSISTENCE_UNIT_NAME)
////	private EntityManager entity2Manager;
////
////	@Autowired
////	private SessionFactory sessionFactory;
////
////	public void showDataSourceURL() {
////		EntityManagerFactoryInfo info1 = (EntityManagerFactoryInfo) entity1Manager.getEntityManagerFactory();
////		EntityManagerFactoryInfo info2 = (EntityManagerFactoryInfo) entity2Manager.getEntityManagerFactory();
////		try {
////			String url1 = info1.getDataSource().getConnection().getMetaData().getURL();
////			String url2 = info2.getDataSource().getConnection().getMetaData().getURL();
////			System.out.println(url1);
////			System.out.println(url2);
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////	}
////
////	public void findAll() {
////		find1All();
////		find2All();
////	}
////
////	public void find1All() {
////		String sql = "SELECT t.* FROM customer t;";
////		Query query = entity1Manager.createNativeQuery(sql, Customer.class);
////		List<Customer> result = query.getResultList();
////		if (!result.isEmpty()) {
////			result.forEach(t -> System.out.println(t));
////		}
////	}
////
////	public void find2All() {
////		String sql = "SELECT t.* FROM office t;";
////		Query query = entity2Manager.createNativeQuery(sql, Office.class);
////		List<Office> result = query.getResultList();
////		if (!result.isEmpty()) {
////			result.forEach(t -> System.out.println(t));
////		}
////	}
////
////	public void save() {
////		save1();
////		save2();
////	}
////
////	public void save1() {
////		Session session = entity1Manager.unwrap(Session.class);
////		session.save(getC1());
////	}
////
////	public void save2() {
////		Session session = entity2Manager.unwrap(Session.class);
////		session.save(getOff1());
////	}
////
////	public void saveAll() {
////		// SessionFactory sessionFactory = config.buildSessionFactory();
////		Session session = sessionFactory.openSession();
////
////		Transaction transaction = session.beginTransaction();
////		System.out.println(transaction);
//////	     
//////	    transaction.commit();
//////	    session.close();
//////	    sessionFactory.close();
////	}
////
//
////
////
//}
