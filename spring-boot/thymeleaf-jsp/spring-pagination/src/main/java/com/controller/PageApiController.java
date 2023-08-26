package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
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
	public ResponseEntity<?> info(HttpServletRequest request) throws ServletRequestBindingException {
		boolean showContent = ServletRequestUtils.getBooleanParameter(request, "showContent", false);
		if (showContent) {
			return ResponseEntity.ok(productService.getContent(request));
		}
		return ResponseEntity.ok(productService.info(request));
	}

}
