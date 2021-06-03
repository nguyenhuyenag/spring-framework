package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.entity.Vocabulary;
import com.repository.VocabRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	VocabRepository repository;

	@Override
	public void run(String... args) throws Exception {
		Vocabulary v = new Vocabulary();
		v.setWord("light");
		v.setPronounce("laɪ-t");
		v.setTranslate("nhẹ, ánh sáng");
		repository.save(v);
	}

}
