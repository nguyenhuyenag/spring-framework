package com.service.impl;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dto.SomeField;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MappingQueryToPOJOService {

	private static ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager entityManager;

	private final String SQL = "SELECT t1.orderNumber, t1.status, SUM(quantityOrdered * priceEach) total" //
			+ " FROM orders t1" //
			+ " INNER JOIN orderdetails t2" //
			+ " ON t1.orderNumber = t2.orderNumber" //
			+ " GROUP BY orderNumber"//
			+ " LIMIT 10";

	/**
	 * Object[]{String, Integer} -> Class(String, Integer)
	 */
	private <T> T parseArrayDataToClassObject(Object[] objects, Class<T> type) {
		try {
			/**
			 * Thứ tự biến trong getConstructor() phải giống với trong SomeField.class
			 */
			Class<?>[] listTypes = Stream.of(objects).map(Object::getClass).toArray(Class[]::new);
			// System.out.println(Arrays.toString(listTypes));
			Constructor<T> ctor = type.getConstructor(listTypes);
			return ctor.newInstance(objects);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private <T> List<T> map(Class<T> type, List<Object[]> records) {
		List<T> result = new LinkedList<>();
		for (Object[] record : records) {
			result.add(parseArrayDataToClassObject(record, type));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> getResultList(Query query, Class<T> type) {
		List<Object[]> records = query.getResultList();
		return map(type, records);
	}

	private BeanPropertyRowMapper<SomeField> rowMapper() {
		return BeanPropertyRowMapper.newInstance(SomeField.class);
	}

	public void forJdbcTemplate() {
		List<SomeField> results = jdbcTemplate.query(SQL, rowMapper());
		if (results != null) {
			results.forEach(t -> System.out.println(t));
		}
	}

	private <T> List<T> parseResult(List<?> list, Class<T> clz) {
		List<T> result = new ArrayList<>();
		for (Map<String, Object> v : convertTuplesToMap(list)) {
			result.add(objectMapper.convertValue(v, clz));
		}
		return result;
	}

	private List<Map<String, Object>> convertTuplesToMap(List<?> tuples) {
		List<Map<String, Object>> result = new ArrayList<>();
		for (Object object : tuples) {
			if (!(object instanceof Tuple)) {
				throw new RuntimeException("Query should return instance of Tuple");
			}
			Tuple tuple = (Tuple) object;
			Map<String, Object> tempMap = new HashMap<>();
			for (TupleElement<?> key : tuple.getElements()) {
				tempMap.put(key.getAlias(), tuple.get(key));
			}
			result.add(tempMap);
		}
		return result;
	}

	public void forTuple() {
		javax.persistence.Query query = entityManager.createNativeQuery(SQL, Tuple.class);
		List<SomeField> results = parseResult(query.getResultList(), SomeField.class);
		if (results != null) {
			results.forEach(t -> System.out.println(t));
		}
	}

	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public void forSession() {
		Session session = entityManager.unwrap(Session.class);
		// (1) Query
		org.hibernate.Query query = session.createSQLQuery(SQL);
		// (2) NativeQuery
		// org.hibernate.query.NativeQuery query = session.createSQLQuery(SQL);
		query.setResultTransformer(Transformers.aliasToBean(SomeField.class));
		List<SomeField> results = query.getResultList();
		if (results != null) {
			results.forEach(t -> System.out.println(t));
		}
	}

	public void forEntityManager() {
		javax.persistence.Query query = entityManager.createNativeQuery(SQL);
		List<SomeField> results = getResultList(query, SomeField.class);
		if (results != null) {
			results.forEach(t -> System.out.println(t));
		}
	}

}
