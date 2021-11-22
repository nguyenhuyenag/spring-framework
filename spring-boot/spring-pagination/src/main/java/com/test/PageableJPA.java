package com.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.model.User;
import com.repository.UserRepository;

@Component
public class PageableJPA {

	private static UserRepository repository;

	@Autowired // Constructor @Autowired
	public PageableJPA(UserRepository repo) {
		PageableJPA.repository = repo;
	}

	public static void init() {
		// Lấy ra 5 user đầu tiên: PageRequest.of(0, 5) tương đương với lấy ra page đầu
		// tiên (page 0), mỗi page 5 phần tử
		// Page đầu tiên
		Page<User> page = repository.findAll(PageRequest.of(0, 5));
		System.out.println("In ra 5 user đầu tiên: ");
		page.forEach(System.out::println);

		// Page tiếp theo
		System.out.println("In ra 5 user tiếp theo: ");
		// page = userRepository.findAll(PageRequest.of(1, 5)); // Sử dụng PageRequest
		// mới
		page = repository.findAll(page.nextPageable()); // Tận dụng đối tượng Page trước đó
		page.forEach(System.out::println);
	}

	public static void info() {
		Page<User> page = repository.findAll(PageRequest.of(0, 5));
		System.out.println("Số lượng user ở page hiện tại: " + page.getSize());
		System.out.println("Tổng số lượng user: " + page.getTotalElements());
		System.out.println("Tổng số page: " + page.getTotalPages());
	}

	// Sort theo tên, lấy ra 5 user ở page 1. Lưu ý, phương thức này sắp xếp trước
	// rồi mới chia page
	public static void sortPage() {
		// Page<User> page = repository.findAll(PageRequest.of(1, 5, Sort.by("email")));
		Page<User> page = repository.findAll(PageRequest.of(1, 5, Sort.Direction.ASC, "email"));
		page.forEach(System.out::println);
	}

	public static List<User> PageToList() {
		Page<User> page = repository.findAll(Pageable.unpaged()); // get all
		return page.getContent();
	}

	public static void showAllPage(int elementPerPage) {
		int i = 0;
		// Page<User> page = repository.findAll(PageRequest.of(0, elementPerPage));
		Page<User> page = repository.findAllWithTypeId(PageRequest.of(0, elementPerPage));
		System.out.println("Tổng số lượng user: " + page.getTotalElements());
		while (i < page.getTotalPages()) {
			System.out.println("Current page: " + (page.getNumber() + 1));
			System.out.println("Number of Elements: " + page.getNumberOfElements());
			page.forEach(System.out::println);
			page = repository.findAllWithTypeId(page.nextPageable());
			i++;
			System.out.println();
		}
	}

	// pageable = PageRequest.of(i, sizeOfPage)
	public static <T> Page<T> createPageFromList(List<T> list, Pageable pageable) {
		if (list == null) {
			throw new IllegalArgumentException("To create a Page, the list mustn't be null!");
		}
		int total = list.size();
		int start = pageable.getPageNumber() * pageable.getPageSize();
		if (start > total) {
			return new PageImpl<>(new ArrayList<>(), pageable, 0);
		}
		int end = Math.min(start + pageable.getPageSize(), total);
		return new PageImpl<>(list.subList(start, end), pageable, total);
	}

	public static <T> List<List<T>> createPageFromList(List<T> list, int nPage) {
		List<List<T>> result = new ArrayList<>();
		if (list == null || list.size() == 0) {
			return result;
		}
		int sizeOfPage = 0;
		int total = list.size();
		if (total % nPage == 0) {
			sizeOfPage = total / nPage;
		} else {
			sizeOfPage = total / nPage + 1;
		}
		for (int i = 0; i < nPage; i++) {
			Page<T> page = createPageFromList(list, PageRequest.of(i, sizeOfPage));
			result.add(page.getContent());
		}
		return result;
	}

}
