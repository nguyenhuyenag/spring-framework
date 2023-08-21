package com.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Order;

public interface SpringDataSelectSpecificColumns extends JpaRepository<Order, Integer> {

	public static interface SubOrder {

		Integer getOrderNumber();

		LocalDate getOrderDate();

		String getStatus();

		String getComments();

	}

	@Query(value = "SELECT t.orderNumber, t.orderDate, t.status, t.comments FROM orders t WHERE t.comments is not null;", nativeQuery = true)
	List<SubOrder> findAllSubOrder();

}
