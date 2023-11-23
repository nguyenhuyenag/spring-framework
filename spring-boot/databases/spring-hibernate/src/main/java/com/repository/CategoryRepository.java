package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.manytomany.type2.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
