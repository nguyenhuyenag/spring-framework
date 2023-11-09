package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.manytomany.type2.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
