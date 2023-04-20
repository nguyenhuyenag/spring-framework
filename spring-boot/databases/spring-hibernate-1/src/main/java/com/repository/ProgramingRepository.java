package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.enumerated.Programing;

@Repository
public interface ProgramingRepository extends JpaRepository<Programing, Integer> {

}
