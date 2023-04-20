package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.repository.VocabRepository;
import com.service.FileService;

@SpringBootApplication
@EnableMongoAuditing
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired 
	VocabRepository repository;
	
	@Autowired
	FileService fileService;
	
	@Override
	public void run(String... args) throws Exception {
		
//		List<Vocabulary> list = repository.findAll();
//		repository.findAll().forEach(t -> {
//			// int count = ThreadLocalRandom.current().nextInt(0, 99 + 1);
//			// t.setCount(count);
//			// t.setCreatedDatetime(new Date());
//		});
//		repository.saveAll(list);
		
//		Optional<Vocabulary> opt = repository.findByWord("abc");
//		if (opt.isPresent()) {
//			// repository.delete(opt.get());
//			// System.out.println("DELETE");
//			// repository.save(new Vocabulary("abc", "abc", "abc"));
//			
//			// System.out.println(opt.get());
//			// Vocabulary v = opt.get();
//			// v.setTranslate("ddddddddddddddddddd");
//			// v.setCreatedDatetime(new Date());
//			// repository.save(v);
//		}
		
		// repository.deleteAll();
		
		// fileService.importData();
		
	}

}
