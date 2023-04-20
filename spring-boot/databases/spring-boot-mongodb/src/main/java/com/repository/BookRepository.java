package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entity.Book;

// @Transactional
public interface BookRepository extends MongoRepository<Book, Integer> {

}
