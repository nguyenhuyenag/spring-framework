package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Book;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("book")
public class BookController {

	private static List<Book> list = new ArrayList<>();

	static {
		list.add(new Book("Java", 19));
		list.add(new Book("C", 22));
		list.add(new Book("Python", 33));
		list.add(new Book("PHP", 5));
		list.add(new Book("C++", 89));
	}

	@GetMapping("/")
	@ApiOperation(value = "Hàm lấy danh sách Book")
	public List<Book> getAllBook() {
		return list;
	}

//	@GetMapping("/{id}")
//	public Book getBookById(@PathVariable String id) {
//		return new Book(id, "Book A", "Deft", 10000);
//	}
//
	@ApiOperation(value = "Hàm tạo mới Book")
	@PostMapping("/")
	public Book create(@RequestBody Book book) {
		list.add(book);
		return book;
	}
//
//	@PutMapping("/")
//	public void update(@RequestBody Book book) {
//		System.out.println(book.getId());
//	}

}
