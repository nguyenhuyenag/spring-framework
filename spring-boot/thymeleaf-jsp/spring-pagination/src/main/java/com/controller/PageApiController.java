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

import java.util.Map;

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

    @GetMapping("/spring-jpa-pagination")
    public ResponseEntity<?> getProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize, // Min = 10 is the best
            String sortBy) {
        if (pageNumber <= 0) {
            var result = Map.of("message", "Page number must be ≥ 1");
            return ResponseEntity.ok(result);
        }
        var result = productService.springJpaPagination(pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/entity-manager-pagination")
    public ResponseEntity<?> getProductsByEntityManager(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize, // Min = 10 is the best
            String sortBy) {
        if (pageNumber <= 0) {
            var result = Map.of("message", "Page number must be ≥ 1");
            return ResponseEntity.ok(result);
        }
        var result = productService.entityManagerPagination(pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(result);
    }

}
