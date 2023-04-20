package com.repository.second;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.second.Bar;

public interface BarRepository extends JpaRepository<Bar, Long> {

}
