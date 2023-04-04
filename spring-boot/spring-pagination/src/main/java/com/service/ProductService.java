package com.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.model.Product;
import com.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Map<String, Object> info(HttpServletRequest request, int page, int size) {
		Map<String, Object> map = new LinkedHashMap<>();
		Page<Product> pagedResult = repository.findAll(PageRequest.of(page, size));
		map.put("Total items", pagedResult.getTotalElements());
		map.put("Total pages", pagedResult.getTotalPages());
		map.put("Page number (current page)", pagedResult.getNumber());
		map.put("Page size", pagedResult.getSize());
		// map.put("isFirst", pagedResult.isFirst());
		// map.put("isLast", pagedResult.isLast());
		// map.put("hasContent", pagedResult.hasContent());
		String url = request.getRequestURL() + "?" + request.getQueryString() + "&showContent=true";
		map.put("Contents", url);
		return map;
	}
	
	public List<Product> getContent(int page, int size) {
		Page<Product> pagedResult = repository.findAll(PageRequest.of(page, size));
		return pagedResult.getContent();
	}

//	public List<Product> get(int page, int size, String sortby) {
//		Pageable pageRequest = PageRequest.of(page, size, Sort.by(sortby));
//		Sort.by("id").and(Sort.by("email"));
//		Page<Product> recordsPage = repository.findAll(pageRequest);
//		if (recordsPage.hasContent()) {
//			return recordsPage.getContent();
//		}
//		return Collections.emptyList();
//	}

	public void pagingWithoutSorting(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Product> pagedResult = repository.findAll(paging);
		pagedResult.getContent();
	}

	public void pagingWithSorting(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("email"));
		// Pageable pageRequest = PageRequest.of(page, size,
		// Sort.by(sortby).ascending());
		// Pageable pageRequest = PageRequest.of(page, size,
		// Sort.by(sortby).descending());
		Page<Product> pagedResult = repository.findAll(paging);
		pagedResult.getContent();
	}

	public void sortingOnly(int pageNo, int pageSize) {
		Sort sortOrder = Sort.by("email");
		List<Product> list = repository.findAll(sortOrder);
		System.out.println(list.size());
	}

	public void sortingAnd(int pageNo, int pageSize) {
		Sort emailSort = Sort.by("email");
		Sort firstNameSort = Sort.by("first_name");
		Sort groupBySort = emailSort.and(firstNameSort);
		List<Product> list = repository.findAll(groupBySort);
		System.out.println(list.size());
	}

	public Page<Product> listAll(int pageNum, String sortField, String sortDir) {
		Pageable pageable = PageRequest.of(pageNum - 1, 5, //
				sortDir.equals("asc") ? Sort.by(sortField).ascending() //
						: Sort.by(sortField).descending() //
		);
		return repository.findAll(pageable);
	}

//	public void save(Product tProduct) {
//		repository.save(tProduct);
//	}
//
//	public Product get(Long id) {
//		return repository.findById(id).get();
//	}
//
//	public void delete(Long id) {
//		repository.deleteById(id);
//	}
}
