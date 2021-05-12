package com.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.manytomany.Category;
import com.entity.manytomany.Product;
import com.repository.CategoryRepository;
import com.service.ManyToManyService;

@Service
public class ManyToManyServiceImpl implements ManyToManyService {

	@Autowired
	private CategoryRepository repository;

	@Override
	@Transactional
	public void demoInsert1() {
		Category category = new Category("Electronic Device");

		Set<Product> products = new HashSet<>();
		products.add(new Product("Television"));
		products.add(new Product("Iphone"));
		products.add(new Product("Samsung Galaxy S9"));

		category.setProducts(products);

		repository.save(category);
		
		repository.deleteById(2);

	}

}
