package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.entity.Order;
import com.repository.OrderRepository;

@Service
public class FindByExample {

	@Autowired
	private OrderRepository orderRepository;

	public void test() {
		Order order = new Order();
		order.setStatus("Shipped");
		order.setComments("They");

		ExampleMatcher matcher = ExampleMatcher.matching() //
				.withMatcher("status", GenericPropertyMatchers.exact()) //
				.withMatcher("comments", GenericPropertyMatchers.startsWith());

		Example<Order> example = Example.of(order, matcher);
		List<Order> findAll = orderRepository.findAll(example);
		if (!findAll.isEmpty()) {
			findAll.forEach(t -> System.out.println(t));
		}
	}

}
