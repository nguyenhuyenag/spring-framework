package com;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.PageUtils;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	int count = 0;
	
	@Override
	public void run(String... args) throws Exception {
		while (true) {
			int total =  ThreadLocalRandom.current().nextInt(0, 1000);
			List<Integer> list = IntStream.rangeClosed(1, total).boxed().collect(Collectors.toList());
			int page = ThreadLocalRandom.current().nextInt(1, total);
			System.err.println("Total: " + total + ", page: " + page);
			List<List<Integer>> split = PageUtils.createPageFromList(list, page);
			split.forEach(t -> {
				count += t.size();
				// System.out.println(t.toString());
			});
			System.err.println("Check: " + (count - list.size()));
			TimeUnit.SECONDS.sleep(1);
			count = 0;
			System.out.println();
		}
	}

}
