package learn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import learn.entity.City;
import learn.service.CityService;

@SpringBootApplication
public class LearnApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
	}
	
	@Autowired
	private CityService cityService;

	@Override
	public void run(String... args) throws Exception {
		List<City> list = cityService.findAll();
		System.out.println(list.size());
	}

}
