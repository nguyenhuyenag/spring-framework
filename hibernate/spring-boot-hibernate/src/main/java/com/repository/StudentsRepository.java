package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.onetoone.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer> {

}
