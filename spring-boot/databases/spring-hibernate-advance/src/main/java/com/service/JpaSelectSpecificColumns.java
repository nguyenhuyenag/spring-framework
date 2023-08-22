package com.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Order;

public interface JpaSelectSpecificColumns extends JpaRepository<Order, Integer> {

	final String SQL_QUERY = "SELECT t.orderNumber, t.orderDate, t.status, t.comments "
			+ "FROM orders t WHERE t.comments is not null";

	public static interface SubOrder {

		Integer getOrderNumber();

		LocalDate getOrderDate();

		String getStatus();

		String getComments();

	}

	@Query(value = SQL_QUERY, nativeQuery = true)
	List<SubOrder> findAllSubOrder();

}
