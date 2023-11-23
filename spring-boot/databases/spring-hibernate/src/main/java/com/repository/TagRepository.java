package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.manytomany.type1.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
