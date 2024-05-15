package com.controller;

import javax.servlet.http.HttpServletRequest;

import com.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.ProductService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PageApiController {

    // @Autowired
    private final ProductService productService;

    @GetMapping("/page-info")
    public ResponseEntity<?> info(HttpServletRequest request) throws ServletRequestBindingException {
        boolean showContent = ServletRequestUtils.getBooleanParameter(request, "showContent", false);
        if (showContent) {
            return ResponseEntity.ok(productService.getContent(request));
        }
        return ResponseEntity.ok(productService.info(request));
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize, // Min = 10 is the best
            String sortBy) {
        var result = productService.getProducts(pageNo, pageSize, sortBy);
        return ResponseEntity.ok(result);
    }

}
