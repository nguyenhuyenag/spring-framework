package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.enumerated.Programing;

public interface ProgramingRepository extends JpaRepository<Programing, Integer> {

}
