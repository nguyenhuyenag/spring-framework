//package com.n1.test;
//
//import com.n1.entity.Book;
//import com.n1.repository.BookRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class AppRunner implements ApplicationRunner {
//
//    private final BookRepository bookRepository;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        bookRepository.findAllBooks().forEach(b -> System.out.println(b.getAuthor()));
//    }
//
//}
