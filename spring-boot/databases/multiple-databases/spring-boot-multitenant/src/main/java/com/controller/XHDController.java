package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Data;
import com.multitenant.MultiTenantManager;
import com.repository.DataRepository;

@RestController
public class XHDController {

	// private static final Logger LOG = LoggerFactory.getLogger(XHDController.class);

	@Autowired
	private DataRepository repository;

	@GetMapping("find-all")
	public ResponseEntity<?> switchDatabase(@RequestParam(defaultValue = "1") int db) {
		MultiTenantManager.switchDatabase2(db);
		List<Data> findAll = repository.findAll();
		// LOG.info("Size: {}", findAll.size());
		return ResponseEntity.ok(findAll);
	}
	
//	@GetMapping("switch-database-2")
//	public ResponseEntity<?> switchDatabase2(@RequestParam(defaultValue = "1") int db) {
//		boolean flag = MultiTenantManager.switchDatabase(db);
//		LOG.info("Switch database: {}", flag);
//		return ResponseEntity.ok(MultiTenantManager.latestTenant);
//	}
	
//	@GetMapping("find-all")
//	public ResponseEntity<?> findAll() {
//		List<Data> findAll = repository.findAll();
//		return ResponseEntity.ok(findAll);
//	}

}
