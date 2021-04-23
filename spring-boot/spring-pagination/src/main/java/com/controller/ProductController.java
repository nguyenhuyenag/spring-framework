package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.Product;
import com.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = { "/", "product" }, method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		List<Product> products = productService.findAll();
		PagedListHolder<?> pagedListHolder = new PagedListHolder<>(products);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(3);
		model.put("pagedListHolder", pagedListHolder);
		return "index";
	}

}
