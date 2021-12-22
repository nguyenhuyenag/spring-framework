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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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

	@GetMapping("get-all")
	@ApiOperation(value = "Hàm lấy danh sách Book")
	public List<Book> getAllBook() {
		return list;
	}

	@GetMapping("find-by-name")
	@ApiOperation(value = "Hàm tìm kiếm Book theo tên")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Success", response = String.class), //
			@ApiResponse(code = 422, message = "Book not found"), //
			@ApiResponse(code = 417, message = "Exception failed") //
	})
	public Book findBook(String name) {
		for (Book book : list) {
			if (book.getName().toLowerCase().contains(name.toLowerCase())) {
				return book;
			}
		}
		return null;
	}

	@ApiOperation(value = "Hàm tạo mới Book")
	@PostMapping("create")
	public Book create(@RequestBody Book book) {
		list.add(book);
		return book;
	}

}
