package com.service;

import com.entity.Jokes;

public interface JokesService {

	Jokes getOne(int id);

	void autoInsert();

}
