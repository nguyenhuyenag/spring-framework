package com.controller;

import com.entity.SaoKe;
import com.repository.SaoKeRepository;
import com.service.SaoKeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SaoKeController {

	private final SaoKeService saoKeService;
	private final SaoKeRepository saoKeRepository;

	@GetMapping({ "/", "index" })
	public String index() {
		return "index";
	}

	@GetMapping("/transactions")
	@ResponseBody
	public ResponseEntity<?> transactions(@RequestParam(required = false, defaultValue = "") String query) {
//		int pageNo = 1;
//		int pageSize = 10;
//		Pageable paging = PageRequest.of(pageNo - 1, pageSize);
//		Page<SaoKe> result = saoKeRepository.findAll(paging);
		List<?> list = saoKeService.listTransactions(query.trim());
		return ResponseEntity.ok(list);
	}

}
