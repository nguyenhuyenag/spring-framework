package com.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.repository.UserRepository;

@RestController
@RequestMapping("user")
public class ParamController {

	@Autowired
	private UserRepository repository;

	@GetMapping("list-user")
	private ResponseEntity<List<User>> getAll() {
		List<User> list = repository.findAll();
		// HttpHeaders responseHeaders = new HttpHeaders();
		return ResponseEntity.ok(list);
	}
	
	/**
	 * Annotation PathVariable được sử dụng để xử lý những URI động, có một hoặc nhiều paramter bên trong URI
	 */
	@RequestMapping("/test1/{id}")
	public String test1(@PathVariable("id") int id, Model model) {
		model.addAttribute("id", id);
		return "test1";
	}

	@RequestMapping("/test2/{id}/{name}")
	public String test2(@PathVariable("id") int id, @PathVariable("name") String name, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		return "test2";
	}
    
	/**
	 * /user/id/11
	 * 
	 * 11 được gọi là tham số đường dẫn
	 */
	@GetMapping("id/{id}")
	// @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Was Not Found")
	private ResponseEntity<User> getById(@PathVariable("id") int id) {
		Optional<User> user = repository.findByIdNativeSQL(id);
		if (user.isPresent()) {
			return ResponseEntity.ok().body(user.get());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/*-
	 * /user/find			(0)
	 * /user/find?id=1 		(1)
	 * 
	 * @RequestParam(required = true) 		: (0) => 400 Bad Request: Required String parameter 'id' is not present
	 * @RequestParam(required = false)		: (0) => id = null
	 * 										: Mặc định `required = true`
	 * 
	 * @RequestParam(defaultValue = "test") : (0) => id = test
	 * 
	 * String id
	 * @RequestParam int id
	 * @RequestParam("id") int id
	 * @RequestParam(name = "id") String id
	 * @RequestParam(value = "id") String id
	 */
	@GetMapping("find")
	private ResponseEntity<String> getByIds(@RequestParam("id") String id) {
		String result = "ID: " + id;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/*-
	 * /user/map-all?a=1&b=2&c=3
	 */
	@GetMapping("map-all")
	private ResponseEntity<String> map(@RequestParam Map<String, String> params) {
		Set<Entry<String, String>> entrys = params.entrySet();
		for (Entry<String, String> e : entrys) {
			System.out.println(e.getKey() + " => " + e.getValue());
		}
		String result = "Parameters are: " + entrys;
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/*-
	 * /user/set-all?name=ABC&name=DEF
	 */
	@GetMapping("set-all")
	private ResponseEntity<String> set(@RequestParam Set<String> name) {
		for (String s : name) {
			System.out.println(s);
		}
		String result = "Parameters are: " + name;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/*-
	 * /user/list-all?id=1,2,3
	 * /user/list-all?id=1&id=2
	 */
	@GetMapping("list-all")
	private ResponseEntity<String> list(@RequestParam List<String> id) {
		String result = "Parameters are: " + id;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
