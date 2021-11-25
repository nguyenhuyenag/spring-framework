package com.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<User> get(int page, int size, String sortby) {
		Pageable pageRequest = PageRequest.of(page, size, Sort.by(sortby));
		Sort.by("id").and(Sort.by("email"));
		Page<User> recordsPage = repository.findAll(pageRequest);
		if (recordsPage.hasContent()) {
			return recordsPage.getContent();
		}
		return Collections.emptyList();
	}

	public void pagingWithoutSorting(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<User> pagedResult = repository.findAll(paging);
		pagedResult.getContent();
	}

	public void pagingWithSorting(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("email"));
		// Pageable pageRequest = PageRequest.of(page, size, Sort.by(sortby).ascending());
		// Pageable pageRequest = PageRequest.of(page, size, Sort.by(sortby).descending());
		Page<User> pagedResult = repository.findAll(paging);
		pagedResult.getContent();
	}

	public void sortingOnly(int pageNo, int pageSize) {
		Sort sortOrder = Sort.by("email");
		List<User> list = repository.findAll(sortOrder);
		System.out.println(list.size());
	}

	public void sortingAnd(int pageNo, int pageSize) {
		Sort emailSort = Sort.by("email");
		Sort firstNameSort = Sort.by("first_name");
		Sort groupBySort = emailSort.and(firstNameSort);
		List<User> list = repository.findAll(groupBySort);
		System.out.println(list.size());
	}

	@Override
	public Map<String, Object> info(int page, int size) {
		Map<String, Object> map = new HashMap<>();
		Page<User> pagedResult = repository.findAll(PageRequest.of(page, size));
		map.put("Page number", pagedResult.getNumber());
		map.put("Total pages", pagedResult.getTotalPages());
		map.put("Total elements", pagedResult.getTotalElements());
		map.put("Size of page", pagedResult.getSize());
		map.put("isFirst", pagedResult.isFirst());
		map.put("isLast", pagedResult.isLast());
		// pagedResult.hasContent();
		// map.put("data", pagedResult.getContent());
		return map;
	}

}
