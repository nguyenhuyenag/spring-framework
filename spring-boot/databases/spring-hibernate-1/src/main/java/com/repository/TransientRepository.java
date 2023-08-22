package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.TransientEntity;

public interface TransientRepository extends JpaRepository<TransientEntity, Integer> {

}
