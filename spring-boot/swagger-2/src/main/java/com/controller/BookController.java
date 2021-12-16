package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Book;

@RestController
@RequestMapping("book")
public class BookController {

	private static List<Book> list = new ArrayList<>();

	static {
		list.add(new Book("Java", 6));
		list.add(new Book("PHP", 9));
		list.add(new Book("C#", 50));
		list.add(new Book("C", 90));
		list.add(new Book("Python", 18));
		list.add(new Book("MYSQL", 21));
	}

	@GetMapping(value = "find-all")
	public ResponseEntity<?> findById() {
		return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	}

}
