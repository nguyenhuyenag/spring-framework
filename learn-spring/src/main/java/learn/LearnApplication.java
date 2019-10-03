package learn;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import learn.of.properties.Configss;

@SpringBootApplication
public class LearnApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
	}

	@Autowired
	private Configss binding;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(binding.getUrl());
		System.out.println(Arrays.toString(binding.getVersion().stream().toArray(String[]::new)));
		System.out.println(binding.getPhone());
	}

}
