package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.TransientEntity;

@Repository
public interface TransRepository extends JpaRepository<TransientEntity, Integer> {

}
