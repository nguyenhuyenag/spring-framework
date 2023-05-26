package com.service.impl;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.SomeField;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SelectSomeFieldService {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	public static <T> T map(Class<T> type, Object[] tuple) {
		List<Class<?>> tupleTypes = new ArrayList<>();
		for (Object field : tuple) {
			tupleTypes.add(field.getClass());
		}
		try {
			Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
			return ctor.newInstance(tuple);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> List<T> map(Class<T> type, List<Object[]> records) {
		List<T> result = new LinkedList<>();
		for (Object[] record : records) {
			result.add(map(type, record));
		}
		return result;
	}

	public static <T> List<T> getResultList(Query query, Class<T> type) {
		@SuppressWarnings("unchecked")
		List<Object[]> records = query.getResultList();
		return map(type, records);
	}

	final String SQL = "SELECT t1.orderNumber, t1.status, SUM(quantityOrdered * priceEach) total" //
			+ " FROM orders t1" //
			+ " INNER JOIN orderdetails t2" //
			+ " ON t1.orderNumber = t2.orderNumber" //
			+ " GROUP BY orderNumber";

	public void forEntityManager() {
		javax.persistence.Query query = entityManager.createNativeQuery(SQL);
		List<SomeField> results = getResultList(query, SomeField.class);
		if (results != null) {
			results.forEach(t -> System.out.println(t));
		}
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public void forSession() {
		Session session = entityManager.unwrap(Session.class);
		NativeQuery query = session.createSQLQuery(SQL);
		query.setResultTransformer(Transformers.aliasToBean(SomeField.class));
		List<SomeField> results = query.getResultList();
		if (results != null) {
			results.forEach(t -> System.out.println(t));
		}
	}

	public static List<Map<String, Object>> convertTuplesToMap(List<?> tuples) {
		List<Map<String, Object>> result = new ArrayList<>();
		for (Object object : tuples) {
			if (object instanceof Tuple) {
				Tuple single = (Tuple) object;
				Map<String, Object> tempMap = new HashMap<>();
				for (TupleElement<?> key : single.getElements()) {
					tempMap.put(key.getAlias(), single.get(key));
				}
				result.add(tempMap);
			} else {
				throw new RuntimeException("Query should return instance of Tuple");
			}
		}
		return result;
	}

	public <T> List<T> parseResult(List<?> list, Class<T> clz) {
		List<T> result = new ArrayList<>();
		List<Map<String, Object>> maps = convertTuplesToMap(list);
		for (Map<String, Object> map : maps) {
			result.add(objectMapper.convertValue(map, clz));
		}
		return result;
	}

	public void for2Session() {
		Query query = entityManager.createNativeQuery(SQL, Tuple.class);
		List<SomeField> results = parseResult(query.getResultList(), SomeField.class);
		if (results != null) {
			results.forEach(t -> System.out.println(t));
		}
	}

}
