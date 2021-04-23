package com;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public List<Product> findAll() {
		return repository.findAll();
	}

}
