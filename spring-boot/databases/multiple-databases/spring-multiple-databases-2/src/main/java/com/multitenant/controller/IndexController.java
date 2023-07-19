package com.multitenant.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multitenant.config.TenantContext;
import com.multitenant.domain.Office;
import com.multitenant.repository.OfficeRepository;

@Controller
public class IndexController {

	@Autowired
	private OfficeRepository officeRepository;

	@GetMapping("index")
	public String index() {
		return "index";
	}

	@ResponseBody
	@GetMapping("/offices")
	public ResponseEntity<?> offices() {
		List<Office> offices = officeRepository.findAll();
		Map<String, Object> result = new LinkedHashMap<>();
		result.put("tenant", TenantContext.getCurrentTenant());
		result.put("offices", offices);
		return ResponseEntity.ok(result);
	}

}
