package com.util;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.model.Product;
import com.repository.ProductRepository;

@Component
@RequiredArgsConstructor
public class PageableJPA {

	private static final ProductRepository repository = null;

	// @Autowired // Constructor @Autowired
//	public PageableJPA(ProductRepository repository) {
//		PageableJPA.repository = repository;
//	}

	public static void init() {
		// Page 0
		// PageRequest.of(0, 5) -> Lấy ra page đầu tiên (page thứ 0), mỗi page 5 phần tử.
		Page<Product> page = repository.findAll(PageRequest.of(0, 5));
		System.out.println("In ra 5 user đầu tiên: ");
		page.forEach(System.out::println);

		// Page 1
		System.out.println("In ra 5 user tiếp theo: ");
		// page = userRepository.findAll(PageRequest.of(1, 5)); // Sử dụng PageRequest mới
		page = repository.findAll(page.nextPageable()); // Tận dụng đối tượng Page trước đó
		page.forEach(System.out::println);
	}

	public static void info() {
		Page<Product> page = repository.findAll(PageRequest.of(0, 5));
		System.out.println("Số lượng user ở page hiện tại: " + page.getSize());
		System.out.println("Tổng số lượng user: " + page.getTotalElements());
		System.out.println("Tổng số page: " + page.getTotalPages());
	}

	// Sort theo tên, lấy ra 5 user ở page 1. Lưu ý, phương thức này sắp xếp trước rồi mới chia page
	public static void sortPage() {
		int pageNo = 1;
		int pageSize = 10;
		// PageRequest.of(pageNo, pageSize, Sort.by("email"));
		// PageRequest.of(pageNo, pageSize, Sort.Direction.ASC, "email");
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "email"));
		Page<Product> page = repository.findAll(pageable);
		page.forEach(System.out::println);
	}

	public static List<Product> PageToList() {
		Page<Product> page = repository.findAll(Pageable.unpaged()); // get all
		return page.getContent();
	}

	public static void showAllPage(int elementPerPage) {
		int i = 0;
		Page<Product> page = repository.findAll(PageRequest.of(0, elementPerPage));
		// Page<Product> page = repository.findAllWithTypeId(PageRequest.of(0,
		// elementPerPage));
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

}
