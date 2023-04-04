package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Product;
import com.service.ProductService;

@RestController
public class PageController {

	@Autowired
	private ProductService productService;
	
//	@GetMapping("product")
//	public String index(HttpServletRequest request, ModelMap model) {
//		List<Product> products = productService.findAll();
//		PagedListHolder<Product> pagedList = new PagedListHolder<>(products);
//		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
//		pagedList.setPage(page); // trang hiện tại
//		pagedList.setPageSize(7); // số dòng mỗi trang
//		// System.out.println("getPageCount(): " + pagedListHolder.getPageCount());
//		model.put("pagedListHolder", pagedList);
//		return "product/product";
//	}

	@GetMapping("user")
	public ResponseEntity<?> get(@RequestParam(defaultValue = "1") int page, //
			@RequestParam(defaultValue = "10") int size, //
			@RequestParam(defaultValue = "id") String sortby) {
		List<Product> pages = productService.get(page, size, sortby);
		return ResponseEntity.ok(pages);
	}

	@GetMapping("page-info")
	public ResponseEntity<?> info(HttpServletRequest req) {
		String page = req.getParameter("page");
		String size = req.getParameter("size");
		Map<String, Object> map = productService.info(Integer.parseInt(page), Integer.parseInt(size));
		return ResponseEntity.ok(map);
	}

}
