package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.model.TProduct;
import com.repository.TProductRepository;

@Service
public class TProductService {

	@Autowired
	private TProductRepository repo;

	public Page<TProduct> listAll(int pageNum, String sortField, String sortDir) {
		Pageable pageable = PageRequest.of(pageNum - 1, 5, //
				sortDir.equals("asc") ? Sort.by(sortField).ascending() //
						: Sort.by(sortField).descending() //
		);
		return repo.findAll(pageable);
	}

	public void save(TProduct tProduct) {
		repo.save(tProduct);
	}

	public TProduct get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}
