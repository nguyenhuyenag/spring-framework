package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.ReceiveMessage;

@Repository
public interface ReceiveMessageRepository extends JpaRepository<ReceiveMessage, Long> {

}
