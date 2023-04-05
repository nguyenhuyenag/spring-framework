package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Product;
import com.repository.ProductRepository;
import com.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductRepository repository;
	
	@RequestMapping({"/", "product"})
	public String viewHomePage(Model model) {
		return viewPage(model, 1, "name", "asc");
	}
	
	@RequestMapping("/page/{pageNum}")
	public String viewPage(Model model, //
			@PathVariable(name = "pageNum") int pageNum, // 
			@Param("sortField") String sortField, //
			@Param("sortDir") String sortDir) {
		
		Page<Product> page = service.listAll(pageNum, sortField, sortDir);
		
		List<Product> listProducts = page.getContent();
		
		model.addAttribute("currentPage", pageNum);		
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", "asc".equals(sortDir) ? "desc" : "asc");
		
		model.addAttribute("listProducts", listProducts);
		
		return "product";
	}
	
	@GetMapping("simple-pagination")
	public String index(HttpServletRequest request, ModelMap model) {
		// int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		// PagedListHolder<Product> pagedList = new PagedListHolder<>(repository.findAll());
		// pagedList.setPage(page); 	// trang hiện tại
		// pagedList.setPageSize(7); 	// số dòng mỗi trang
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		int size = ServletRequestUtils.getIntParameter(request, "size", 10);
		Page<Product> pagedResult = repository.findAll(PageRequest.of(page, size));
		model.put("pageList", pagedResult.getContent());
		return "simple-pagination";
	}
	
}
