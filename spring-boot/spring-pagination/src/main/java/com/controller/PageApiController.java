package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.ProductService;

@RestController
@RequestMapping("api")
public class PageApiController {

	@Autowired
	private ProductService productService;

	@GetMapping("/page-info")
	public ResponseEntity<?> info(HttpServletRequest request) {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		int size = ServletRequestUtils.getIntParameter(request, "size", 1);
		boolean showContent = ServletRequestUtils.getBooleanParameter(request, "showContent", false);
		if (showContent) {
			return ResponseEntity.ok(productService.getContent(page, size));
		}
		return ResponseEntity.ok(productService.info(request, page, size));
	}

}
