package com.n1.controller;

import com.n1.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books-lazy-error")
    public ResponseEntity<?> findAllBooks() {
        var books = bookService.findAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books-lazy-initialization-exception")
    public ResponseEntity<?> lazyInitializationException() {
        var books = bookService.lazyInitializationException();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books-join-fetch-author")
    public ResponseEntity<?> findAllBooksWithJoinFetch() {
        var books = bookService.findAllBooksWithJoinFetch();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books-entity-graph-author")
    public ResponseEntity<?> findAllBooksWithEntityGraph() {
        var books = bookService.findAllBooksWithEntityGraph();
        return ResponseEntity.ok(books);
    }

}
