package com.n1.service;

import com.n1.entity.Book;
import com.n1.repository.BookRepository;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
    https://thorben-janssen.com/lazyinitializationexception/
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    @PersistenceUnit // (unitName = "readwrite.config")
    private EntityManagerFactory emf;

    private final BookRepository bookRepository;

    /*
        HibernateLazyInitializer, LazyInitializationException
        => Xảy ra khi ta cố gắng truy cập dữ liệu liên quan (được định nghĩa với FetchType.LAZY)
        ngoài phạm vi của Hibernate Session.
     */
    public List<Book> findAllBooks() {
        return bookRepository.findAllBooks();
    }

    // LazyInitializationException
    @Transactional
    public List<Book> lazyInitializationException() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Error
            String query = "SELECT a FROM Book a";

            // OK
            // String query = "SELECT a FROM Book a LEFT JOIN FETCH a.author";

            TypedQuery<Book> q = em.createQuery(query, Book.class);

            List<Book> books = q.getResultList();
            em.getTransaction().commit();

//        for (Book book : books) {
//            var authors = book.getAuthor();
//            log.info("... the next line will throw LazyInitializationException ...");
//            books.size();
//        }
            return books;
        }
    }

    // Cách 1: Sử dụng JOIN FETCH
    public List<Book> findAllBooksWithJoinFetch() {
        return bookRepository.findAllBooksWithJoinFetch();
    }

    // Cách 2: Sử dụng @EntityGraph
    public List<Book> findAllBooksWithEntityGraph() {
        return bookRepository.findAllBooksWithEntityGraph();
    }

    // Cách 3: Sử dụng @NamedEntityGraph
    /*
        @Entity
        @NamedEntityGraph(
            name = "Book.author",
            attributeNodes = @NamedAttributeNode("author")
        )
        public class Book {

        }

        ------------------------------------------------------------------
        Cách 1:
        @Repository
        public interface BookRepository extends JpaRepository<Book, Long> {
            @EntityGraph(value = "Book.author")
            Book findByTitle(String title);
        }

        ------------------------------------------------------------------
        Cách 2:
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        EntityGraph<?> entityGraph = em.createEntityGraph("Book.author");
        TypedQuery<Author> q = em.createQuery("SELECT a FROM Author a", Author.class)
                .setHint("javax.persistence.fetchgraph", entityGraph);
        List<Author> authors = q.getResultList();

        ------------------------------------------------------------------
        Cách 3:
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        EntityGraph<Author> entityGraph = em.createEntityGraph(Author.class);
        entityGraph.addAttributeNodes("books");
        TypedQuery<Author> q = em.createQuery("SELECT a FROM Author a", Author.class)
                .setHint("javax.persistence.fetchgraph", entityGraph);
        List<Author> authors = q.getResultList();
     */

}
