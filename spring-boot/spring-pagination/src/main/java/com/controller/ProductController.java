package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.model.Product;
import com.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping({ "/", "home" })
	public String home() {
		return "home";
	}

	@GetMapping("product")
	public String index(HttpServletRequest request, ModelMap model) {
		List<Product> products = productService.findAll();
		PagedListHolder<Product> pagedList = new PagedListHolder<>(products);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedList.setPage(page); // trang hiện tại
		pagedList.setPageSize(7); // số dòng mỗi trang
		// System.out.println("getPageCount(): " + pagedListHolder.getPageCount());
		model.put("pagedListHolder", pagedList);
		return "product";
	}

}
