package com.controller;

import com.entity.SaoKe;
import com.repository.SaoKeRepository;
import lombok.RequiredArgsConstructor;
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

	private final SaoKeRepository saoKeRepository;

	@GetMapping({ "/", "index" })
	public String index() {
		return "index";
	}

	@GetMapping("/transactions")
	@ResponseBody
	public ResponseEntity<?> transactions(@RequestParam(defaultValue = "1") int pageNo,
									   @RequestParam(defaultValue = "10") int pageSize) {
		Pageable paging = PageRequest.of(pageNo - 1, pageSize);
		return ResponseEntity.ok(saoKeRepository.findAll(paging));
	}

}
