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

//	@GetMapping("test")
//	public String index(HttpServletRequest request, ModelMap model) {
//		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
//		List<Product> findAll = null;
//		PagedListHolder<Product> pagedList = new PagedListHolder<>(findAll);
//		pagedList.setPage(page); // trang hiện tại
//		pagedList.setPageSize(7); // số dòng mỗi trang
//		// System.out.println("getPageCount(): " + pagedListHolder.getPageCount());
//		model.put("pagedListHolder", pagedList);
//		return "product/product";
//	}

//	@GetMapping("/user")
//	public ResponseEntity<?> get(@RequestParam(defaultValue = "1") int page, //
//			@RequestParam(defaultValue = "10") int size, //
//			@RequestParam(defaultValue = "id") String sortby) {
//		List<Product> pages = productService.get(page, size, sortby);
//		return ResponseEntity.ok(pages);
//	}

	@GetMapping("/page-info")
	public ResponseEntity<?> info(HttpServletRequest request) {
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		int size = ServletRequestUtils.getIntParameter(request, "size", 1);
		boolean showContent = ServletRequestUtils.getBooleanParameter(request, "showContent", false);
		// System.out.println(request.getRequestURL());
		if (showContent) {
			return ResponseEntity.ok(productService.getContent(page, size));
		}
		return ResponseEntity.ok(productService.info(request, page, size));
	}

}
