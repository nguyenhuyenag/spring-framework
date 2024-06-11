package com.n1.repository;

import com.n1.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b")
    List<Book> findAllBooks();

    @Query("SELECT b FROM Book b JOIN FETCH b.author")
    List<Book> findAllBooksWithJoinFetch();

    @EntityGraph(attributePaths = { "author" })
    @Query("SELECT b FROM Book b")
    List<Book> findAllBooksWithEntityGraph();

}
