package com.spring.repository.second;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.second.Bar;

public interface BarRepository extends JpaRepository<Bar, Long> {

}
