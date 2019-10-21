package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import core.entity.cascade.Country;
import core.repository.CountryRepositoty;

@Controller
@RequestMapping("country")
public class CountryController {

	@Autowired
	CountryRepositoty repositoty;

	@GetMapping("load-all")
	private ResponseEntity<List<Country>> loadAll() {
		List<Country> list = repositoty.findAll();
		if (!list.isEmpty()) {
			list.forEach(System.out::println);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
