package com.n1.service;

import com.n1.entity.Book;
import com.n1.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    /*
        HibernateLazyInitializer, LazyInitializationException
        => Xảy ra khi ta cố gắng truy cập dữ liệu liên quan (được định nghĩa
        với FetchType.LAZY) ngoài phạm vi của Hibernate Session.
     */
    public List<Book> findAllBooks() {
        return bookRepository.findAllBooks(); // Error
    }

    // Cách 1: Sử dụng JOIN FETCH
    public List<Book> findAllBooksWithJoinFetch() {
        return bookRepository.findAllBooksWithJoinFetch();
    }

    // Cách 2: Sử dụng @EntityGraph
    public List<Book> findAllBooksWithEntityGraph() {
        return bookRepository.findAllBooksWithEntityGraph();
    }

}
