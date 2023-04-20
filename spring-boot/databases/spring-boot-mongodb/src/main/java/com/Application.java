package com;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.entity.Book;
import com.repository.BookRepository;

@SpringBootApplication
// @EnableMongoAuditing
// @EnableMongoRepositories
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	BookRepository repository;

	public void find() {
		List<Book> findAll = repository.findAll();
		if (!findAll.isEmpty()) {
			Book book = findAll.get(0);
			System.out.println(book.toString());
		}
	}

	public void insert() {
		Book book = new Book();
		book.setTitle("Android in Action, Second Edition");
		book.setIsbn("1935182722");
		book.setPageCount(592);
		repository.save(book);
		System.out.println("OK");
	}

	@Override
	public void run(String... args) throws Exception {
		find();
		// insert();
	}

}
