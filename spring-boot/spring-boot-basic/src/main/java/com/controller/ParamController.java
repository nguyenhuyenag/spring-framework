package com.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.repository.UserRepository;

@RestController
@RequestMapping("user")
public class ParamController {

	@Autowired
	private UserRepository repository;

	@RequestMapping(value = "list-user", method = { RequestMethod.PUT, RequestMethod.POST })
	private ResponseEntity<List<User>> getAll() {
		List<User> list = repository.findAll();
		// HttpHeaders responseHeaders = new HttpHeaders();
		return ResponseEntity.ok(list);
	}

	/**
	 * @PathVariable được sử dụng để xử lý những URI động, có một hoặc nhiều
	 *               paramter bên trong URI
	 * 
	 *               /user/id/11 -> `11` được gọi là tham số đường dẫn
	 */

	// One variable
	@GetMapping("/test1/{id}")
	public String test1(@PathVariable("id") int id) {
		System.out.println("ID: " + id);
		return "test1";
	}

	// Multiple variable
	@GetMapping("/test2/{id}/{name}")
	public String test2(@PathVariable("id") int id, @PathVariable("name") String name) {
		System.out.println("ID: " + id);
		System.out.println("Name: " + name);
		return "test2";
	}

	// Using Map for multiple variable: /api/employees/1/bar
	@GetMapping("/api/employees/{id}/{name}")
	@ResponseBody
	public String getEmployeesByIdAndNameWithMapVariable(@PathVariable Map<String, String> pathVarsMap) {
		String id = pathVarsMap.get("id");
		String name = pathVarsMap.get("name");
		if (id != null && name != null) {
			return "ID: " + id + ", name: " + name;
		}
		return "Missing Parameters";
	}

	/*-
	 * Optional Path Variables
	 * 
	 * 		/api/employeeswithrequired		->	Erorr 404
	 * 
	 * 		/api/employeeswithrequired/1   	-> 	ID: 111
	 */
	@GetMapping(value = { "/api/employeeswithrequired", "/api/employeeswithrequired/{id}" })
	@ResponseBody
	public String getEmployeesByIdWithRequired(@PathVariable String id) {
		return "ID: " + id;
	}

	/*-
	 * Setting @PathVariable as Not Required
	 */
	@GetMapping({ "/api/employeeswithoptional", "/api/employeeswithoptional/{id}" })
	@ResponseBody
	public String getEmployeesByIdWithOptional(@PathVariable Optional<String> id) {
		if (id.isPresent()) {
			return "ID: " + id.get();
		}
		return "ID missing";
	}

	/**
	 * @RequestParam
	 */

	/*-
	 * For: /user/find
	 * 		/user/find/id
	 * 
	 * 		@RequestParam(required = true) 			: 400 Bad Request if 'id' is not present
	 * 		@RequestParam(required = false)			: id = null
	 * 		@RequestParam(defaultValue = "test")	: id = test
	 * 
	 * - Các khai báo dưới đây là tương đương
	 * 		
	 * 		String id
	 * 		@RequestParam int id
	 * 		@RequestParam("id") int id
	 * 		@RequestParam(name = "id") String id
	 * 		@RequestParam(value = "id") String id
	 */
	@GetMapping("find")
	private ResponseEntity<String> getByIds(@RequestParam("id") String id) {
		String result = "ID: " + id;
		return ResponseEntity.ok(result);
	}

	// /user/map-all?name=Zen&age=25&country=US
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
	 * /user/list-all?id=1,2,3
	 * 
	 * /user/list-all?id=1&id=2
	 */
	@GetMapping("list-all")
	private ResponseEntity<String> list(@RequestParam List<String> id) {
		String result = "Parameters are: " + id;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// /user/set-all?name=ABC&name=DEF
	@GetMapping("set-all")
	private ResponseEntity<String> set(@RequestParam Set<String> name) {
		for (String s : name) {
			System.out.println(s);
		}
		String result = "Parameters are: " + name;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
