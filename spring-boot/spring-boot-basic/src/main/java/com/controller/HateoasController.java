package com.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
class Greeting extends RepresentationModel<Greeting> {

	private final String content;

	@JsonCreator
	public Greeting(@JsonProperty("content") String content) {
		this.content = content;
	}

}

/*-
	{
		"content": "Hello, World!",
		"_links": {
			"self": {
	        	"href": "http://localhost:8082/greeting?name=World"
			}
	    }
	}
 */

@RestController
public class HateoasController {

	private static final String TEMPLATE = "Hello, %s!";

	@GetMapping("/greeting")
	public HttpEntity<Greeting> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		Greeting greeting = new Greeting(String.format(TEMPLATE, name));
		greeting.add(linkTo(methodOn(HateoasController.class).greeting(name)).withSelfRel());
		return new ResponseEntity<>(greeting, HttpStatus.OK);
	}

}
