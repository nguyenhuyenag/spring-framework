package com.service.impl;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.SomeField;

@Service
public class SelectSomeFieldService {

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

	public void forEntityManager() {
		String sql = "SELECT t1.orderNumber, t1.status, SUM(quantityOrdered * priceEach) total" //
				+ " FROM orders t1" //
				+ " INNER JOIN orderdetails t2" //
				+ " ON t1.orderNumber = t2.orderNumber" //
				+ " GROUP BY orderNumber";
		javax.persistence.Query query = entityManager.createNativeQuery(sql);
		List<SomeField> results = getResultList(query, SomeField.class);
		if (results != null) {
			results.forEach(t -> System.out.println(t));
		}
	}

}
