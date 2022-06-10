package com.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.entity.Jokes;
import com.repository.JokesRepository;
import com.response.JokesResponse;
import com.service.JokesService;

@Service
public class JokesServiceImpl implements JokesService {

	private static final Logger LOG = LoggerFactory.getLogger(JokesServiceImpl.class);

	@Autowired
	private JokesRepository repository;

	private final static String URL = "http://api.icndb.com/jokes/random";

	private static final RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public Jokes getOne() {
		return repository.getOne();
	}

	@Override
	public void insert() {
		while (true) {
			JokesResponse res = restTemplate.getForObject(URL, JokesResponse.class);
			Jokes jokes = new Jokes();
			int id = res.getValue().getId();
			if (repository.existsById(id)) {
				LOG.info("Duplicate id={}", id);
				continue;
			}
			jokes.setId(id);
			jokes.setType(res.getType());
			jokes.setJoke(res.getValue().getJoke().replaceAll("&quot;", ""));
			List<String> list = res.getValue().getCategories();
			if (!list.isEmpty()) {
				jokes.setCategories(StringUtils.join(list));
			}
			if (repository.save(jokes) != null) {
				LOG.info("Insert id={} to table jokes", id);
			}
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
