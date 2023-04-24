package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Restaurant;
import com.repository.RestaurantRepository;

@Service
public class MongodbTransaction {

	@Autowired
	private RestaurantRepository repository;

	@Transactional
	public void testTransaction() {
		// r1
		Restaurant r1 = new Restaurant();
		r1.setAddress("6 Drummond Street");
		r1.setAddressLine2("Rotherham");
		r1.setName("007 Takeaway");
		r1.setOutcode("S65");
		r1.setPostcode("S65");
		r1.setRating(6);
		r1.setTypeOfFood("Pizza");
		r1.setUrl("http://www.just-eat.co.uk/restaurants-007takeaway-s65/menu");
		repository.save(r1);
		// r2
		Restaurant r2 = new Restaurant();
		r2.setAddress("885 High Road Leytonstone");
		r2.setAddressLine2("London");
		r2.setName("042 Restaurant & Bar");
		r2.setOutcode("E11");
		r2.setPostcode("1HR");
		// r2.setRating(Integer.parseInt("1A"));
		r2.setTypeOfFood("African");
		r2.setUrl("http://www.just-eat.co.uk/restaurants-042-restaurant-e11/menu");
		// repository.save(r2);
	}

}
