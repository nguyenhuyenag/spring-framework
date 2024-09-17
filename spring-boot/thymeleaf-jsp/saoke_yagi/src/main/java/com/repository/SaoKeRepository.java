package com.repository;

import com.entity.SaoKe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaoKeRepository extends JpaRepository<SaoKe, Long> {

}
