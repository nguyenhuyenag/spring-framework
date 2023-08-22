package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.onetoone.Students;

public interface StudentsRepository extends JpaRepository<Students, Integer> {

}
