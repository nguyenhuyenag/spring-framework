package com.r2dbc.mysql.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.r2dbc.mysql.model.Tutorial;

import reactor.core.publisher.Flux;

public interface TutorialRepository extends R2dbcRepository<Tutorial, Integer> {

	Flux<Tutorial> findByTitleContaining(String title);

	Flux<Tutorial> findByPublished(boolean isPublished);

}
