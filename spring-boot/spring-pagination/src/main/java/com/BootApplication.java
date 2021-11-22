package com;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.model.User;
import com.repository.UserRepository;
import com.test.PageableJPA;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Autowired
	UserRepository repository;

//	public static <T> Page<T> createPageFromList(List<T> list, Pageable pageable) {
//		if (list == null) {
//			throw new IllegalArgumentException("To create a Page, the list mustn't be null!");
//		}
//		int startOfPage = pageable.getPageNumber() * pageable.getPageSize();
//		if (startOfPage > list.size()) {
//			return new PageImpl<>(new ArrayList<>(), pageable, 0);
//		}
//		int endOfPage = Math.min(startOfPage + pageable.getPageSize(), list.size());
//		return new PageImpl<>(list.subList(startOfPage, endOfPage), pageable, list.size());
//	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 12; i++) {
			User u = new User();
			u.setName(RandomStringUtils.randomAlphanumeric(5).toUpperCase());
			// repository.save(u);
		}
		// PageableJPA.showAllPage(7);
		// System.out.println("OK");
		List<User> list = repository.findAll();
		PageableJPA.createPageFromList(list, 4).forEach(t -> System.out.println(t.toString()));
		// page.getContent().forEach(t -> System.out.println(t));
	}

}
